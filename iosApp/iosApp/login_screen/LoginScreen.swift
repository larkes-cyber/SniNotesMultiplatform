//
//  LoginScreen.swift
//  iosApp
//
//  Created by MacBook on 05.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI


struct LoginScreen:View{
    
    @StateObject var viewModel = LoginScreenViewModel()
    @State private(set) var isSignUp = false
    
    var body: some View {
        VStack{
            Text(isSignUp ? "Sign up now" : "Sign in now" )
                .font(.system(size: 32, weight: .medium))
                .padding(.bottom, 14)
            if isSignUp{
                Text("Please fill the details and create account")
                    .font(.system(size: 18, weight: .medium))
                    .frame(width: 330)
                    .multilineTextAlignment(.center)
                    .padding(.bottom, 40)
                TextField("Enter your name", text: $viewModel.name)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding(.horizontal, 30)
                    .padding(.bottom, 10)
            }
            
            TextField("Enter your email", text: $viewModel.login)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding(.horizontal, 30)
                .padding(.bottom, 10)
            SecureField("Enter your password", text: $viewModel.password)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding(.horizontal, 30)
                .padding(.bottom, 10)

            LargeButton(
                title:isSignUp ? "Sign up" : "Sign in",
                backgroundColor: Color.blue) {
                }
                .padding(.bottom, 10)
                .padding(.horizontal, 10)
            
            HStack{
                Text(isSignUp ? "Already have an account" : "Don't have an account")
                    .font(.system(size: 12, weight: .medium))
                Button(isSignUp ? "Sign in" : "Sign up"){
                    isSignUp = !isSignUp
                }
                .font(.system(size: 12, weight: .medium))
                
            }
            
            
            
        }
    }
}

struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}



