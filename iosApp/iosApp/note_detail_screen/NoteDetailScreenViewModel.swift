//
//  NoteDetailScreenViewModel.swift
//  iosApp
//
//  Created by MacBook on 15.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class NoteDetailScreenViewModel:ObservableObject{
    
    private var noteRepository:NoteRepository
    
    private var noteId:String = ""
    var color:Int64? = nil
    @Published var title:String = ""
    @Published var text:String = ""
    @Published var hasBeenDone:Bool = false
    
    init(noteRepository: NoteRepository) {
        self.noteRepository = noteRepository
    }
    
    func saveNote(){
        noteRepository.insertNote(noteEntity: Note(id: self.noteId, title: self.title, text: self.text, color: self.color ?? 0, online_sync: false, visible: true, timestamp: 0), completionHandler: {_ in
            self.hasBeenDone = true
        })
    }
    
    func loadNoteIfExists(id:String?){
        if !noteId.isEmpty{
            noteRepository.observeNoteById(id: id!, completionHandler: { note, err in
                self.title = note?.title ?? ""
                self.text = note?.text ?? ""
                self.color = note?.color ?? 0
            })
        }else{
            self.noteId = id!
        }
    }
    
}
