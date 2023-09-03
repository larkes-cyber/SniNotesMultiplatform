package com.example.kmpsninotes.android.presentation.screen.login

import com.example.kmpsninotes.android.until.Constants.RegistrationMode


data class LoginUiState(
  val mode:String = RegistrationMode,
  val emailTextFieldValue:String = "",
  val passwordTextFieldValue:String = "",
  val nameTextFieldValue:String = "",
  val hasBeenDone:Boolean = false,
  val error:String = ""
)