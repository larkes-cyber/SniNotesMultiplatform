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
    @Published var error:String? = nil
    
    @Published var hasBeenDone = false
    
    private var userRepository:UserRepository? = nil
    
    init(userRepository: UserRepository? = nil) {
        self.userRepository = userRepository
    }
    
    func loginUser(){
        
        if isSignUp{
            userRepository?.registerUser(
                user: User(
                    login: login,
                    password: password,
                    session: "",
                    name: name
                ),
                completionHandler: { res, err in
                    self.error = res?.message
                }
            )
        }else{
            userRepository?.authUser(
                login: Login(login: login, password: password),
                completionHandler: {res, err in
                    print(res?.message)
                    self.error = res?.message
                    if res?.message != nil{
                        self.hasBeenDone = res!.message!.isEmpty
                    }
                }
            )
        }
       
    }
    
    func switchLoginMode(){
        isSignUp = !isSignUp
    }
    
}
    
