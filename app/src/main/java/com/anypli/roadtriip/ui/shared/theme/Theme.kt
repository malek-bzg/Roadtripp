package com.anypli.roadtriip.ui.shared.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun RoadtriipTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val  RoadTripColors = RoadTripColors(
        black = BLACK ,
        blue = BLUE ,
        red = RED ,
        green = GREEN ,
        grey = GREY ,
        yellow = YELLOW,
        transparent = TRANSPARENT,
        yeloows = YELOOWS,
        yeloowsc =YELOOWSC,
        white= WHITE,
        greyy= GREYY,
        Purple200=PURPLE200,
        Purple500= PURPLE500,
        Purple700 = PURPLE700,
        Teal200 = TEAL200



    )

    CompositionLocalProvider(
        LocalCustomColors provides  RoadTripColors ,

    ) {
        MaterialTheme(
            typography = Typography() ,
            shapes = Shapes() ,
            content = content
        )
    }
}
object RoadtriipTheme {
  val colors: RoadTripColors
       @Composable
        get() = LocalCustomColors.current
    val typography: Typography
        @Composable
        get() = MaterialTheme.typography
    val shapes : Shapes
        @Composable
        get() = MaterialTheme.shapes
        }


private val DarkColorPalette = darkColors(
    primary = PURPLE200,
    primaryVariant = PURPLE700,
    secondary = TEAL200
)

private val LightColorPalette = lightColors(
    primary = PURPLE500,
    primaryVariant = PURPLE700,
    secondary = TEAL200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)
@Composable
fun LearningMapTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }


}






