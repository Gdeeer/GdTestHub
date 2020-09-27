package com.gdeer.rule

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UClass

/**
 * Detector: 可以在 lint 中使用
 * SourceCodeScanner：可以检查 java、kotlin 文件
 */
class TextViewCodeDetector : Detector(), SourceCodeScanner {
    companion object {
        // TODO: 2020/9/24 Scope.JAVA_AND_RESOURCE_FILES 不生效
        val ISSUE = Issue.create(
                id = "TextViewCodeDetector",
                briefDescription = "Detects incorrect TextView usages",
                explanation = "Detects incorrect TextView usages",
                implementation = Implementation(TextViewCodeDetector::class.java, Scope.JAVA_FILE_SCOPE),
                priority = 9,
                category = Category.CORRECTNESS,
                severity = Severity.FATAL
        )

        private const val TEXT_VIEW = "android.widget.TextView"
        private const val EDIT_TEXT = "android.widget.EditText"
        private const val BUTTON = "android.widget.Button"
        private val listOriginView = listOf(
                TEXT_VIEW, EDIT_TEXT, BUTTON
        )

        private const val INSTEAD_FONT_TEXT = "com.gdeer.gdtesthub.other.lint.C"
        private val listInsteadView = listOf(
                INSTEAD_FONT_TEXT
        )
        private const val INSTEAD_VIEW = "FontText、FontEditText or their child view"
    }

    override fun getApplicableConstructorTypes() = listOriginView

    override fun visitConstructor(context: JavaContext, node: UCallExpression, constructor: PsiMethod) {
//        println("visitConstructor: ${node.returnType} ${node.kind} ${node.methodIdentifier} ${node.methodName} ${node.receiver} ${node.receiverType} ${node.typeArguments} ${node.valueArguments}")
//        println("visitConstructor: ${node.sourcePsi} + ${context.psiFile?.javaClass}")
        context.report(
                issue = ISSUE,
                scope = node,
                location = context.getCallLocation(
                        call = node,
                        includeReceiver = true,
                        includeArguments = true
                ),
                message = "Instantiate an instance of `$INSTEAD_VIEW` instead."
        )
    }

    override fun applicableSuperClasses() = listOriginView

    override fun visitClass(context: JavaContext, declaration: UClass) {
        if (listOriginView.contains(declaration.qualifiedName) ||
                listInsteadView.contains(declaration.qualifiedName)) {
            // Don't warn if this is the actual support library being compiled.
            return
        }
        if (context.evaluator.inheritsFrom(declaration, INSTEAD_FONT_TEXT, false)) {
            // Do nothing if the class extends FontText.
            return
        }

        context.report(
                issue = ISSUE,
                scopeClass = declaration,
                location = context.getNameLocation(declaration),
                message = "Extend `$INSTEAD_VIEW` instead."
        )
    }
}