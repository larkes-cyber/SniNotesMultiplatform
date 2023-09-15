

import Foundation


import SwiftUI
import shared

struct SplashScreen:View{
    
    @ObservedObject private var viewModel:SplashScreenViewModel
    private var userRepostory:UserRepository
    private var noteRepository:NoteRepository
    
    init(userRepository: UserRepository, noteRepository:NoteRepository) {
        self.viewModel = SplashScreenViewModel(userRepository: userRepository)
        self.userRepostory = userRepository
        self.noteRepository = noteRepository
        viewModel.checkUserCached()
    }
    

    var body: some View{
        
        NavigationLink(destination: NotesScreen(notesRepository: noteRepository), isActive: $viewModel.authorizated){
            NotesScreen(notesRepository: noteRepository)
                       }.hidden()
        
        NavigationLink(destination: LoginScreen(userRepository: userRepostory), isActive: $viewModel.authorizated){
            LoginScreen(userRepository: userRepostory)
                       }.hidden()
        
        VStack{
            
        }
    }
}
