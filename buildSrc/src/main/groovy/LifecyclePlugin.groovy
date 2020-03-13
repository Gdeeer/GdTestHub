import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.impldep.com.amazonaws.services.s3.model.lifecycle.LifecyclePredicateVisitor
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

// Transform 来自 android gradle 插件
class LifecyclePlugin extends Transform implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def android = project.extensions.android
        println("here: $android")
        android.registerTransform(this)
    }

    @Override
    String getName() {
        return getClass().simpleName
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        println("transform start")
        def startTime = System.currentTimeMillis()
        Collection<TransformInput> inputs = transformInvocation.inputs
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

    void handleDirectoryInput(DirectoryInput directoryInput, TransformOutputProvider outputProvider) {
        if (directoryInput.file.isDirectory()) {
            directoryInput.file.eachDirRecurse { file ->
                def name = file.name
                if (isFragmentActivityClassFile()) {
                    ClassReader classReader = new ClassReader(file.bytes)
                    ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                }
            }
        }
    }


    void handleJarInput(JarInput jarInput, TransformOutputProvider outputProvider) {

    }

    boolean isFragmentActivityClassFile(String name) {
        return "android/support/v4/app/FragmentActivity.class" == name
    }
}