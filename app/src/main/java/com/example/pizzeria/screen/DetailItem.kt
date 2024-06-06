package com.example.pizzeria.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.example.pizzeria.R
import com.example.pizzeria.ui.theme.AppBarCollapsedHeight
import com.example.pizzeria.ui.theme.AppBarExpendedHeight
import com.example.pizzeria.ui.theme.Shapes
import com.example.pizzeria.ui.theme.blackcart
import com.example.pizzeria.ui.theme.grayFont
import com.example.pizzeria.ui.theme.lightGray
import com.example.pizzeria.ui.theme.red
import com.example.pizzeria.ui.theme.redlight
import com.example.pizzeria.ui.theme.yellow1
import com.example.pizzeria.ui.theme.yellow2
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import kotlin.math.max
import kotlin.math.min


@SuppressLint("SuspiciousIndentation, UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailItem(){
    val scrollState = rememberLazyListState()

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {

                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    shape = CircleShape,
                    backgroundColor = redlight,
                    contentColor = White,
                ) {

                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "",
                        modifier = Modifier.size(32.dp))
                }
        },

        topBar = {
            ParallaxToolbar(scrollState)
            Content(scrollState)
        }
    ) {paddingValues ->

    }
}

@Composable
fun ParallaxToolbar(scrollState: LazyListState){

    val imageHeight = AppBarExpendedHeight - AppBarCollapsedHeight

    val maxOffset =
        with(LocalDensity.current) { imageHeight.roundToPx() } - LocalWindowInsets.current.systemBars.layoutInsets.top

    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset

    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = White,
        modifier = Modifier
            .height(
                AppBarExpendedHeight
            )
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = if (offset == maxOffset) 4.dp else 0.dp
    ){
        Column {
            Box(
                Modifier
                    .height(imageHeight)
                    .graphicsLayer {
                        alpha = 1f - offsetProgress
                    }) {
                Image(
                    painter = painterResource(id = R.drawable.gamayo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.4f, Transparent),
                                    Pair(1f, White)
                                )
                            )
                        )
                )

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        "Pizza",
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .clip(Shapes.small)
                            .background(lightGray)
                            .padding(vertical = 6.dp, horizontal = 16.dp),
                    )
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(AppBarCollapsedHeight),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Pizza Mozzarella",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = (16 + 28 * offsetProgress).dp)
                        .scale(1f - 0.25f * offsetProgress)
                )
            }
        }

    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(AppBarCollapsedHeight)
            .padding(horizontal = 20.dp)
    ) {
        CircularButton(R.drawable.ic_arrow_back)
        CircularButton(R.drawable.ic_favorite)
    }
}

@Composable
fun CircularButton(
    @DrawableRes iconResouce: Int,
    color: Color = grayFont,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(),
        shape = Shapes.small,
        colors = ButtonDefaults.buttonColors(backgroundColor = White, contentColor = color),
        elevation = elevation,
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)
    ) {
        Icon(
            painterResource(id = iconResouce),
            null
            )
    }
}

@Composable
fun Content(scrollState: LazyListState){
    LazyColumn(contentPadding = PaddingValues(top = AppBarExpendedHeight, bottom = 150.dp), state = scrollState){
        item {
            BasicInfo()
            Description()
            QuantityCalculator()

        }
    }
}

@Composable
fun QuantityCalculator() {
    var value by remember { mutableStateOf(2) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .clip(Shapes.medium)
//            .border(border = BorderStroke(1.dp, Color(0xFFFFE9A6)), shape = Shapes.medium)
            .background(lightGray)
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = "$12.00",
            Modifier.weight(2f),
            fontWeight = Bold,
            fontSize = 21.sp
        )

        CircularButton(
            iconResouce = R.drawable.ic_minus,
            elevation = null,
            color = red
        ) { value-- }

        Text(
            text = "$value",
            Modifier.padding(16.dp),
            fontWeight = Medium,
            fontSize = 18.sp
        )

        CircularButton(
            iconResouce = R.drawable.ic_plus,
            elevation = null,
            color = red
        ) { value++ }
    }
}

@Composable
fun Description(){
    Text(
        text = "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco.",
//        fontWeight = Medium,
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
        style = TextStyle(
            color = DarkGray
        ),
        fontSize = 15.sp
    )
}

@Composable
fun BasicInfo() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        InfoColumn(R.drawable.ic_clock, "40 min")
        InfoColumn(R.drawable.ic_flame, "328 kcal")
        InfoColumn(R.drawable.ic_star, "4.9")
    }
}

@Composable
fun InfoColumn(@DrawableRes iconResouce: Int, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = iconResouce),
            contentDescription = null,
            tint = redlight,
            modifier = Modifier.height(24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = text, fontWeight = Bold)
    }
}

@Preview
@Composable
fun previewDetailItem(){
    DetailItem()
}

