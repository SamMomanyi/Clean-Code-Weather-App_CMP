import org.jetbrains.compose.resources.DrawableResource
import cmp_weatherapp.composeapp.generated.resources.Res
// Ensure these match your actual file names in commonMain/composeResources/drawable
import cmp_weatherapp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource

fun IconMapper(
    iconCode: String,
    isDay: Boolean = true
): DrawableResource {
    // WeatherAPI codes are 1000, 1003, 1063, etc.
    return when (iconCode) {
        // Sunny / Clear
        "1000" -> {
            if (isDay) Res.drawable.sunny_day else Res.drawable.partly_cloudy_night
        }

        // Partly Cloudy
        "1003" -> {
            if (isDay) Res.drawable.partly_cloudy_day else Res.drawable.partly_cloudy_night
        }

        // Cloudy & Overcast
        "1006", "1009" -> Res.drawable.cloud

        // Mist & Fog
        "1030", "1135", "1147" -> Res.drawable.foggy

        // Rain (Patchy rain, Light rain, Heavy rain, Showers)
        "1063", "1180", "1183", "1186", "1189", "1192", "1195",
        "1240", "1243", "1246", "1273", "1276" -> Res.drawable.rainy_day

        // Snow (Patchy snow, Blizzards, Ice pellets)
        "1066", "1114", "1117", "1210", "1213", "1216", "1219",
        "1222", "1225", "1279", "1282" -> Res.drawable.snowy_day

        // Thunderstorms (Mapped to Rain if you don't have a storm icon)
        "1087" -> Res.drawable.rainy_day

        // Sleet / Freezing Rain
        "1069", "1072", "1168", "1171", "1198", "1201", "1204", "1207",
        "1237", "1249", "1252", "1255", "1258", "1261", "1264" -> Res.drawable.rainy_day

        else -> Res.drawable.unkown_day
    }
}

/**
 * Maps WMO codes to the localized string resource.
 *
 */


fun DescriptionMapper(
    iconCode: String
): StringResource {
    // WeatherAPI codes (1000+)
    return when (iconCode) {
        // Sunny / Clear
        "1000" -> Res.string.sunny

        // Partly Cloudy
        "1003" -> Res.string.partly_cloudy

        // Cloudy & Overcast
        "1006", "1009" -> Res.string.cloudy

        // Mist & Fog
        "1030", "1135", "1147" -> Res.string.foggy

        // Rain (Drizzle, Light, Moderate, Heavy, Showers)
        "1063", "1150", "1153", "1180", "1183", "1186", "1189", "1192", "1195",
        "1240", "1243", "1246", "1168", "1171" -> Res.string.rainy

        // Snow (Snow, Blizzard, Ice Pellets, Sleet)
        "1066", "1069", "1072", "1114", "1117", "1204", "1207",
        "1210", "1213", "1216", "1219", "1222", "1225",
        "1237", "1249", "1252", "1255", "1258", "1261", "1264" -> Res.string.snowy

        // Thunderstorms
        "1087", "1273", "1276", "1279", "1282" -> Res.string.thunderstorm

        else -> Res.string.unknown
    }
}