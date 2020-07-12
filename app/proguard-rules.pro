# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 解决 Please correct the above warnings first 问题
-ignorewarnings

# 解决 Value "a" is not an integer value [proguard.evaluation.value.UnknownReferenceValue] 问题
# https://stackoverflow.com/questions/32185060/android-proguard-failing-with-value-i-is-not-a-reference-value/32615580
-optimizations !class/unboxing/enum

# keep kotlin，解决 fastjson 解析失败问题（default constructor not found）
# 参考 https://github.com/alibaba/fastjson/issues/1752，但里面只 keep 了部分 kotlin，测试下来不够，于是 keep 了整个 kotlin
# 相比于不 keep，包增加了 500k
-keep class kotlin.** { *; }
