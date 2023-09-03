package com.example.kmpsninotes.android.presentation.screen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Login
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import com.example.kmpsninotes.android.until.Constants.AuthorizationMode
import com.example.kmpsninotes.android.until.Constants.RegistrationMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
):ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState:StateFlow<LoginUiState> = _uiState




    fun onEmailTextFieldChange(text:String){
        _uiState.value = uiState.value.copy(emailTextFieldValue = text)
    }

    fun onNameTextFieldChange(text:String){
        _uiState.value = uiState.value.copy(nameTextFieldValue = text)
    }
    fun onPasswordTextFieldChange(text:String){
        _uiState.value = uiState.value.copy(passwordTextFieldValue = text)
    }
    fun switchMode(){
        if(_uiState.value.mode == RegistrationMode){
            _uiState.value = uiState.value.copy(mode = AuthorizationMode, error = "")
        }else{
            _uiState.value = uiState.value.copy(mode = RegistrationMode, error = "")
        }
    }

    fun onRegSubmit(){
        viewModelScope.launch {
            val user = userRepository.registerUser(
                User(
                login = uiState.value.emailTextFieldValue,
                password = uiState.value.passwordTextFieldValue,
                name = uiState.value.nameTextFieldValue
              )
            )
            Log.d("efgfdwefgfd",user.data.toString())
            if(user.data != null){
                userRepository.putUserData(user.data!!)
                _uiState.value = uiState.value.copy(hasBeenDone = true)
            }else{
                Log.d("efgbgfwergfd",user.message.toString())
                _uiState.value = uiState.value.copy(error = user.message!!)
            }
        }
    }
    fun onAuthSubmit(){
        viewModelScope.launch {
            val session = userRepository.authUser(
                Login(
                login = uiState.value.emailTextFieldValue,
                password = uiState.value.passwordTextFieldValue
            )
            )
            if (session.data != null){
                val user = userRepository.observeUserData(
                    session = session.data!!,
                    email = uiState.value.emailTextFieldValue
                )
                 userRepository.putUserData(user.data!!)
                _uiState.value = uiState.value.copy(hasBeenDone = true)
            }else{
                _uiState.value = uiState.value.copy(error = session.message!!)
            }
        }
    }
}