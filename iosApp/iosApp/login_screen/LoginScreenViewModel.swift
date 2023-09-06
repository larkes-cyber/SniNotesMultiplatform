//
//  LoginScreenViewModel.swift
//  iosApp
//
//  Created by MacBook on 05.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared


    
class LoginScreenViewModel:ObservableObject{
    @Published var login = ""
    @Published var name = ""
    @Published var password = ""
    
    @Published var isSignUp = false
    
    private var userRepository:UserRepository? = nil
    
    init(userRepository: UserRepository? = nil) {
        self.userRepository = userRepository
    }
    
    func registerUser(){
        
        userRepository?.registerUser(
            user: User(
                login: login,
                password: password,
                session: "",
                name: name
            ),
            completionHandler: { res, err in
                print(res?.message)
            }
        )
    }
    
    func switchLoginMode(){
        isSignUp = !isSignUp
    }
    
}
    
