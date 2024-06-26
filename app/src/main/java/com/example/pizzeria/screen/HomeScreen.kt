package com.example.pizzeria.screen

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pizzeria.R
import com.example.pizzeria.nav.BottomNav
import com.example.pizzeria.nav.Screen
import com.example.pizzeria.nav.myFAB
import com.example.pizzeria.ui.theme.green
import com.example.pizzeria.ui.theme.yellow1
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter, UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(navController: NavHostController){
    Scaffold(
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
                androidx.compose.material.Icon(
                    imageVector = Icons.Rounded.ShoppingCart,
                    contentDescription = "")
            }
        },
        topBar = {
            //location
    var location by remember { mutableStateOf("") }
    getLocation(LocalContext.current) { specificLocation ->
        // Cập nhật biến location với chuỗi địa chỉ cụ thể
        location = "$specificLocation"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .height(70.dp)
                .padding(horizontal = 15.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CenterAlignedTopAppBar(
                title = {
                    Box(
                    modifier = Modifier
                        .height(50.dp)
                        .width(240.dp)
                        .background(Color.White)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "location icon",
                            modifier = Modifier
                                .size(15.dp)
                                .align(Alignment.CenterStart),
                            tint = Color.Unspecified
                        )

                        Text(
                            text = location,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .widthIn(max = 210.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_down),
                            contentDescription = "location icon",
                            modifier = Modifier
                                .size(15.dp)
                                .align(Alignment.CenterEnd),
                            tint = Color.Unspecified
                        )
                    }
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.list),
                        contentDescription = "List",
                        modifier = Modifier.height(40.dp),
                        tint = Color.Unspecified
                    )
                },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.avar),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(15.dp))
                    )
                }
            )
        }
    }
        },
        bottomBar = {
            val selectedItem = remember { mutableStateOf("Home") }

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
//                        navController.navigate(Screen.Home.rout)
                            },
                            icon = {
                                androidx.compose.material.Icon(
                                    imageVector = Icons.Rounded.Home,
                                    contentDescription = "Home")
                            },
                            label = { androidx.compose.material.Text(text = "Home") },
                            alwaysShowLabel = false
                        )
                        BottomNavigationItem(
                            selected =  selectedItem.value == "Wishlist",
                            onClick = {
                                selectedItem.value = "Wishlist"
                                navController.navigate(Screen.Wishlist.rout)
                            },
                            icon = {
                                androidx.compose.material.Icon(
                                    imageVector = Icons.Rounded.Favorite,
                                    contentDescription = "Wishlist")
                            },
                            label = { androidx.compose.material.Text(text = "Wishlist") },
                            alwaysShowLabel = false
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        BottomNavigationItem(
                            selected =  selectedItem.value == "Ordered",
                            onClick = {
                                selectedItem.value = "Ordered"
//                        navController.navigate(Screen.Ordered.rout)
                            },
                            icon = {
                                androidx.compose.material.Icon(
                                    painterResource(id = R.drawable.pending),
                                    contentDescription = "Ordered")
                            },
                            label = { androidx.compose.material.Text(text = "Order") },
                            alwaysShowLabel = false
                        )
                        BottomNavigationItem(
                            selected =  selectedItem.value == "Profile",
                            onClick = {
                                selectedItem.value = "Profile"
//                        navController.navigate(Screen.Profile.rout)
                            },
                            icon = {
                                androidx.compose.material.Icon(
                                    imageVector = Icons.Rounded.Person,
                                    contentDescription = "Profile")
                            },
                            label = { androidx.compose.material.Text(text = "Profile") },
                            alwaysShowLabel = false
                        )
                    }

                }
            )
        },
        content = {

            val scrollState = rememberLazyListState()
            LazyColumn(contentPadding = PaddingValues(bottom = 110.dp), state = scrollState) {
                item(){

                    SearchViewBar()

                    Spacer(modifier = Modifier.height(10.dp))

                    ListCategory()

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
                                color = Color.Black
                            )
                            TextButton(onClick = {
                                navController.navigate(Screen.Cart.rout)
                            },
                            ) {
                                Text(text = "All",
                                    fontWeight = FontWeight.W400,
                                    fontSize = 14.sp,
                                    color = Color.Black
                                )
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

                    SliderBanner()
                }
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCategory(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(6) {
                Surface(
                    onClick = {

                    },
                    shape = RoundedCornerShape(42.dp),
                    color = yellow1,
                    modifier = Modifier
                        .width(120.dp)
                        .height(47.dp)
                        .padding(end = 10.dp, start = 3.dp)
                        .background(
                            MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(42.dp)
                        )
                        .padding(bottom = 1.dp),
                    shadowElevation = 6.dp,
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
                                .size(39.dp),
                            color = Color(0xFFF5F0E4)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pizza),
                                contentDescription = "",
                                modifier = Modifier.padding(3.dp)
                                //                        contentScale = ContentScale.Crop,
                            )
                        }
                        Text(
                            text = "Chicken",
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                    }
                }
            }
        }
    }
}

//ham lay vi tri
@SuppressLint("MissingPermission")
fun getLocation(context: Context, onLocationReceived: (String) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    val locationRequest = LocationRequest.create().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        interval = 10000 // 10 seconds
        fastestInterval = 5000 // 5 seconds
    }

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation?.let { location ->
                // Sử dụng Geocoder để chuyển đổi kinh độ và vĩ độ thành địa chỉ cụ thể
                val geocoder = Geocoder(context, Locale.getDefault())
                val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                val address = addresses?.firstOrNull()

                // Tạo chuỗi địa chỉ cụ thể từ thông tin trả về từ Geocoder
                val specificLocation = address?.let {
                    "${it.featureName ?: ""}, ${it.thoroughfare ?: ""}, ${it.subLocality ?: ""}, ${it.locality ?: ""}, ${it.subAdminArea ?: ""}, ${it.adminArea ?: ""}, ${it.countryName ?: ""}"
                } ?: "Unknown Location"

                // Gửi chuỗi địa chỉ cụ thể đến hàm callback
                onLocationReceived(specificLocation)
            }
        }
    }

    fusedLocationClient.requestLocationUpdates(
        locationRequest,
        locationCallback,
        Looper.getMainLooper()
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun previewHomeScreen(){
    HomeScreen(rememberNavController())
}
