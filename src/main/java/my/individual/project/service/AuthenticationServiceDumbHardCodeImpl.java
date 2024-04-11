package my.individual.project.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceDumbHardCodeImpl implements AuthenticationService {
    public boolean isAuthenticated(Long chatId) {
        return false;
    }

    public void register(){

    }
}
