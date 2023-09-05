//
//  LoginScreen.swift
//  iosApp
//
//  Created by MacBook on 05.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI


struct LoginScreen:View{
    var body: some View {
        VStack{
            Text("Sign up now")
                .font(.system(size: 32, weight: .medium))
                .padding(.bottom, 14)
            Text("Please fill the details and create account")
                .font(.system(size: 18, weight: .medium))
            
        }
    }
}

struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}
