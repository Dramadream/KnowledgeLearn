#-------------------------------------------基本不变区域---------------------------------------------
#-------------------------------------------  基本指令区---------------------------------------------
#指定代码的压缩级别 0 - 7
-optimizationpasses 7
#不使用大小写混合
-dontusemixedcaseclassnames
#表示不跳过library中的非public的类。
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
#表示不进行优化，建议使用此选项，因为根据proguard-android-optimize.txt中的描述，优化可能会造成一些潜在风险
-dontoptimize
# 表示不进行预校验。这个预校验是作用在Java平台上的，Android平台上不需要这项功能，去掉之后还可以加快混淆速度。
-dontpreverify

 # 混淆时所采用的算法
-optimizations !code/simplification/cast,!field/*,!class/merging/*
#表示对注解（内部类？）中的参数进行保留。
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable

#----------------------  记录生成的日志数据,gradle build时在本项目根目录输出---------------------------
 #混淆时是否记录日志
-verbose
#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射，热更新时比较重要的数据
-printmapping proguardMapping.txt
#---------------------  记录生成的日志数据，gradle build时 在本项目根目录输出-end ---------------------

# 编译时可能会报错，添加这个就不会报错
-ignorewarnings

#----------------------------------------- 默认保留区 -----------------------------------------------
#不混淆四大组件，Fragment，V4，V7，注解 等相关类
-keep public class * extends android.view.View
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.preference.Preference
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.annotation.**
-keep public class * extends android.support.v7.**

-keep class android.support.** {*;}
-keepattributes *Annotation*

#表示不混淆下面的两个类，这两个类我们基本也用不上，是接入Google原生的一些服务时使用的。
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

#表示不混淆任何包含native方法的类的类名以及native方法名
-keepclasseswithmembernames class * {
    native <methods>;
}
# onClick方法不能被混淆，保证在XML中的onClick=XXXX正常
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
#枚举不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#表示不混淆任何一个View中的set和get方法，因为属性动画需要有相应的setter和getter的方法实现，混淆了就无法工作了。
#同样不混淆自定义的View，
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
# Parcelable和Serializable序列化数据不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
# 不混淆R文件
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}
#----------------------------------------------------------------------------

#--------------------------------- webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
#----------------------------------------------------------------------------
#-------------------------------------------定制化区域----------------------------------------------
#================== 1.实体类，涉及到与服务端的交互等，也就是Bean类或者Model相关类 ======================
# 本文件中放到了Gson的混淆规则中

#======================================= 2.与js互相调用的类 =========================================
#--- 接口
#-keep class com.burning.iplay.ui.detail.** {*;}


#======================================= 3.反射相关的类和方法 =======================================

#----  自己使用反射用到的类和方法
#============================================ 4.第三方包    =========================================
#--------------------------------------- OKHttp3----------------------------------------------------
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
#------------------------------------ Retrofit 2 ---------------------------------------------------
-keepattributes Signature
-keepclassmembernames,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

#-------------------------------------RxJava2  RxAndroid--------------------------------------------
#-------------------------------------RxJava2 与 Retrofit 的适配-------------------------------------
# rx内部已有混淆

#--------------------------------------  Gson   -------------------- -------------------------------
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson 下面替换成自己的实体类
-keep class com.fkw.knowledge.data.** { *; }

#-------------------------------------------  Glide ------------------------------------------------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#-------------------------------------------  GreenDao  --------------------------------------------
-keep class org.greenrobot.greendao.**{*;}
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties
-keep class com.fkw.knowledge.db.bean.** { *; }
-keep class com.fkw.knowledge.db.gen.** { *; }
#-------------------------------------------  EventBus  --------------------------------------------

-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

