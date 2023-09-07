import SwiftUI
import shared

@main
struct iOSApp: App {
    
    var sharedModule = DatabaseModule()
    
	var body: some Scene {
        
        let userRepository = sharedModule.userRepository
        let noteRepository = sharedModule.noteRepository
        
        let loginViewModel = LoginScreenViewModel(userRepository: userRepository)
        
		WindowGroup {
            NavigationView(){
                LoginScreen(viewModel: loginViewModel)
            }
            
		}
	}
}
