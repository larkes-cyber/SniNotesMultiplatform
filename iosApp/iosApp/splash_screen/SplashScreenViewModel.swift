

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
                noteRepository.notesSynchronization(online: networkService.isNetworkAvailable(), completionHandler: {res, err in
                    print(err)
                    self.authorizated = true
                })
            }else{
                self.authorizated = false
                self.notAuthorizated = true
            }
        })
    
    }
    
    init(userRepository: UserRepository? = nil, noteRepository:NoteRepository) {
        self.userRepository = userRepository
        self.noteRepository = noteRepository
    }
    
}
