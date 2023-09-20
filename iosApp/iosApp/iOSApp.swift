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
                SplashScreen(userRepository: userRepository, noteRepository: noteRepository)
            }
            
		}
	}
}
