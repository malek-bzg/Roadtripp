package com.anypli.roadtriip.ui.shared.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import com.anypli.roadtriip.R
import com.anypli.roadtriip.global.utilities.fontDimensionResource
import com.anypli.roadtriip.ui.shared.theme.RoadtriipTheme
import com.anypli.roadtriip.ui.shared.theme.fontSize12spBLack
import com.anypli.roadtriip.ui.shared.theme.globalOutlinedTextField
import com.anypli.roadtriip.ui.shared.theme.globalfontSize14spBLack
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.remember


@Composable
fun GradientButton(
    gradientColors: List<Color> ,
    cornerRadius: Dp ,
    nameButton: String ,
    roundedCornerShape: RoundedCornerShape,
    onClick: () -> Unit,
    errorMessage: String? = null
) {

    androidx.compose.material3.Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(R.dimen.margin_global_32dp) ,
                end = dimensionResource(R.dimen.margin_global_32dp)
            ),
        onClick = onClick,

        contentPadding = PaddingValues() ,
        colors = ButtonDefaults.buttonColors(
            containerColor = RoadtriipTheme.colors.transparent
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors) ,
                    shape = roundedCornerShape
                )
                .clip(roundedCornerShape)
                /*.background(
                    brush = Brush.linearGradient(colors = gradientColors),
                    shape = RoundedCornerShape(cornerRadius)
                )*/
                .padding(
                    horizontal = dimensionResource(R.dimen.margin_global_16dp) ,
                    vertical = dimensionResource(R.dimen.margin_global_8dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = nameButton,
                fontSize = fontDimensionResource(R.dimen.font_20sp) ,
                color = RoadtriipTheme.colors.white

            )
        }
    }
}
@Composable
 fun GradientButtonReset(
    gradientColors: List<Color>,
    cornerRadius: Dp,
    nameButton: String,
    roundedCornerShape: RoundedCornerShape,
    onClick: () -> Unit,

) {
    androidx.compose.material3.Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(R.dimen.margin_global_32dp) ,
                end = dimensionResource(R.dimen.margin_global_32dp)
            ),
        onClick = onClick,
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = RoadtriipTheme.colors.transparent
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors) ,
                    shape = roundedCornerShape
                )
                .clip(roundedCornerShape)
                .padding(
                    horizontal = dimensionResource(R.dimen.margin_global_16dp) ,
                    vertical = dimensionResource(R.dimen.margin_global_8dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.material3.Text(
                text = nameButton,
                fontSize = fontDimensionResource(R.dimen.font_20sp),
                color = RoadtriipTheme.colors.white
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlobalOutlinedTextField(
    value: String ,
    onValueChange: (String) -> Unit ,
    label: String,
    placeholder: String? = null ,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default ,
    singleLine: Boolean = true ,
    errorMessage: String? = null,

    


    ) {
    val isValidEmail = remember(value) { isValidEmail(value) }
   // val isValidDate = remember(value) { isValidDate(value) }


    Column {
        OutlinedTextField(
            value = value ,
            onValueChange = {
                onValueChange(it)
            },
            label = {
                Text(
                    label ,
                    color = RoadtriipTheme.colors.black ,
                    style = RoadtriipTheme.typography.fontSize12spBLack ,
                )
            } ,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = RoadtriipTheme.colors.black ,
                unfocusedBorderColor = RoadtriipTheme.colors.black
            ) ,
            placeholder = placeholder?.let { { Text(text = placeholder) } } ,
            keyboardOptions = keyboardOptions ,
            singleLine = singleLine ,
            textStyle = TextStyle(fontSize = fontDimensionResource(id = R.dimen.font_14sp)) ,
            shape = RoadtriipTheme.shapes.globalOutlinedTextField ,

            )
        if (isValidEmail.not() && errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = TextStyle(fontSize = fontDimensionResource(id = R.dimen.font_12sp))
            )
        }
//        if (!isValidDate && errorMessage != null) { // Utilisation de isValidDate
//            Text(
//                text = errorMessage,
//                color = Color.Red,
//                style = TextStyle(fontSize = fontDimensionResource(id = R.dimen.font_12sp))
//            )
//        }


    }
}
private fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex(pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
    return emailRegex.matches(email)
}
//private fun isValidDate(date: String): Boolean {
//    val dateRegex = Regex(pattern = "^\\d{4}-\\d{2}-\\d{2}\$")
//    return dateRegex.matches(date)
//}





    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun GlobalPasswordOutlinedTextField(
        value: String ,
        onValueChange: (String) -> Unit ,
        label: String ,
        placeholder: String? = null ,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default ,
        singleLine: Boolean = true ,
        passwordHidden: Boolean = true ,
        onPasswordHiddenChange: (Boolean) -> Unit ,
        errorPass: String? = null
    ) {
        val visualTransformation =
            if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None
        Column {
            OutlinedTextField(
                value = value ,
                onValueChange = onValueChange ,
                label = {
                    Text(
                        label ,
                        color = RoadtriipTheme.colors.black ,
                        style = RoadtriipTheme.typography.fontSize12spBLack ,
                    )
                } ,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = RoadtriipTheme.colors.black ,
                    unfocusedBorderColor = RoadtriipTheme.colors.black
                ) ,
                placeholder = placeholder?.let { { Text(text = placeholder) } } ,
                keyboardOptions = keyboardOptions ,
                singleLine = singleLine ,
                textStyle = RoadtriipTheme.typography.globalfontSize14spBLack ,
                shape = RoadtriipTheme.shapes.globalOutlinedTextField ,
                visualTransformation = visualTransformation ,


                trailingIcon = {
                    IconButton(onClick = { onPasswordHiddenChange(!passwordHidden) }) {
                        val visibilityIcon =
                            if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description =
                            if (passwordHidden) stringResource(R.string.show_password) else stringResource(
                                R.string.hide_password
                            )
                        Icon(
                            imageVector = visibilityIcon ,
                            contentDescription = description
                        )


                    }
                }
            )
            errorPass?.let { error ->
                Text(
                    text = error,
                    color = Color.Red,
                    style = TextStyle(fontSize = fontDimensionResource(id = R.dimen.font_12sp))
                )
            }
        }
    }



