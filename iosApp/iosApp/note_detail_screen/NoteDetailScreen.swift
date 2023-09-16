//
//  NoteDetailScreen.swift
//  iosApp
//
//  Created by MacBook on 14.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct NoteDetailScreen:View{
    
    @ObservedObject var viewModel:NoteDetailScreenViewModel
    var noteId:String?
    
    @Environment(\.presentationMode) var presentation

    
    init(noteid:String?, noteRepository:NoteRepository){
        self.viewModel = NoteDetailScreenViewModel(noteRepository: noteRepository)
        self.noteId = noteid
    }
    
    
    var body:some View{
        let _ = {
            print(String(self.viewModel.hasBeenDone), "#########")
        }
        let _ = {
            if self.viewModel.hasBeenDone{
                self.presentation.wrappedValue.dismiss()
            }
        }
        VStack{
            TextField("Enter a title...", text: $viewModel.title)
                .font(.title)
            TextField("Enter a content...", text: $viewModel.text).padding(.all, 8)
            Spacer()
        }.onAppear{
            viewModel.loadNoteIfExists(id: noteId)
        }
        .padding()
        .background(Color(hex: viewModel.color))
    }
}
