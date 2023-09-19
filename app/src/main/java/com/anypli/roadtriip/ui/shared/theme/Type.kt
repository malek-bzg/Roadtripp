@file:Suppress("unused")

package com.anypli.roadtriip.ui.shared.theme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Typography

import androidx.compose.ui.unit.sp
import com.anypli.roadtriip.R
import com.anypli.roadtriip.global.utilities.fontDimensionResource


val elmessri = FontFamily(
listOf(
Font(R.font.elmessiri_regular, FontWeight.Normal) ,
Font(R.font.elmessiri_medium, FontWeight.Medium),
Font(R.font.elmessiri_semibold, FontWeight.SemiBold),
Font(R.font.elmessiri_bold, FontWeight.Bold),
)
)
val Typography.fontSize16spBLack
    @Composable
    get() = TextStyle(
        color = RoadtriipTheme.colors.black ,
        fontFamily = elmessri ,
        fontSize = fontDimensionResource(id = R.dimen.font_16sp)
    )

val Typography.bold16spBLack
    @Composable
    get() = fontSize16spBLack.copy(
        fontWeight = FontWeight.Bold
    )

val Typography.normal16spBLack
    @Composable
    get() = fontSize16spBLack.copy(
        fontWeight = FontWeight.Bold
    )
//LabelMedium
val Typography.fontSize12spBLack
    @Composable
    get() = TextStyle(
        color = RoadtriipTheme.colors.black ,
        fontFamily = elmessri ,
        fontSize = fontDimensionResource(id = R.dimen.font_12sp)
    )
val Typography.fontSize18spBLack
    @Composable
    get() = TextStyle(
        color = RoadtriipTheme.colors.black ,
        fontFamily = elmessri ,
        fontSize = fontDimensionResource(id = R.dimen.font_18sp)
    )

val Typography.fontSize20spBLack
    @Composable
    get() = TextStyle(
        color = RoadtriipTheme.colors.black ,
        fontFamily = elmessri ,
        fontSize = fontDimensionResource(id = R.dimen.font_20sp)
    )
//headlineSmall
val Typography.fontSize24spBLack
    @Composable
    get() = TextStyle(
        color = RoadtriipTheme.colors.black ,
        fontFamily = elmessri ,
        fontSize = fontDimensionResource(id = R.dimen.font_24sp)
    )
//LabelLarge
val Typography.fontSize14spBLack
    @Composable
    get() = TextStyle(
        color = RoadtriipTheme.colors.black ,
        fontFamily = elmessri ,
        fontSize = fontDimensionResource(id = R.dimen.font_12sp)
    )
val Typography.fontSize15spBLack
    @Composable
    get() = TextStyle(
        color = RoadtriipTheme.colors.black ,
        fontFamily = elmessri ,
        fontSize = fontDimensionResource(id = R.dimen.font_15sp)
    )

val Typography.globalfontSize14spBLack
    @Composable
    get() = TextStyle(
        color = RoadtriipTheme.colors.black ,
        fontFamily = elmessri ,
        fontSize = fontDimensionResource(id = R.dimen.font_14sp)
    )

