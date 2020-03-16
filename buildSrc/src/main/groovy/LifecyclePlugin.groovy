import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.IOUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

// Transform 来自 android gradle 插件
class LifecyclePlugin extends Transform implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def android = project.extensions.android
        println("apply LifecyclePlugin")
        android.registerTransform(this)
    }

    // 返回这个 Transform 的名字
    @Override
    String getName() {
        return getClass().simpleName
    }

    // what
    // Transform 要处理的输入类型
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        // CONTENT_CLASS：处理 class 文件，可能在 dir 中或 jar 包中
        return TransformManager.CONTENT_CLASS
    }

    // where
    // Transform 要处理的范围
    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        // FULL_PROJECT：本项目和其所有的依赖
        return TransformManager.SCOPE_FULL_PROJECT
    }

    // Transform 是否能处理增量作业
    // 如果返回 true，TransformInput 中将会携带增删改信息
    // 没太理解
    @Override
    boolean isIncremental() {
        return false
    }

    // how
    // 要执行的具体操作
    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        println("transform start")
        def startTime = System.currentTimeMillis()

        // TransformInput: 包含一个 directoryInput 列表、一个 jarInput 列表
        Collection<TransformInput> inputs = transformInvocation.inputs

        // TransformOutputProvider: Output 的提供者
        TransformOutputProvider outputProvider = transformInvocation.outputProvider

        // 删除之前的输出
        if (!outputProvider) {
            outputProvider.deleteAll()
        }

        inputs.each {
            it.directoryInputs.each {
                handleDirectoryInput(it, outputProvider)
            }

            it.jarInputs.each {
                handleJarInput(it, outputProvider)
            }
        }

        def cost = (System.currentTimeMillis() - startTime) / 1000
        println("cost: ${cost}")
    }

    static void handleDirectoryInput(DirectoryInput directoryInput, TransformOutputProvider outputProvider) {
        if (directoryInput.file.isDirectory()) {
            directoryInput.file.eachDirRecurse { file ->
                def name = file.name
                if (isFragmentActivityClassFile()) {
                    ClassReader classReader = new ClassReader(file.bytes)
                    ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                    ClassVisitor classVisitor = new LifecycleClassVisitor(classWriter)
                    // ??
                    classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES)
                    byte[] code = classWriter.toByteArray()
                    FileOutputStream fos = new FileOutputStream(file.parentFile.absolutePath + File.separator + name)
                    fos.write(code)
                    fos.close()
                }
            }
        }

        // 处理完输入文件之后，要把输出给下一个任务
        def dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes,
                directoryInput.scopes, Format.DIRECTORY)
        FileUtils.copyDirectory(directoryInput.file, dest)
    }


    static void handleJarInput(JarInput jarInput, TransformOutputProvider outputProvider) {
        if (!jarInput.file.absolutePath.endsWith(".jar")) {
            return
        }
        def jarName = jarInput.name
        def md5Name = DigestUtils.md5Hex(jarInput.file.absolutePath)
        if (jarName.endsWith(".jar")) {
            jarName = jarName.substring(0, jarName.length() - 4)
        }
        JarFile jarFile = new JarFile(jarInput.file)
        Enumeration<JarEntry> enumerations = jarFile.entries()
        File tmpFile = new File(jarInput.file.getParent() + File.separator + "classes_tmp.jar")
        if (tmpFile.exists()) {
            tmpFile.delete()
        }
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(tmpFile))
        enumerations.each {
            String entryName = it.name
            ZipEntry zipEntry = new ZipEntry(entryName)
            InputStream inputStream = jarFile.getInputStream(it)
            if (isFragmentActivityClassFile(entryName)) {
                println("dela with jar class file $entryName")
                jarOutputStream.putNextEntry(zipEntry)
                ClassReader classReader = new ClassReader(IOUtils.toByteArray(inputStream))
                ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                ClassVisitor classVisitor = new LifecycleClassVisitor(classWriter)
                classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES)
                byte[] code = classWriter.toByteArray()
                jarOutputStream.write(code)
            } else {
                jarOutputStream.putNextEntry(zipEntry)
                jarOutputStream.write(IOUtils.toByteArray(inputStream))
            }
            jarOutputStream.closeEntry()
        }
        jarOutputStream.close()
        jarFile.close()
        def dest = outputProvider.getContentLocation(jarName + md5Name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
        FileUtils.copyFile(tmpFile, dest)
        tmpFile.delete()
    }

    static boolean isFragmentActivityClassFile(String name) {
        return "android/support/v4/app/FragmentActivity.class" == name
    }
}