package com.example.pizzeria.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pizzeria.nav.BottomNav
import com.example.pizzeria.nav.myFAB
import com.example.pizzeria.screen.CartHeader

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WishlistScreen(navController: NavHostController){
    val scrollState = rememberLazyListState()

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            myFAB()
        },
        topBar = {
            CartHeader()
        },
        bottomBar = {
            BottomNav(rememberNavController())
        }

    ) {

    }
}