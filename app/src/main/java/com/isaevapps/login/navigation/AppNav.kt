package com.isaevapps.login.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.isaevapps.login.auth.AuthViewModel
import com.isaevapps.login.ui.screen.AuthMode
import com.isaevapps.login.ui.screen.LoginScreen
import com.isaevapps.login.ui.screen.ProfileScreen
import com.isaevapps.login.ui.screen.SignInScreen
import com.isaevapps.login.ui.screen.SplashScreen

@Composable
fun AppNav(vm: AuthViewModel = viewModel()) {
    val nav = rememberNavController()
    val state by vm.state.collectAsState()

    NavHost(navController = nav, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onLogin = { nav.navigate(Screen.LoginOptions.route) },
                onSignUp = { nav.navigate(Screen.SignUp.route) }
            )
        }

        composable(Screen.LoginOptions.route) {
            LoginScreen(
                onEmailClick = { nav.navigate(Screen.SignIn.route) },
                onSignUpClick = { nav.navigate(Screen.SignUp.route) }
            )
        }

        composable(Screen.SignIn.route) {
            SignInScreen(
                mode = AuthMode.SIGN_IN,
                onPrimary = { email, password -> vm.signIn(email, password) },
                onSwitch = { nav.navigate(Screen.SignUp.route) }
            )
            LaunchedEffect(state.user) { if (state.user != null) gotoProfile(nav) }
        }
        composable(Screen.SignUp.route) {
            SignInScreen(
                mode = AuthMode.SIGN_UP,
                onPrimary = { email, password -> vm.signUp(email, password) },
                onSwitch = { nav.navigate(Screen.SignIn.route) }
            )
            LaunchedEffect(state.user) { if (state.user != null) gotoProfile(nav) }
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onLogout = {
                    vm.signOut()
                    nav.navigate(Screen.Splash.route) {
                        popUpTo(Screen.Profile.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

private fun gotoProfile(nav: NavHostController) {
    nav.navigate(Screen.Profile.route) {
        popUpTo(Screen.Splash.route) {
            inclusive = true
        }
        launchSingleTop = true
    }

}