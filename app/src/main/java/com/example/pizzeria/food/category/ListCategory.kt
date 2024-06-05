package com.example.pizzeria.food.category

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pizzeria.ui.theme.red
import com.example.pizzeria.ui.theme.yellow1
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.pizzeria.R
import com.example.pizzeria.food.models.Categories
import com.example.pizzeria.food.sealed.DataState
import com.example.pizzeria.food.viewmodels.CategoryViewModel
import com.example.pizzeria.ui.theme.bottomBorder
import com.example.pizzeria.ui.theme.yellow3

@Composable
fun SetDataCategories(viewModel: CategoryViewModel){
    when (val result = viewModel.response.value) {
        is DataState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is DataState.Success -> {
            ShowListCate(result.data)
        }
        is DataState.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = result.message,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                )
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error Fetching data",
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                )
            }
        }
    }
}

@Composable
fun ShowListCate(category: MutableList<Categories>){

    LazyRow(
        contentPadding = PaddingValues(horizontal = 6.dp, vertical = 8.dp),
    ) {
        items(category){ categories ->
            ListCategory(categories)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCategory(categories: Categories){
    Surface(
        onClick = { },
        shape = RoundedCornerShape(42.dp),
        color = yellow1,
        modifier = Modifier
            .width(130.dp)
            .height(50.dp)
            .padding(end = 10.dp)
            .background(MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(42.dp))
            .padding(bottom = 1.dp)
        ,
        shadowElevation = 10.dp,
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxHeight()
                    .size(42.dp),
                color = Color(0xFFF5F0E4)
            ) {
                Image(
                    painter = rememberImagePainter(categories.categoryImage),
                    contentDescription = "",
                    modifier = Modifier.padding(3.dp)
//                        contentScale = ContentScale.Crop,
                )
            }
            Text(
                text = categories.categoryName!!,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

//
//@Preview()
//@Composable
//fun previewListCate(){
//    ListCate()
//}