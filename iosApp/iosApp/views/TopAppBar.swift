//
//  TopAppBar.swift
//  iosApp
//
//  Created by MacBook on 16.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TopAppBar<Destination:View>: View {
    
    var destinationProvider:() -> Destination
    
    var body: some View {
        ZStack{
            Text("SniNotes")
                .font(.system(size: 22, weight: .medium))
                .frame(alignment: .leading)
            Spacer()
            HStack{
                Spacer()
                NavigationLink(destination: destinationProvider()){
                    Image(systemName: "plus")
                }
            }
            .padding(.horizontal, 20)
            .padding(.bottom, 20)
        }
    }
}


