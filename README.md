# 🎓 MatrixMarket — Peer-to-Peer Student Marketplace

![Android CI](https://github.com/MatrixMarket/MatrixMarket/actions/workflows/ci.yml/badge.svg)
![Platform](https://img.shields.io/badge/Platform-Android%20%7C%20Jetpack%20Compose-green?logo=android)
![Backend](https://img.shields.io/badge/Backend-.NET%208.0%20Web%20API-512BD4?logo=dotnet)
![Database](https://img.shields.io/badge/Database-SQLite%20%2F%20EF%20Core-003B57?logo=sqlite)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-7F52FF?logo=kotlin)
![License](https://img.shields.io/badge/License-MIT-blue)

**MatrixMarket** is a secure, campus-verified peer-to-peer mobile marketplace engineered specifically for tertiary students. It enables students to buy, sell, and trade textbooks, electronics, residence supplies, and tutoring services using verified student accounts, on-campus trade meetups, and a gamified trust and karma system.

---

## 👨‍💻 Developer

This project was engineered and developed by:

**Caleb Naidoo** — ST10084625

---

## 🛠️ Tech Stack & Architecture

### **Android Mobile Client**
* **Language:** Kotlin
* **UI Framework:** Jetpack Compose (Material Design 3)
* **Architecture Pattern:** MVVM (Model-View-ViewModel) + Repository Pattern
* **Networking:** Retrofit2 + OkHttp3 + Kotlinx Serialization
* **Local Persistence & Auth:** Encrypted `SessionManager` (Tokens & Preferences)
* **Hardware Integration:** CameraX & Google ML Kit (Barcode / ISBN Scanner)
* **Localization:** Multi-language support (English, Afrikaans `values-af`, isiZulu `values-zu`)

### **Backend Service**
* **Framework:** ASP.NET Core 8.0 REST Web API
* **ORM:** Entity Framework Core 8.0
* **Database:** SQLite (`matrixmarket.db`)
* **Security:** JWT Bearer Token Authentication & BCrypt Password Hashing
* **Documentation:** OpenAPI / Swashbuckle Swagger UI

---

## 🏗️ System Architecture & Folder Structure

```text
MatrixMarket-main/
├── .github/
│   └── workflows/
│       └── ci.yml                   # CI/CD GitHub Actions Pipeline
├── android/                         # Android Native Application
│   ├── app/
│   │   ├── build.gradle.kts         # Android Dependencies & App Config
│   │   └── src/
│   │       ├── main/
│   │       │   ├── java/com/matrixmarket/app/
│   │       │   │   ├── auth/        # Google Auth Manager Integration
│   │       │   │   ├── data/        # ApiService, RetrofitClient, DTOs & Repositories
│   │       │   │   ├── navigation/  # Jetpack Compose NavGraph Routing
│   │       │   │   ├── ui/          # Compose Screens, ViewModels, Theme & Components
│   │       │   │   └── util/        # AppContainer, Resource Wrapper, ViewModelFactory
│   │       │   └── res/             # Strings (EN, AF, ZU), Drawables & Themes
│   │       └── test/                # JUnit Unit Tests for ViewModels
├── backend/
│   └── MatrixMarket.Api/            # ASP.NET Core Web API Project
│       ├── Controllers/             # Auth, Listings, Offers, Trades, Users, Categories
│       ├── Data/                    # AppDbContext & DbSeeder
│       ├── DTOs/                    # Request & Response Contracts
│       ├── Models/                  # EF Core Database Entities
│       ├── Services/                # KarmaService & TokenService
│       ├── Dockerfile               # Backend Containerization Spec
│       └── Program.cs               # Middleware & Dependency Injection Setup
└── docs/                            # Design & Planning Documentation
```

---

## ⚡ Key Features

* **Verified Student Onboarding:** Registration restricted to academic email domains, integrated with JWT token authentication.
* **Smart Catalog & ISBN Barcode Scanner:** Real-time search, filter, and barcode scanning using CameraX and Google ML Kit to automatically pre-fill textbook metadata.
* **Campus Safety Trade Meetings:** Secure, on-campus exchange scheduling with 6-digit confirmation codes to verify physical handoffs.
* **Student Karma & Trust Badges:** Algorithmic reputation system awarding karma points and trust badges (e.g., Top Seller, Verified Student, Trusted Trader) upon successful trades.
* **Multi-Language Support:** Fully localized interface supporting English, Afrikaans (af), and isiZulu (zu).

---

## 🚀 Running MatrixMarket on Android & Local Machine

### 1. Backend Service Setup (.NET 8 Web API)

**Prerequisites**
* .NET 8.0 SDK installed.

**Steps**

Navigate to the backend directory:

```bash
cd backend/MatrixMarket.Api
```

Restore dependencies and run the server:

```bash
dotnet restore
dotnet run
```

The API will start on `http://localhost:5000` or `https://localhost:5001`. You can open Swagger documentation in your browser at:

```text
http://localhost:5000/swagger
```

### 2. Android Client Setup (Android Studio & Physical Device / Emulator)

**Prerequisites**
* Android Studio Jellyfish / Ladybug or newer
* Android SDK 34+
* An Android physical device (e.g., Xiaomi Redmi) or Android Emulator running API Level 26+

**Configuring Local IP for Physical Testing**

When running the Android application on a physical device, update the API base URL to match your development machine's local IP address.

Find your network IP address on your host machine:
* **Windows:** Run `ipconfig` in CMD or PowerShell.
* **macOS/Linux:** Run `ifconfig` or `ip a` in terminal.

Open `android/app/src/main/java/com/matrixmarket/app/data/remote/RetrofitClient.kt`.

Update `BASE_URL`:

```kotlin
// For physical Android device testing (e.g., Xiaomi Redmi)
private const val BASE_URL = "http://<YOUR_LOCAL_IP>:5000/"

// For Android Emulator
// private const val BASE_URL = "http://10.0.2.2:5000/"
```

**Building & Running the App**

1. Open the `android` folder in Android Studio.
2. Sync Gradle files (`File -> Sync Project with Gradle Files`).
3. Connect your Android physical device via USB debugging or start an emulator.
4. Select the app run configuration and click Run (`Shift + F10`).

---

## 🧪 Running Unit Tests

**Android Unit Tests (JUnit)**

To run the ViewModel unit tests for the Android application:

```bash
cd android
./gradlew test
```

**Backend Tests**

To run tests across the .NET backend solution:

```bash
cd backend
dotnet test
```

---

## 🔄 GitHub Actions CI/CD Pipeline

MatrixMarket uses GitHub Actions (`.github/workflows/ci.yml`) to automatically build and verify code quality on every push and pull request:

* Builds the .NET 8 Web API backend.
* Runs Kotlin Gradle unit tests for the Android project.
* Verifies compilation and assembly for both backend and mobile layers.

---

## 📄 License

This project is released under the MIT License.

---

## Repository setup note

Add your own `android/app/google-services.json` from the Firebase console before building the Android app. It is kept out of the repository on purpose.
