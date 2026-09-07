# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# signingConfigs.debug.proguardFiles and signingConfigs.release.proguardFiles
# in build.gradle.

-keepattributes *Annotation*
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# Room
-keep class * extends androidx.room.RoomDatabase
-keepclassmembers class * extends androidx.room.RoomDatabase { *** create*(...); }