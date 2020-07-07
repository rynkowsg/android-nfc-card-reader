// ktlint-disable max-line-length

@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package buildsrc

import org.gradle.api.JavaVersion

object Versions {
    const val JAVA_VERSION_STR = "1.8"
    val JAVA_VERSION = JavaVersion.VERSION_1_8

    const val ktlint = "0.31.0"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.0.0"
    const val dexcountGradlePlugin = "com.getkeepsafe.dexcount:dexcount-gradle-plugin:1.0.2"
    const val versionsGradlePlugin = "com.github.ben-manes:gradle-versions-plugin:0.28.0"
    const val playPublisherPlugin = "com.github.triplet.gradle:play-publisher:2.7.2"
    // https://plugins.gradle.org/plugin/com.diffplug.gradle.spotless
    const val spotlessGradlePlugin = "com.diffplug.spotless:spotless-plugin-gradle:4.3.0"

    // Plugin for publishing artifacts: https://github.com/Codearte/gradle-nexus-staging-plugin
    const val nexusStagingPlugin = "io.codearte.gradle.nexus:gradle-nexus-staging-plugin:0.21.2"

    const val threeTenBp = "org.threeten:threetenbp:1.4.0"
    const val threeTenBpNoTzdb = "$threeTenBp:no-tzdb"
    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:1.2.3"

    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val slf4jApi = "org.slf4j:slf4j-api:1.7.30"
    const val logback = "com.github.tony19:logback-android:2.0.0"

    const val junit = "junit:junit:4.12" // latest: 4.13
    const val robolectric = "org.robolectric:robolectric:4.3.1"
    const val mockito = "org.mockito:mockito-core:3.3.0"
    const val mockitoAndroid = "org.mockito:mockito-android:3.3.2"
    const val mockk = "io.mockk:mockk:1.9.3"
    const val hamcrest = "org.hamcrest:hamcrest-integration:1.3"
    const val assertJ = "org.assertj:assertj-core:3.16.1"

    object Google {
        const val materialObsolete = "com.google.android.material:material:1.0.0"
        const val material = "com.google.android.material:material:1.2.0-alpha04" // latest: 1.2.0-alpha04, 1.1.0-rc02, stable: 1.0.0
        const val playCore = "com.google.android.play:core:1.6.5"

        // Test
        const val truth = "com.google.truth:truth:0.42"
    }

    object Kotlin {
        // 1.3.70 causes a warning breaking tests in this project
        // More: https://discuss.kotlinlang.org/t/classpath-entry-points-to-a-non-existent-location-in-1-3-70/16599
        private const val version = "1.3.72"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val stdlib7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
        const val stdlib8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
        const val testJunit = "org.jetbrains.kotlin:kotlin-test-junit:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    // List of libraries providing immutable collections: https://www.jishuwen.com/d/28FX
    const val kotlinCollectionsImmutable = "org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3"
    const val kotlinCollectionsImmutableJvm = "org.jetbrains.kotlinx:kotlinx-collections-immutable-jvm:0.3"
    const val paguro = "org.organicdesign:Paguro:3.1.2"

    object Coroutines {
        private const val version = "1.3.5"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val rx2 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        const val debug = "org.jetbrains.kotlinx:kotlinx-coroutines-debug:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.1.0" // latest: 1.2.0-alpha03, stable: 1.1.0
        const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0" // latest: 1.2.0-alpha01, stable: 1.1.0
        const val cardview = "androidx.cardview:cardview:1.0.0"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta4"
        const val constraintlayoutStable = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val legacySupportV4 = "androidx.legacy:legacy-support-v4:1.0.0"
        const val coreKtx = "androidx.core:core-ktx:1.3.0" // stable: 1.3.0
        const val annotation = "androidx.annotation:annotation:1.1.0"
        const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"

        object Navigation {
            private const val version = "2.2.1" // latest: 2.3.0-alpha04, stable: 2.2.1
            const val fragment = "androidx.navigation:navigation-fragment:$version"
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Activity {
            private const val version = "1.1.0" // latest: 1.2.0-alpha02, stable: 1.1.0
            const val activity = "androidx.activity:activity:$version"
            const val activityKtx = "androidx.activity:activity-ktx:$version"
        }

