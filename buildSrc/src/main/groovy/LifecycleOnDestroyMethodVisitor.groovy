import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class LifecycleOnDestroyMethodVisitor extends MethodVisitor implements Opcodes {

    private static final String TAG = "zhangjlASM"

    LifecycleOnDestroyMethodVisitor(MethodVisitor mv) {
        // Opcodes.ASM4 是 ASM 的 api 版本
        super(Opcodes.ASM5, mv)
    }

    @Override
    void visitCode() {
        super.visitCode()
        // 方法执行前插入
        mv.with {
            visitLdcInsn(TAG)
            visitTypeInsn(NEW, "java/lang/StringBuilder")
            visitInsn(DUP)
            visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false)
            visitLdcInsn("-------> onDestroy : ")
            visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
            visitVarInsn(ALOAD, 0)
            visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false)
            visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getSimpleName", "()Ljava/lang/String;", false)
            visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
            visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false)
            visitMethodInsn(INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false)
            visitInsn(POP)
        }
    }

    @Override
    void visitInsn(int opcode) {
        // 方法执行后插入
        super.visitInsn(opcode)
    }
}
