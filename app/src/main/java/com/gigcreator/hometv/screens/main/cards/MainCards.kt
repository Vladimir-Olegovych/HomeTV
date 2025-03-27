package com.gigcreator.hometv.screens.main.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.gigcreator.domain.pagining.PageItem

@Composable
fun SearchCard(item: PageItem, onClick: (PageItem) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke(item) }
            .padding(16.dp)
            .border(1.dp, Color.White, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
    ) {
        Row(modifier = Modifier.fillMaxWidth().background(Color.Gray)) {
            AsyncImage(
                modifier = Modifier.height(250.dp).width(150.dp).background(Color.Black),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.dataResult.image)
                    //.error(R.drawable.no_image)
                    .build(),
                contentDescription = null,
            )

            Column(
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {
                Text(text = item.searchResult.title, color = Color.White, fontSize = 20.sp)
                Text(text = "Date: ${item.searchResult.date}", color = Color.White, fontSize = 16.sp)
                Text(
                    modifier = Modifier,
                    text = "Source: ${item.sourceName}",
                    color = Color.White,
                    fontSize = 16.sp,
                )
                Text(text = item.dataResult.description ?: return@Column, color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
