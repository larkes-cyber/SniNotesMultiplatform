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
            let user = User(
                login: login,
                password: password,
                session: "",
                name: name
            )
            userRepository?.registerUser(
                user: user,
                completionHandler: { res, err in
                    self.error = res?.message
                    if res!.data != nil{
                        self.userRepository?.putUserData(user: user, completionHandler: {_ in
                            self.hasBeenDone = true
                        })
                    }
                }
            )
        }else{
            userRepository?.authUser(
                login: Login(login: login, password: password),
                completionHandler: {res, err in
                    self.error = res?.message
                    
                    if res?.message != nil{
                        self.hasBeenDone = res!.message!.isEmpty
                    }else{
                        self.userRepository?.observeUserData(session: res?.data as! String, email: self.login, completionHandler: {user, err in
                            if user?.data != nil{
                                self.userRepository?.putUserData(user: user?.data ?? User(login: "",password: "",session: "",name: ""), completionHandler: {_ in
                                    self.hasBeenDone = true
                                    })
                                }
                            }
                        )
                    }
                }
            )
        }
       
    }
    
    func switchLoginMode(){
        isSignUp = !isSignUp
    }
    
}
    
