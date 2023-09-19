package com.anypli.roadtriip.ui.shared.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource

import androidx.compose.ui.unit.dp
import com.anypli.roadtriip.R


val Shapes.radiusShape30dp
    @Composable
    get() = RoundedCornerShape(topStart = dimensionResource(R.dimen.margin_global_30dp),
        dimensionResource(R.dimen.margin_global_30dp)
    )
val Shapes.radiusShape20dp
    @Composable
    get() = RoundedCornerShape(topStart = dimensionResource(R.dimen.margin_global_20dp),
        dimensionResource(R.dimen.margin_global_20dp)
    )
val Shapes.radiusShape8dp
    @Composable
    get() = RoundedCornerShape(topStart = dimensionResource(R.dimen.margin_global_8dp),
        dimensionResource(R.dimen.margin_global_8dp)
    )

val Shapes.radiusShape250dp
    @Composable
    get() = RoundedCornerShape(topStart = dimensionResource(R.dimen.dimen_global_250),
        dimensionResource(R.dimen.dimen_global_250)
    )

val Shapes.radiusShape12dp
    @Composable
    get() = RoundedCornerShape(topStart = dimensionResource(R.dimen.margin_global_12dp),
        dimensionResource(R.dimen.margin_global_12dp)
    )
val Shapes.globalOutlinedTextField
    @Composable
    get() = RoundedCornerShape(dimensionResource(R.dimen.margin_global_8dp)
    )

