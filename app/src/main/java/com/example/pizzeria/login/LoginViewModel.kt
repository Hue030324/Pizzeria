package com.example.pizzeria.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzeria.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository = AuthRepository()
): ViewModel() {
    val currentUser = repository.currentUser

    val hasUser:Boolean
        get() = repository.hasUser()

    var  loginUIState by mutableStateOf(LoginUIState())
        private set

    fun onUserNameChange(userName: String){
        loginUIState = loginUIState.copy(userName = userName)
    }
    fun onPasswordChange(password: String){
        loginUIState = loginUIState.copy(password = password)
    }
    fun onUserNameChangeSignUp(userName: String){
        loginUIState = loginUIState.copy(userNameSignUp = userName)
    }
    fun onPasswordChangeSignUp(password: String){
        loginUIState = loginUIState.copy(passwordSignUp = password)
    }
    fun onConfirmPasswordChange(password: String){
        loginUIState = loginUIState.copy(confirmPasswordSignUp = password)
    }

    private fun validateLoginForm() =
        loginUIState.userName.isNotBlank() &&
                loginUIState.password.isNotBlank()

    private fun validateSignUpForm() =
        loginUIState.userNameSignUp.isNotBlank() &&
                loginUIState.passwordSignUp.isNotBlank() &&
                loginUIState.confirmPasswordSignUp.isNotBlank()

    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateSignUpForm()){
                throw IllegalArgumentException("Email & Password can't be empty!")
            }
            loginUIState = loginUIState.copy(isLoading = true)
            if (loginUIState.passwordSignUp !=
                loginUIState.confirmPasswordSignUp){
                throw IllegalArgumentException("Password do not match!"
                )
            }
            loginUIState = loginUIState.copy(signUpError = null)
            repository.createrUser(
                loginUIState.userNameSignUp,
                loginUIState.passwordSignUp
            ){isSuccessfull ->
                if (isSuccessfull){
                    Toast.makeText(
                        context,
                        "Registration Successful!",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUIState = loginUIState.copy(isSuccessLogin = true)
                }else{
                    Toast.makeText(
                        context,
                        "Registration Failed!",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUIState = loginUIState.copy(isSuccessLogin = false)

                }
            }

        }catch (e:Exception){
            loginUIState = loginUIState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        }finally {
            loginUIState = loginUIState.copy(isLoading = false)
        }
    }

    fun loginUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateLoginForm()){
                throw IllegalArgumentException("Email & Password can't be empty!")
            }
            loginUIState = loginUIState.copy(isLoading = true)

            loginUIState = loginUIState.copy(loginError = null)
            repository.login(
                loginUIState.userName,
                loginUIState.password
            ){isSuccessfull ->
                if (isSuccessfull){
                    Toast.makeText(
                        context,
                        "Login Successful!",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUIState = loginUIState.copy(isSuccessLogin = true)
                }else{
                    Toast.makeText(
                        context,
                        "Login Failed!",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUIState = loginUIState.copy(isSuccessLogin = false)

                }
            }

        }catch (e:Exception){
            loginUIState = loginUIState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        }finally {
            loginUIState = loginUIState.copy(isLoading = false)
        }
    }
}

data class LoginUIState(
    val userName:String = "",
    val password:String = "",
    val userNameSignUp:String = "",
    val passwordSignUp:String = "",
    val confirmPasswordSignUp:String = "",
    val isLoading:Boolean = false,
    val isSuccessLogin:Boolean = false,
    val signUpError:String? = null,
    val loginError:String? = null
)