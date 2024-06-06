package com.example.pizzeria.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pizzeria.nav.Screen
import com.example.pizzeria.ui.theme.red

@Composable
fun ListContentProduct(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Popular Items",
                fontWeight = FontWeight.W300,
                fontSize = 15.sp,
                letterSpacing = 1.sp,
                color = Black
            )
            TextButton(onClick = {

            },
            ) {
                Text(text = "All",
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = Black)
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            contentPadding = PaddingValues(5.dp)
        ){
            items(8){
                ProductCard()
            }
        }
    }
}