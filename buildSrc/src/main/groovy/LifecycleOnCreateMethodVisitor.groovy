import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class LifecycleOnCreateMethodVisitor extends MethodVisitor implements Opcodes {

    LifecycleOnCreateMethodVisitor(MethodVisitor mv) {
        // Opcodes.ASM4 是什么意思？
        super(ASM4, mv)
    }

    @Override
    void visitCode() {
        super.visitCode()
        // 方法执行前插入
        mv.visitLdcInsn("TAG")
        mv.visitTypeInsn(NEW, "java/lang/StringBuilder")
        mv.visitInsn(DUP)
    }

    @Override
    void visitInsn(int opcode) {
        // 方法执行后插入
        super.visitInsn(opcode)
    }
}
