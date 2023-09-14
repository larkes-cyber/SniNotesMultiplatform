//
//  NotesScreen.swift
//  iosApp
//
//  Created by MacBook on 07.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NotesScreen: View {
    
    
    @ObservedObject var viewModel:NotesScreeViewModel
    
    init(notesRepository:NoteRepository) {
        self.viewModel = NotesScreeViewModel(noteReppository: notesRepository)
    }
    
    var body: some View {
        VStack(alignment:.trailing, spacing: 0){
            ZStack{
                Text("SniNotes")
                    .font(.system(size: 22, weight: .medium))
                    .frame(alignment: .leading)
                Spacer()
                HStack{
                    Spacer()
                    if viewModel.selectingMode{
                        Button(action: {}){
                            Image(systemName:"trash")
                        }
                    }else{
                        NavigationLink(destination: NoteDetailScreen()){
                            Image(systemName: "plus")
                        }
                                    
                    }
                }
            }.padding(.horizontal, 20)
                .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
            List{
                ForEach(viewModel.notesList, id:\.self.id){note in
                    NoteView(noteTitle: note.title, noteText: note.text, color: note.color, selected: false)
                }
            }.onAppear{
                viewModel.loadNotes()
            }
        }
    }
    
}