        object Fragment {
            private const val version = "1.2.3" // latest: 1.3.0-alpha02, stable: 1.2.3
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Lifecycle {
            private const val version = "2.2.0"
            // ViewModel and LiveData
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val process = "androidx.lifecycle:lifecycle-process:$version"
            // alternatively - just ViewModel
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel:$version"
            // alternatively - just ViewModel (for Kotlin)
            const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            // alternatively - just LiveData
            const val livedata = "androidx.lifecycle:lifecycle-livedata:$version"
            // alternatively - Lifecycles only (no ViewModel or LiveData). Some UI
            //     AndroidX libraries use this lightweight import for Lifecycle
            const val runtime = "androidx.lifecycle:lifecycle-runtime:$version"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            // Annotations compiler (when to use: https://stackoverflow.com/a/58286301/3023806)
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            // alternately - if using Java8, use the following instead of lifecycle-compiler
            const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:$version"
            // optional - ReactiveStreams support for LiveData
            const val reactivestreams = "androidx.lifecycle:lifecycle-reactivestreams:$version"
            // optional - ReactiveStreams support for LiveData (for Kotlin)
            const val reactivestreamsKtx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:$version"
            // optional - Test helpers for LiveData
            const val coreTesting = "androidx.arch.core:core-testing:$version"
        }

        object Test {
            private const val version = "1.2.0" // latest: 1.3.0-alpha02
            // Core library
            const val core = "androidx.test:core:$version"
            // AndroidJUnitRunner and JUnit Rules
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"
            // Assertions
            const val junit = "androidx.test.ext:junit:1.1.1"
            const val truth = "androidx.test.ext:truth:$version"
            // Espresso dependencies
            object Espresso {
                private const val version = "3.2.0" // latest: 3.3.0-alpha05
                const val espressoCore = "androidx.test.espresso:espresso-core:$version"
                const val espressoContrib = "androidx.test.espresso:espresso-contrib:$version"
                const val espressoIntents = "androidx.test.espresso:espresso-intents:$version"
                const val espressoAccessibility = "androidx.test.espresso:espresso-accessibility:$version"
                const val espressoWeb = "androidx.test.espresso:espresso-web:$version"
                const val espressoConcurrent = "androidx.test.espresso:idling-concurrent:$version"
                // The following Espresso dependency can be either "implementation"
                // or "androidTestImplementation", depending on whether you want the
                // dependency to appear on your APK's compile classpath or the test APK
                // classpath.
                const val espressoIdlingResource = "androidx.test.espresso:espresso-idling-resource:$version"
            }
            // UIAutomator
            const val uiautomator = "androidx.test.uiautomator:uiautomator:2.2.0"
            // Orchestrator
            const val orchestrator = "androidx.test:orchestrator:1.2.0" // latest: 1.3.0-alpha05
        }
    }

    object RxJava {
        const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.19"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
        const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
    }

    // dependency injection

    object Dagger {
        private const val version = "2.28"
        const val dagger = "com.google.dagger:dagger:$version"
        const val android = "com.google.dagger:dagger-android:$version"
        const val androidSupport = "com.google.dagger:dagger-android-support:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val androidProcessor = "com.google.dagger:dagger-android-processor:$version"
    }

    const val jsr250Api = "javax.annotation:jsr250-api:1.0" // for Dagger

    // https://github.com/fabioCollini/DaggerMock
    // http://www.albertgao.xyz/2018/04/24/how-to-mock-dagger-android-injection-in-instrumented-tests-with-kotlin/
    // https://medium.com/@fabioCollini/android-testing-using-dagger-2-mockito-and-a-custom-junit-rule-c8487ed01b56
    const val daggerMock = "com.github.fabioCollini.daggermock:daggermock:0.8.5"

