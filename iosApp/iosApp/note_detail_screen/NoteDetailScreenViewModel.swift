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
    var color:Int64 =  Note.Companion().generateRandom()
    @Published var title:String = "" 
    @Published var text:String = ""
    @Published var hasBeenDone:Bool = false

    
    private let networkService = NetworkReachability()
    
    init(noteRepository: NoteRepository) {
        self.noteRepository = noteRepository
    }
    
    func saveData(){
        print(self.noteId, "%%%%%%%%%%%%%%%%%")
        let note = Note(id: self.noteId, title: self.title, text: self.text, color: self.color, online_sync: false, visible: true, timestamp: 0)
       
        noteRepository.noteSyncWithServer(note: note, online: self.networkService.isNetworkAvailable(), completionHandler: {res, error in
            note.online_sync = res?.data != nil
            print(note.id, "#####################################")
            self.noteRepository.insertNote(noteEntity: note, completionHandler: {_ in
                self.hasBeenDone = true
            })
        })
    }
    
    func loadNoteIfExists(id:String?){
        print(id, "$$$$$$$$")
        if id == nil{
            self.noteId = UUID().uuidString
        }else{
            self.noteId = id!
            noteRepository.observeNoteById(id: id!, completionHandler: { note, err in
                self.title = note?.title ?? ""
                self.text = note?.text ?? ""
                self.color = note?.color ?? Note.Companion().generateRandom()
            })
        }
    }
    
    
}
