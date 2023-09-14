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
    
    private var noteTitle = ""
    private var noteText = ""
    private var color:Int64 = 0
    private var selected:Bool = false
    
    init(noteTitle: String, noteText: String, color: Int64 = Note.Companion().generateRandom(), selected: Bool) {
        self.noteTitle = noteTitle
        self.noteText = noteText
        self.color = color
        self.selected = selected
    }
    
    func toggle(){}
    
    
    var body:some View{
        VStack{
            HStack{
                VStack{
                    Text(noteTitle).frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, alignment: .leading)
                        .font(.system(size: 16, weight: .regular))
                    Text(noteText)
                        .font(.system(size: 14, weight: .regular))
                        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, alignment: .leading)
                }
                .padding(.leading, 16)
                .padding(.trailing, 24)
                .padding(.vertical, 8)
                
                if self.selected{
                    Button(action: toggle){
                        HStack{
                            Image(systemName: "checkmark.square")
                        }
                    }
                }
        
            }
        }.padding(.trailing, 24).background(Color(hex: color))
            .padding(.leading,12)
            .padding(.trailing, 16)
    }
    
}

struct NoteView_Previews: PreviewProvider {
    static var previews: some View {
        NoteView(noteTitle: "title", noteText: "this is a title", selected:true)
    }
}
