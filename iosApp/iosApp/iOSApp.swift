import SwiftUI
import shared

@main
struct iOSApp: App {
    
    var sharedModule = DatabaseModule()
    
	var body: some Scene {
        
        let userRepository = sharedModule.userRepository
        let noteRepository = sharedModule.noteRepository
        
        
		WindowGroup {
            NavigationView(){
                //LoginScreen(viewModel: loginViewModel)
                NotesScreen(notesRepository: noteRepository)
            }
            
		}
	}
}
