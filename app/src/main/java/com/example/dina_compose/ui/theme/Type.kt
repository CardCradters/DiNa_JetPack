package com.example.dina_compose.ui.theme
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.dina_compose.R

val MyFontFamily = FontFamily(
//  Font(R.font.poppins_bold, FontWeight.Bold),
//  Font(R.font.poppins_semibold, FontWeight.SemiBold),
//  Font(R.font.poppins_medium, FontWeight.Medium),
//  Font(R.font.poppins_regular, FontWeight.Normal),
  Font(R.font.inter_bold, FontWeight.Bold),
  Font(R.font.inter_semibold, FontWeight.SemiBold),
  Font(R.font.inter_medium, FontWeight.Medium),
  Font(R.font.inter_regular, FontWeight.Normal)
)

// Set of Material typography styles to start with
val MyTypography = Typography(
  defaultFontFamily = MyFontFamily, // Default Fot Set
  h1 = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    letterSpacing = 0.02.em,
  ),
  h2 = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp,
    letterSpacing = 0.04.em
  ),
  subtitle1 = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    letterSpacing = 0.04.em
  ),
  subtitle2 = TextStyle(
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    letterSpacing = 0.06.em
  ),
  body1 = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
  ),
)

@Composable
fun PreviewText()
{
  Column {
    Text(text = "Hello, World!", style = MyTypography.h1)
    Text(text = "Hello, World!", style = MyTypography.h2)
    Text(text = "Hello, World!", style = MyTypography.subtitle1)
    Text(text = "Hello, World!", style = MyTypography.subtitle2)
    Text(text = "Hello, World!", style = MyTypography.body1)
  }

}

@Preview
@Composable
fun TextPreview()
{
  PreviewText()
}