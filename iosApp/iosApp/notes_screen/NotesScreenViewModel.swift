//
//  NotesScreenViewModel.swift
//  iosApp
//
//  Created by MacBook on 13.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class NotesScreeViewModel:ObservableObject{
    
    @Published var selectingMode = false
    @Published var notesList:[Note] = []
    @Published var selectedNotes:[Note] = []
    
    private var noteReppository:NoteRepository? = nil
    
    init(noteReppository: NoteRepository? = nil) {
        self.noteReppository = noteReppository
    }
    
    func loadNotes(){
        self.noteReppository?.observeNotes(completionHandler: {notes, error in
            self.selectedNotes = notes!
        })
    }
    
    func deleteNotes(){
        selectedNotes.forEach { note in
            let index = notesList.firstIndex(of: note)
            notesList.remove(at: index!)
        }
    }
    
    func switchMode(){
        selectingMode = !selectingMode
    }
    
    
    
    
}
