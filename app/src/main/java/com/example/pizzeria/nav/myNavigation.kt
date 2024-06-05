package com.example.pizzeria.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pizzeria.register.SignUpScreen
import com.example.pizzeria.StartScreen
import com.example.pizzeria.login.LoginViewModel
import com.example.pizzeria.login.SignInScreen

@Composable
fun MyNavigation(
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel
){
//    val navController = rememberNavController()
    NavHost(
        navController = navController ,
        startDestination = HomeRoutes.StartScreen.name
    ) {
//        composable(route = HomeRoutes.StartScreen.name) {
//            StartScreen(onNavToLoginPage = {
//                navController.navigate(LoginRoutes.SignIn.name){
//                    popUpTo(HomeRoutes.StartScreen.name){
//                        inclusive = true
//                    }
//                }
//            }
//            ){
//                navController.navigate(LoginRoutes.SignIn.name)
//            }
//        }
//
//        composable(route = LoginRoutes.SignIn.name) {
//            SignInScreen(onNavToHomePage = {
//                navController.navigate(HomeRoutes.Home.name){
//                    launchSingleTop = true
//                    popUpTo(LoginRoutes.SignIn.name){
//                        inclusive = true
//                    }
//                }
//            },
//                loginViewModel = loginViewModel
//            ){
//                navController.navigate(LoginRoutes.SignUp.name){
//                    launchSingleTop = true
//                    popUpTo(LoginRoutes.SignIn.name){
//                        inclusive = true
//                    }
//                }
//            }
//        }
//
//        composable(route = LoginRoutes.SignUp.name) {
//            SignUpScreen(onNavToHomePage = {
//                navController.navigate(HomeRoutes.Home.name){
//                    popUpTo(LoginRoutes.SignUp.name){
//                        inclusive = true
//                    }
//                }
//            },
//                loginViewModel = loginViewModel){
//                navController.navigate(LoginRoutes.SignIn.name)
//            }
//        }


    }
}