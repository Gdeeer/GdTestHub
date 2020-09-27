package com.gdeer.rule

import com.android.tools.lint.detector.api.*
import org.w3c.dom.Element

/**
 * Detector: 可以在 lint 中使用
 * LayoutDetector：可以检查 layout 文件
 */
class TextViewXmlDetector : LayoutDetector() {
    companion object {
        val ISSUE = Issue.create(
                id = "TextViewXmlDetector",
                briefDescription = "Detects incorrect TextView usages",
                explanation = "Detects incorrect TextView usages",
                implementation = Implementation(TextViewXmlDetector::class.java, Scope.RESOURCE_FILE_SCOPE),
                priority = 9,
                category = Category.CORRECTNESS,
                severity = Severity.ERROR
        )

        private const val TEXT_VIEW = "android.widget.TextView"
        private const val EDIT_TEXT = "android.widget.EditText"
        private const val BUTTON = "android.widget.Button"
        private val listOriginView = mutableListOf(
                TEXT_VIEW, EDIT_TEXT, BUTTON
        ).apply {
            // android.widget.TextView -> TextView
            val simpleList = mutableListOf<String>()
            mapTo(simpleList) {
                it.substringAfterLast(".")
            }
            addAll(simpleList)
        }

        private const val INSTEAD_VIEW = "FontText、FontEditText or their child view"
    }

    override fun getApplicableElements() = listOriginView

    override fun visitElement(context: XmlContext, element: Element) {
        context.report(
                issue = ISSUE,
                scope = element,
                location = context.getNameLocation(element),
                message = "Use `$INSTEAD_VIEW` instead."
        )
    }
}