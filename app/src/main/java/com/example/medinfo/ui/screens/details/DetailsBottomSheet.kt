package com.example.medinfo.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medinfo.R
import com.example.medinfo.ui.theme.textStyle12
import com.example.medinfo.ui.theme.textStyle14
import com.example.medinfo.ui.theme.textStyle16
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyle

@Destination(style = DestinationStyle.BottomSheet::class)
@Composable
fun DetailsBottomSheet() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        BottomSheetIndicator()
        DetailsHeader()
        DetailsContent()
    }
}

@Composable
fun BottomSheetIndicator() {
    Spacer(
        modifier = Modifier
            .padding(vertical = 24.dp)
            .height(4.dp)
            .width(70.dp)
            .background(
                color = Color(0xFFCCCCCC),
                shape = RoundedCornerShape(2.dp)
            )
    )
}

@Composable
fun DetailsHeader() {
    Image(
        painter = painterResource(id = R.drawable.ic_details),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "სთუდენტური თვითმმართველობა",
        style = textStyle16,
        color = Color(0xFF2D2E2B),
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(start = 16.dp).fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "15/01/2024",
        style = textStyle12,
        color = Color(0xFF2D2E2B),
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(start = 16.dp).fillMaxWidth()
    )
}

@Composable
fun DetailsContent() {
    Spacer(
        Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFF028FF6))
    )
    Text(
        text = "თსსუ-ის სტუდენტური თვითმმართველობა თანამშრომლობს როგორც ქვეყანაში, ასევე მის გარეთ  არსებულ სახელმწიფო უწყებებთან, არასამთავრობო ორგანიზაციებთან და საქველმოქმედო ფონდებთან. ჩვენ  ერთად ვახორციელებთ ყველაზე მასშტაბურ და დაუვიწყარ პროექტებს.\n" +
                "ვთვლი, რომ სტუდენტური თვითმმართველობა არის უდიდესი გამოცდილება, აქ შეიძენთ ყველაზე მნიშვნელოვანს ურთიერთობებს და  აგრეთვე ისეთ უნარ-ჩვევებს როგორიცაა თავდაჯერებულობა, გუნდურობა, მიზანსწრაფულობა და ორგანიზებულობა.",
        style = textStyle14,
        lineHeight = 18.sp,
        color = Color(0xFF2D2E2B),
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

}