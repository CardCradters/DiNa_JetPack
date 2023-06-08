package com.example.dina_compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dina_compose.R

@Composable
fun Categories(times: Int, label: List<String>, selectedIndex: Int, onCategorySelected: (Int) -> Unit)
{
  val icons = listOf(
    painterResource(id = R.drawable.baseline_folder_shared_24),
    painterResource(id = R.drawable.baseline_folder_special_24),
    painterResource(id = R.drawable.baseline_business_24),
  )

  Row(
    modifier = Modifier
      .padding(top = 16.dp)
      .padding(horizontal = 16.dp)
      .fillMaxWidth()
      .height(72.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {

    repeat(times) {index ->
      val isSelected = index == selectedIndex
      val backgroundColor = if (isSelected) Color.DarkGray else Color.LightGray
      val iconColor = if (isSelected) MaterialTheme.colors.primary else Color.DarkGray
        .copy(alpha = ContentAlpha.high)

      Box(
        modifier = Modifier
          .fillMaxHeight()
          .weight(1f)
          .clip(shape = RoundedCornerShape(8.dp))
          .background(color = backgroundColor),
        contentAlignment = Alignment.Center
      ) {
        IconButton(
          onClick = { onCategorySelected(index) },
          modifier = Modifier.fillMaxSize(),
        ) {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Icon(
              painter = icons[index],
              contentDescription = "All",
              modifier = Modifier
                .size(32.dp),
              tint = iconColor
            )
            Text(
              text = label[index],
              color = iconColor
            )
          }
        }
      }
      if (index < times - 1) {
        Spacer(modifier = Modifier.width(8.dp))
      }
    }
  }
}