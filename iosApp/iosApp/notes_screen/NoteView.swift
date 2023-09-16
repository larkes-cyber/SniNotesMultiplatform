//
//  NoteView.swift
//  iosApp
//
//  Created by MacBook on 13.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct NoteView:View{
    
    var note:Note
    var onDeleteClick:() -> Void
    
    
    var body:some View{
        VStack{
            HStack{
                Text(note.title).frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, alignment: .leading)
                    .font(.title3)
                Spacer()
                Button(action: {
                    onDeleteClick()
                }){
                    Image(systemName:"xmark").foregroundColor(.black)
                }
             
            }
            .padding(.horizontal, 20)
            .padding(.top, 15)
            .padding(.bottom, 2)
            Text(note.text)
                .font(.system(size: 14, weight: .regular))
                .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, alignment: .leading)
                .padding(.horizontal, 20)
                .padding(.bottom, 15)
        }.background(Color(hex: note.color))
    }
    
}