    object AssistedInject {
        private const val version = "0.5.2"
        const val annotationDagger2 = "com.squareup.inject:assisted-inject-annotations-dagger2:$version"
        const val processorDagger2 = "com.squareup.inject:assisted-inject-processor-dagger2:$version"
        const val assistedInjectAnnotations = "com.squareup.inject:assisted-inject-annotations:$version"
        const val assistedInjectProcessor = "com.squareup.inject:assisted-inject-processor:$version"
        const val inflationInject = "com.squareup.inject:inflation-inject:$version"
        const val inflationInjectProcessor = "com.squareup.inject:inflation-inject-processor:$version"
    }

    object ButterKnife {
        private const val version = "10.2.1"
        const val butterknife = "com.jakewharton:butterknife:$version"
        const val butterknifeCompiler = "com.jakewharton:butterknife-compiler:$version"
    }

    // networking

    object Retrofit {
        private const val version = "2.8.1"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val retrofitConverters = "com.squareup.retrofit2:retrofit-converters:$version"
        const val adapterRxjava2 = "com.squareup.retrofit2:adapter-rxjava2:$version"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:$version"
        const val converterScalars = "com.squareup.retrofit2:converter-scalars:$version"
    }

    object OkHttp {
        private const val version = "4.5.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    // AirBnB UI frameworks

    // On version bump up, keep in mind about updating `MyBaseMvRxFragment`.
    const val mvrx = "com.airbnb.android:mvrx:1.4.0" // latest: 2.0.0-alpha2

    object Epoxy {
        private const val version = "3.9.0"
        const val epoxy = "com.airbnb.android:epoxy:$version"
        const val paging = "com.airbnb.android:epoxy-paging:$version"
        const val dataBinding = "com.airbnb.android:epoxy-databinding:$version"
        const val processor = "com.airbnb.android:epoxy-processor:$version"
    }

    const val lottie = "com.airbnb.android:lottie:3.3.1"

    // utils

    object Anko {
        private const val version = "0.10.8"
        const val common = "org.jetbrains.anko:anko-common:$version"
        const val coroutines = "org.jetbrains.anko:anko-coroutines:$version"
    }

    object Moshi {
        private const val version = "1.9.2"
        const val moshi = "com.squareup.moshi:moshi:$version"
        const val moshiAdapters = "com.squareup.moshi:moshi-adapters:$version"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$version"
        const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }

    const val gson = "com.google.code.gson:gson:2.8.6"

    object Apache {
        // We can't use the latest due to the crash due to lambda use
        // in src/main/java/org/apache/commons/lang3/Validate.java:
        // https://github.com/apache/commons-lang/commit/374426397
        const val commonsLang3 = "org.apache.commons:commons-lang3:3.9" // latest: 3.10
        const val commonsCollections4 = "org.apache.commons:commons-collections4:4.4"
        const val commonsIo = "commons-io:commons-io:2.6"
    }

    object Guava {
        private const val versionJre = "28.1-jre"
        private const val versionAndroid = "28.1-android"
        const val guava = "com.google.guava:guava:$versionAndroid"
        const val guavaTestlib = "com.google.guava:guava-testlib:$versionAndroid"
    }

    const val zxingCore = "com.google.zxing:core:3.4.0" // 3.3.2 is the last working with zxing-android-embedded:3.6.0
    // zxing-android-embedded
    // In my case, I don't really need it. I used it for Bitmap creation only.
    const val zxingAndroidEmbedded = "com.journeyapps:zxing-android-embedded:3.6.0" // latest: 4.0.2 (but minSdk required is 24)

    object Glide {
        private const val version = "4.10.0"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val glideCompiler = "com.github.bumptech.glide:compiler:$version"
    }

    object Coil {
        private const val version = "0.9.5"
        const val coil = "io.coil-kt:coil:$version"
    }

    const val rxPermissions = "com.github.tbruyelle:rxpermissions:0.10.2"
    // rxpermission uses rxpermissions, but provides a bit simplified API
    const val rxPermission = "com.vanniktech:rxpermission:0.7.0"

    const val sdp = "com.intuit.sdp:sdp-android:1.0.6"

