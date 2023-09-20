

import Foundation
import shared

class SplashScreenViewModel:ObservableObject{

    var userRepository:UserRepository? = nil
    var noteRepository:NoteRepository
    @Published var authorizated:Bool = false
    @Published var notAuthorizated:Bool = false
    private let networkService = NetworkReachability()
    
    func checkUserCached(){
        self.userRepository?.getUserData(completionHandler: { [self](user, error) in
            if user != nil{
                do{
                    try noteRepository.notesSynchronization(online: networkService.isNetworkAvailable(), completionHandler: {_,_ in
                        self.authorizated = true
                    })
                }catch{
                    self.authorizated = true

                }
            }else{
                self.authorizated = false
            }
//            self.authorizated = user != nil
//            self.notAuthorizated = user == nil
        })
    
    }
    
    init(userRepository: UserRepository? = nil, noteRepository:NoteRepository) {
        self.userRepository = userRepository
        self.noteRepository = noteRepository
    }
    
}
