package com.gdeer.rule

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API

class MyIssueRegistry : IssueRegistry() {

    override val api: Int = CURRENT_API

    override val issues = listOf(TextViewCodeDetector.ISSUE, TextViewXmlDetector.ISSUE)
}