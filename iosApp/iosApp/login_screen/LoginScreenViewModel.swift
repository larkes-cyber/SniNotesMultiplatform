//
//  LoginScreenViewModel.swift
//  iosApp
//
//  Created by MacBook on 05.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared


    
@MainActor class LoginScreenViewModel:ObservableObject{
    @Published var login = ""
    @Published var name = ""
    @Published var password = ""
    
    private var userRepository:UserRepository? = nil
    
    init(userRepository: UserRepository? = nil) {
        self.userRepository = userRepository
    }
    
    func registerUser(){
    
    }
    
}
    
