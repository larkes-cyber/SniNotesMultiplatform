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
    
    @ObservedObject var viewModel:NotesScreeViewModel = NotesScreeViewModel()
    private var noteRepository:NoteRepository
    
    init(notesRepository:NoteRepository) {
        self.noteRepository = notesRepository
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
                        NavigationLink(destination: NoteDetailScreen(noteRepository: noteRepository)){
                            Image(systemName: "plus")
                        }
                    }
                }
            }
            .padding(.horizontal, 20)
            .padding(.bottom, 20)
            List{
                ForEach(viewModel.notesList, id:\.self.id){note in
                    let _ = print(12334)
                    NoteView(noteTitle: note.title, noteText: note.text, color: note.color, selected: false)
                }
            }.onAppear{
                let _ = print(12334)
                viewModel.setupModel(noteReppository: noteRepository)
                viewModel.loadNotes()
            }
            .listStyle(.plain)
        }
    }
    
}

