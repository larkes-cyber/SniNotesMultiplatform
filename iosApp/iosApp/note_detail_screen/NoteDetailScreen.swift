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
    private var noteRepository:NoteRepository
    
    @Environment(\.presentationMode) var presentation

    
    init(noteid:String?, noteRepository:NoteRepository){
        self.noteRepository = noteRepository
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
        NavigationLink(
            destination: NotesScreen(notesRepository: noteRepository),
            isActive: $viewModel.hasBeenDone
        ){
            EmptyView()
        }
        .navigationBarHidden(true)
        VStack(alignment: .leading){
            VStack(alignment:.leading){
                Button(
                    action: {
                        viewModel.saveData()
                    }
                ){
                    Image(systemName: "xmark")
                        .foregroundColor(.black)
                        .imageScale(.medium)
                       
                }
            }.padding(6).padding(.bottom, 10)
            TextField("Enter a title...", text: $viewModel.title)
                .font(.title)
                .padding(.leading, 6)
            TextField("Enter a content...", text: $viewModel.text).padding(.all, 6)
            Spacer()
        }.onAppear{
            viewModel.loadNoteIfExists(id: noteId)
        }
        .padding()
        .background(Color(hex: viewModel.color))
        
    }
}
