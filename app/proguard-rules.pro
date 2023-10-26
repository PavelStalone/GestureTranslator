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
-dontwarn javax.lang.model.**
-dontwarn autovalue.shaded.com.squareup.javapoet.**
-dontwarn javax.annotation.processing.SupportedAnnotationTypes.*
-dontwarn javax.lang.model.SourceVersion.**
-dontwarn javax.lang.model.element.Element.**
-dontwarn javax.lang.model.element.ElementKind.**
-dontwarn javax.lang.model.element.Modifier.**
-dontwarn javax.lang.model.type.TypeMirror.**
-dontwarn javax.lang.model.type.TypeVisito.**
-dontwarn javax.lang.model.util.SimpleTypeVisitor8.**
-dontwarn javax.annotation.processing.AbstractProcessor.**
-dontwarn javax.annotation.processing.**
