//
//  LoginScreenViewModel.swift
//  iosApp
//
//  Created by MacBook on 05.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

extension LoginScreen{
    
    @MainActor class LoginScreenViewModel:ObservableObject{
        @Published var login = ""
        @Published var name = ""
        @Published var password = ""
    }
    
}
