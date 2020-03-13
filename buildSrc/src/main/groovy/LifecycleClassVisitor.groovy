import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class LifecycleClassVisitor extends ClassVisitor implements Opcodes {

    public static final String TARGET_CLASS_NAME = 'android/support/v4/app/FragmentActivity'
    private String mClassName

    LifecycleClassVisitor(int api) {
        super(api)
    }

    LifecycleClassVisitor(int api, ClassVisitor cv) {
        super(api, cv)
    }

    @Override
    void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        mClassName = name
        super.visit(version, access, name, signature, superName, interfaces)
    }

    @Override
    MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions)
        if (TARGET_CLASS_NAME == mClassName) {
            if ("onCreate" == name) {
                println("change method --> $name")
                return new LifecycleOnCreateMethodVisitor("ss")
            }
        }
        return super.visitMethod(access, name, desc, signature, exceptions)
    }

    @Override
    void visitEnd() {
        println("visitEnd")
        super.visitEnd()
    }
}