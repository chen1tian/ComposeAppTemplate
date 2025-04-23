plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.welldone.composeapptemplate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.welldone.composeapptemplate"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // AutoSize
    implementation("com.github.JessYanCoding:AndroidAutoSize:v1.2.1")
    // 导航
    implementation(libs.navigation.compose)
    // viewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose.android)
    // 依赖注入
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")
    // Hilt Navigation Compose 依赖注入 使用Nav导航用此包进行注入(hiltViewModel)
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    // json
    implementation("com.google.code.gson:gson:2.8.8")
    // datastore-preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    // room ktx扩展
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    // 日志
    implementation("com.jakewharton.timber:timber:5.0.1")
    // 截图
    implementation("dev.shreyaspatil:capturable:2.1.0")
    // 表单
    implementation("com.github.benjamin-luescher:compose-form:0.2.8")
}