package my.individual.project.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    boolean isAuthenticated(Long chatId);
}
