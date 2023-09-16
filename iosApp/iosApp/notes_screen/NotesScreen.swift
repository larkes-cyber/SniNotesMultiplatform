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
            
            NavigationLink(destination: NoteDetailScreen(noteid: viewModel.selectedNoteId, noteRepository: noteRepository), isActive: $viewModel.isNoteSelected){
                EmptyView()
            }.hidden()
            
            TopAppBar<NoteDetailScreen>(destinationProvider: {
                NoteDetailScreen(noteid: nil, noteRepository: noteRepository)
            })
            List{
                
                let notes = viewModel.notesList.filter{note in
                    return note.visible
                }
                
                ForEach(notes, id:\.self.id){note in
                    Button(action: {
                        viewModel.selectNote(id: note.id)
                    }){
                        NoteView(note: note, onDeleteClick: {
                            viewModel.deleteNote(note: note)
                        })
                    }
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

