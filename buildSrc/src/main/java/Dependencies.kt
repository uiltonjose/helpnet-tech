object Versions {

    // Android
    const val kotlin                    = "1.3.21"
    const val coroutines                = "1.1.1"
    const val androidxAppCompat         = "1.1.0-alpha04"
    const val androidxCore              = "1.1.0-alpha05"
    const val androidxConstraintLayout  = "1.1.3"
    const val material                  = "1.1.0-alpha05"
    const val leakCanary                = "1.6.3"
    const val kodein                    = "6.1.0"
    const val archComponents            = "1.1.1"

    // Third party
    const val retrofit                  = "2.0.1"
    const val coroutinesAdapter         = "0.9.2"
    const val okHttp                    = "3.2.0"
    const val gson                      = "2.8.2"
    const val glide                     = "3.7.0"
    const val firebaseCore              = "16.0.8"
    const val firebaseAuth              = "16.2.1"
    const val multidex                  = "1.0.3"
    const val betterSpinner             = "1.1.0"
    const val circleImageView           = "2.0.0"

    // Testing
    const val junit                     = "4.12"
    const val instrumentation_runner    = "1.1.1"
    const val instrumentation_rules     = "1.0.2"
    const val espresso                  = "3.0.2"
}

object Libs {
    // Android
    const val androidXAppCompat                 = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
    const val androidXCore                      = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val constraintLayout                  = "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"
    const val material                          = "com.google.android.material:material:${Versions.material}"

    // BetterSpinner
    const val betterSpinner                     = "com.weiwangcn.betterspinner:library-material:${Versions.betterSpinner}"

    // BetterSpinner
    const val circleImageView                     = "de.hdodenhof:circleimageview:${Versions.circleImageView}"

    // Kotlin
    const val kotlin                            = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val androidArchLifecycle              = "android.arch.lifecycle:extensions:${Versions.archComponents}"

    // Dependency Injection
    const val kodein                            = "org.kodein.di:kodein-di-erased-jvm:${Versions.kodein}"

    // Coroutines
    const val coroutinesCore                    = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid                 = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Gson
    const val gson                              = "com.google.code.gson:gson:${Versions.gson}"

    // Retrofit Api
    const val retrofit                          = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter                 = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHttp                            = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLogginInterceptor           = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val retrofitCoroutinesAdapter         = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesAdapter}"

    // Glide
    const val glide                             = "com.github.bumptech.glide:glide:${Versions.glide}"

    // Test
    // Unit
    const val junit                             = "junit:junit:${Versions.junit}"
    // Instrumentation
    const val instrumentationRunner             = "androidx.test:runner:${Versions.instrumentation_runner}"
    const val instrumentationRules              = "com.android.support.test:rules:${Versions.instrumentation_rules}"
    const val espressoCore                      = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoContrib                   = "com.android.support.test.espresso:espresso-contrib:${Versions.espresso}"

    // Leak Canary
    const val leakCanaryDebug                   = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    const val leakCanaryRelease                 = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanary}"

    // Firebase
    const val firebaseCore                      = "com.google.firebase:firebase-core:${Versions.firebaseCore}"
    const val firebaseAuth                      = "com.google.firebase:firebase-auth:${Versions.firebaseAuth}"

    // Multidex
    const val multidex                          = "com.android.support:multidex:${Versions.multidex}"
}