    const val viewpagerDotsIndicator = "com.tbuonomo.andrui:viewpagerdotsindicator:4.1.2"
    const val viewpagerDotsIndicatorObsolete = "com.tbuonomo.andrui:viewpagerdotsindicator:3.0.3"

    const val androidShell = "com.jaredrummler:android-shell:1.0.0"

    const val emvNfcCardLibrary = "com.github.devnied.emvnfccard:library:3.0.1"

    object Stetho {
        private const val version = "1.5.1"
        const val stetho = "com.facebook.stetho:stetho:$version"
        const val stethoOkHttp = "com.facebook.stetho:stetho-okhttp:$version"
        const val stethoOkHttp3 = "com.facebook.stetho:stetho-okhttp3:$version"
        const val stethoUrlConnection = "com.facebook.stetho:stetho-urlconnection:$version"
    }

    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.2"

    // SAAS

    object Aws {
        object Android {
            private const val version = "2.16.0" // versions 2.16.1 to 2.16.4 crashes, latest: 2.16.6
            const val core = "com.amazonaws:aws-android-sdk-core:$version"
            const val iot = "com.amazonaws:aws-android-sdk-iot:$version"
            const val mobileClient = "com.amazonaws:aws-android-sdk-mobile-client:$version"
        }
        object Java {
            private const val version = "1.11.82"
            const val iot = "com.amazonaws:aws-java-sdk-iot:$version"
        }
    }

    object GooglePlayServices {
        const val gradlePlugin = "com.google.gms:google-services:4.3.3"
        const val playServicesBase = "com.google.android.gms:play-services-base:17.1.0"
        const val playServicesAuthApiPhone = "com.google.android.gms:play-services-auth-api-phone:17.3.0"
        const val playServicesAdsIdentifier = "com.google.android.gms:play-services-ads-identifier:17.0.0"
        const val playServicesWallet = "com.google.android.gms:play-services-wallet:18.0.0"
        const val playServicesWalletObsolete = "com.google.android.gms:play-services-wallet:16.0.0"
        const val playServicesMaps = "com.google.android.gms:play-services-maps:17.0.0"
        const val playServicesLocation = "com.google.android.gms:play-services-location:17.0.0"
    }

    object Crashlytics {
        const val gradlePlugin = "io.fabric.tools:gradle:1.31.2"
        const val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.10.1"
        const val crashlyticsNdk = "com.crashlytics.sdk.android:crashlytics-ndk:2.1.1"
    }

    object Firebase {
        const val firebaseCore = "com.google.firebase:firebase-core:17.3.0"
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics:17.3.0"
        const val firebaseAuth = "com.google.firebase:firebase-auth:19.2.0"
        const val firebaseConfig = "com.google.firebase:firebase-config:19.1.2"
        const val firebaseFirestore = "com.google.firebase:firebase-firestore:21.4.1"
        const val firebaseMessaging = "com.google.firebase:firebase-messaging:20.1.1"

        object Crashlytics {
            const val crashlytics = "com.google.firebase:firebase-crashlytics:17.0.0-beta01"
            const val crashlyticsNdk = "com.google.firebase:firebase-crashlytics-ndk:17.0.0-beta01"
            const val gradlePlugin = "com.google.firebase:firebase-crashlytics-gradle:2.0.0-beta01"
        }
    }

    const val stripe = "com.stripe:stripe-android:13.2.0"
    const val stripeObsolete = "com.stripe:stripe-android:10.4.2"

    const val facebook = "com.facebook.android:facebook-android-sdk:6.0.0" // previously: 4.41.0

    // To review:

    // RxDogTag
    // https://github.com/uber/RxDogTag
    // https://www.bugsnag.com/blog/error-handling-on-android-part-4

    // BugSnog
    // https://www.bugsnag.com/
    // https://mvnrepository.com/artifact/com.bugsnag/bugsnag-android/4.22.3
    // https://www.letsenvision.com/blog/effective-logging-in-production-with-firebase-crashlytics
}
