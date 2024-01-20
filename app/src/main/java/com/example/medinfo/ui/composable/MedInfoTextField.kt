package com.example.medinfo.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medinfo.R
import com.example.medinfo.ui.theme.textStyle14

@Composable
fun MedInfoTextField(
    search: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = Modifier.textFieldModifier(),
        value = search,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        cursorBrush = SolidColor(Color(0xFF2D2E2B)),
        textStyle = textStyle14,
        decorationBox = { TextFieldContent(search, it) }
    )
}

@Composable
fun TextFieldContent(
    text: String,
    innerTextField: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.textFieldContentModifier(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.weight(1f)) {
            val visible = text.isEmpty()
            if (visible) SearchPlaceHolder()
            innerTextField()
        }
        SearchIcon()
    }
}

@Composable
fun SearchPlaceHolder() {
    Text(
        text = "ძიება",
        style = textStyle14,
        color = Color(0XFF028FF6)
    )
}

@Composable
fun SearchIcon() {
    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
        tint = Color(0XFF028FF6),
        contentDescription = "",
        modifier = Modifier.requiredSize(22.dp)
    )
}

fun Modifier.textFieldModifier() =
    padding(textFieldPadding)
        .height(34.dp)
        .fillMaxWidth()
        .background(Color.White)
        .textFieldBorder()


@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.textFieldContentModifier() =
    fillMaxWidth().padding(horizontal = 8.dp)

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.textFieldBorder() =
    border(
        width = 1.dp,
        color = Color(0xFF028FF6),
        shape = RoundedCornerShape(6.dp),
    )

val textFieldPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 16.dp)

@Preview
@Composable
fun MedInfoTextFieldPreview() {
    MedInfoTextField(
        "", {}
    )
}