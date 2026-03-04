# CMP Weather App 🌦️

A Kotlin Multiplatform weather app for Android & iOS, built with Compose Multiplatform.

## Stack
- **UI** — Compose Multiplatform
- **Networking** — Ktor + WeatherAPI
- **DI** — Koin
- **Location & Geocoding** — Compass (Jordond)
- **State** — MVI (ViewModel + StateFlow + sealed commands)
- **Async** — Kotlin Coroutines

## Features
- 🔍 City search with autocomplete
- 📍 Precise device location
- 🌡️ Current temp, feels like, high/low
- 💨 Wind speed & direction
- 💧 Humidity & precipitation
- ☀️ UV index with color scale
- 🌅 Sunrise & sunset times
- 📅 3-day daily forecast
- ⏱️ Hourly forecast scroll
- 🎨 Animated weather background (condition-reactive orbs)

## Architecture
```
composeApp/
├── core/          # Result, DataError, HTTP client, UiText
├── weather/
│   ├── data/      # DTOs, Ktor source, repositories, mappers
│   ├── domain/    # Models, repository interfaces
│   └── presentation/
│       └── weatherInfo/   # ViewModel, State, Commands, Screens
└── di/            # Koin modules (shared + platform)
```

## Setup
1. Get a free API key from [weatherapi.com](https://www.weatherapi.com)
2. Add to `local.properties`:
   ```
   WEATHER_API_KEY=your_key_here
   ```
3. Wire it through `BuildConfig` in your `build.gradle.kts`
4. Run on Android or iOS

## Permissions
- `ACCESS_FINE_LOCATION` — precise location feature
- `ACCESS_COARSE_LOCATION` — fallback location

## TODO
- [ ] Move API key to BuildConfig
- [ ] Settings screen
- [ ] Favorite locations (Room/SQLDelight)
- [ ] Widget support
- [ ] iOS polish

      ![coldweather](https://github.com/user-attachments/assets/e9588561-0437-4f02-8433-1f72236d0db3)![cozy](https://github.com/user-attachments/assets/04c675cd-f38a-41d0-bae0-f420518c46ef)

