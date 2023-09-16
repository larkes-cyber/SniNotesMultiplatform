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
    @Published var selectedNoteId:String? = nil
    @Published var isNoteSelected:Bool = false
    
    private var noteReppository:NoteRepository? = nil
    
    func setupModel(noteReppository:NoteRepository){
        self.noteReppository = noteReppository
    }

    func loadNotes(){
        self.noteReppository?.observeNotes(completionHandler: {notes, error in
            self.notesList = notes!
        })
    }
    
    func deleteNote(note:Note){
        noteReppository?.deleteNote(noteEntity: note, online: false, completionHandler: {err in
            print(err)
            self.loadNotes()
        })
    }
    
    func switchMode(){
        selectingMode = !selectingMode
    }
    
    func selectNote(id:String){
        self.selectedNoteId = id
        self.isNoteSelected = true
    }
    
    
    
}
