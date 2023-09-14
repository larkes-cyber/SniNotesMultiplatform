

import Foundation
import shared

class SplashScreenViewModel:ObservableObject{

    var userRepository:UserRepository? = nil
    @Published var authorizated:Bool = false
    @Published var notAuthorizated:Bool = false
    
    func checkUserCached(){
        self.userRepository?.getUserData(completionHandler: { [self](user, error) in
            self.authorizated = user != nil
            self.notAuthorizated = user == nil
        })
    
    }
    
    init(userRepository: UserRepository? = nil) {
        self.userRepository = userRepository
    }
    
}
