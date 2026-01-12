import org.jetbrains.compose.resources.DrawableResource
import cmp_weatherapp.composeapp.generated.resources.Res
// Ensure these match your actual file names in commonMain/composeResources/drawable
import cmp_weatherapp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource

fun IconMapper(
    iconCode: String,
    isDay: Boolean = true
): DrawableResource {
    return when (iconCode) {
        "0", "1" -> if (isDay) Res.drawable.sunny_day else Res.drawable.partly_cloudy_night // Fallback to night icon if sun isn't out
        "2" -> if (isDay) Res.drawable.partly_cloudy_day else Res.drawable.partly_cloudy_night
        "3" -> Res.drawable.cloud // Your "fully cloudy" icon
        "45", "48" -> Res.drawable.foggy
        "51", "53", "55", "61", "63", "65" -> Res.drawable.rainy_day
        "71", "73", "75", "77" -> Res.drawable.snowy_day
        "95", "96", "99" -> Res.drawable.rainy_day // Or Thunderstorm if you have it
        else -> Res.drawable.unkown_day // Fallback for errors
    }
}

/**
 * Maps WMO codes to the localized string resource.
 *
 */
fun DescriptionMapper(
    iconCode: String
): StringResource {
    return when (iconCode) {
        "0", "1" -> Res.string.sunny
        "2" -> Res.string.partly_cloudy
        "3" -> Res.string.cloudy
        "45", "48" -> Res.string.foggy
        "51", "53", "55", "61", "63", "65" -> Res.string.rainy
        "71", "73", "75", "77" -> Res.string.snowy
        "95", "96", "99" -> Res.string.thunderstorm
        else -> Res.string.unknown
    }
}