package com.example.pizzeria.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pizzeria.R
import com.example.pizzeria.nav.BottomNav
import com.example.pizzeria.nav.Screen
import com.example.pizzeria.nav.myFAB
import com.example.pizzeria.ui.theme.Shapes
import com.example.pizzeria.ui.theme.black
import com.example.pizzeria.ui.theme.delete
import com.example.pizzeria.ui.theme.red
import com.example.pizzeria.ui.theme.yellow1

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WishlistScreen(navController: NavHostController){
    val scrollState = rememberLazyListState()

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.Cart.rout)
                },
                backgroundColor = yellow1,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(5.dp, 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.ShoppingCart,
                    contentDescription = "")
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                ),
                title = {
                    androidx.compose.material3.Text(
                        text = "My Favorites",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    Column(
                        modifier = Modifier.padding(start = 15.dp)
                    ) {
                        Button(
                            onClick = { },
                            contentPadding = PaddingValues(),
                            shape = Shapes.small,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = Color.Black
                            ),
                            //                    elevation = 5.dp,
                            modifier = Modifier
                                .width(38.dp)
                                .height(38.dp)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.ic_arrow_back),
                                null
                            )
                        }
                    }
                },
                actions = {
                    Row(
                        modifier = Modifier.padding(end = 15.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Favorite,
                            contentDescription = "",
                            tint = delete
                        )
                        Text(text = "4", color = red, fontWeight = FontWeight.SemiBold)
                    }
                }
            )
        },
        bottomBar = {
            val selectedItem = remember { mutableStateOf("Wishlist") }

            BottomAppBar(
                cutoutShape = CircleShape,
                backgroundColor = yellow1,
                contentColor = Color.White,
                elevation = 5.dp,
                modifier = Modifier
                    .clip(RoundedCornerShape(topEnd = 13.dp, topStart = 13.dp)),
                content = {
                    BottomNavigation(
                        backgroundColor = yellow1,
                    ){
                        BottomNavigationItem(
                            selected =  selectedItem.value == "Home",
                            onClick = {
                                selectedItem.value = "Home"
                        navController.navigate(Screen.Home.rout)
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Rounded.Home,
                                    contentDescription = "Home")
                            },
                            label = { Text(text = "Home") },
                            alwaysShowLabel = false
                        )
                        BottomNavigationItem(
                            selected =  selectedItem.value == "Wishlist",
                            onClick = {
                                selectedItem.value = "Wishlist"
                                navController.navigate(Screen.Wishlist.rout)
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Rounded.Favorite,
                                    contentDescription = "Wishlist")
                            },
                            label = { Text(text = "Wishlist") },
                            alwaysShowLabel = false
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        BottomNavigationItem(
                            selected =  selectedItem.value == "Ordered",
                            onClick = {
                                selectedItem.value = "Ordered"
                        navController.navigate(Screen.Ordered.rout)
                            },
                            icon = {
                                Icon(
                                    painterResource(id = R.drawable.pending),
                                    contentDescription = "Ordered")
                            },
                            label = { Text(text = "Order") },
                            alwaysShowLabel = false
                        )
                        BottomNavigationItem(
                            selected =  selectedItem.value == "Profile",
                            onClick = {
                                selectedItem.value = "Profile"
                        navController.navigate(Screen.Profile.rout)
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Rounded.Person,
                                    contentDescription = "Profile")
                            },
                            label = { Text(text = "Profile") },
                            alwaysShowLabel = false
                        )
                    }

                }
            )
        },

        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 50.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    columns = GridCells.Fixed(2),
                    content = {
                        items(4) {
                            Surface(
                                onClick = { /*TODO*/ },
                                shape = RoundedCornerShape(17.dp),
                                color = Color.White,
                                modifier = Modifier
                                    .width(165.dp)
                                    .background(
                                        color = yellow1,
                                        shape = RoundedCornerShape(17.dp)
                                    )
                                    .padding(bottom = 1.dp),
                                shadowElevation = 8.dp
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp, bottom = 14.dp, start = 10.dp, end = 10.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Box(
                                        modifier = Modifier
                                            .size(130.dp)
//                        .padding(5.dp)
                                            .clip(RoundedCornerShape(20.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.pizza_smoked_salmon_74190_5108),
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop,
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(20.dp))

                                    androidx.compose.material3.Text(
                                        text = "Pizza Mozzarella Size L",
                                        fontWeight = FontWeight.Medium,
                                        color = black,
                                        fontSize = 16.sp,
                                        style = TextStyle(
                                            textAlign = TextAlign.Center
                                        )
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        androidx.compose.material3.Text(
                                            text = "$12.00",
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.Black,
                                            modifier = Modifier.align(Alignment.CenterVertically),
                                            fontSize = 17.sp
                                        )

                                        androidx.compose.material3.Button(
                                            modifier = Modifier.size(30.dp),
                                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = yellow1),
                                            shape = CircleShape,
                                            contentPadding = PaddingValues(4.dp),
                                            onClick = {
                                            }
                                        )
                                        {
                                            androidx.compose.material3.Icon(
                                                modifier = Modifier.fillMaxSize(),
                                                imageVector = Icons.Default.AddCircle,
                                                contentDescription = ""
                                            )
                                        }
                                    }
                                }
                                Row() {
                                    androidx.compose.material3.Surface(
                                        onClick = {
                                        },
                                        shape = CircleShape,
                                        color = Color.White
                                    ) {
                                        androidx.compose.material3.Icon(
                                            imageVector = Icons.Rounded.Favorite,
                                            contentDescription = "",
                                            tint = delete,
                                            modifier = Modifier
                                                .size(30.dp)
                                                .padding(2.dp),
                                        )
                                    }
                                }

                            }
                        }
                    })
            }
        }

    )
}

@Preview
@Composable
fun PreviewWishScreen(){
    WishlistScreen(rememberNavController())
}
