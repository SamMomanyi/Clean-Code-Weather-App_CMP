import org.jetbrains.compose.resources.DrawableResource
import cmp_weatherapp.composeapp.generated.resources.Res
// Ensure these match your actual file names in commonMain/composeResources/drawable
import cmp_weatherapp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource

fun iconMapper(
    iconCode: String,
    isDay: Boolean = true
): DrawableResource {
    return when (iconCode) {
        "0", "1" -> if (isDay) Res.drawable.WBSunny else Res.drawable.PartlyCloudyNight // Fallback to night icon if sun isn't out
        "2" -> if (isDay) Res.drawable.PartlyCloudyDay else Res.drawable.PartlyCloudyNight
        "3" -> Res.drawable.Cloud // Your "fully cloudy" icon
        "45", "48" -> Res.drawable.Foggy
        "51", "53", "55", "61", "63", "65" -> Res.drawable.Rainy
        "71", "73", "75", "77" -> Res.drawable.Snowy
        "95", "96", "99" -> Res.drawable.Rainy // Or Thunderstorm if you have it
        else -> Res.drawable.Unkown // Fallback for errors
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