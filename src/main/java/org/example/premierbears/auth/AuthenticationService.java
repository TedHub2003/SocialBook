package org.example.premierbears.auth;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.example.premierbears.email.EmailService;
import org.example.premierbears.email.EmailTemplateName;
import org.example.premierbears.role.RoleRepository;
import org.example.premierbears.user.Token;
import org.springframework.beans.factory.annotation.Value;
import org.example.premierbears.user.TokenRepository;
import org.example.premierbears.user.User;
import org.example.premierbears.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;

    @Value("${spring.application.mailing.frontend.activation-url}")
    private String activitionUrl;


    // cette methode permet d'enregistrer un utilisateur
    public void register(RegistrationRequest request) throws MessagingException {

        var userRole = roleRepository.findByName("USER")
                //TODO: handle this exception
                .orElseThrow(() -> new IllegalStateException("ROLE USER was  not found"));

        var user = User.builder()
                .id(generateId())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);

    }
    private int generateId() {
        // Utiliser UUID pour générer un ID unique et le convertir en entier
        UUID uuid = UUID.randomUUID();
        return Math.abs(uuid.hashCode());
    }
    // cette methode permet d'envoyer un email de validation
    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        //TODO: send email
        emailService.sendEmail(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activitionUrl,
                newToken,
                "Activate your account"
               );
    }


    // cette methode permet de generer et de sauvegarder le token d'activation
    // elle prend en parametre un utilisateur et retourne le token genere
    private String generateAndSaveActivationToken(User user) {

        String generatedToken = generateActivationToken(6);
        var token= Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiratedAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;

    }

    // cette methode permet de generer un token d'activation
    // elle prend en parametre la longueur du token a generer
    // et retourne le token genere
    private String generateActivationToken(int length) {
      String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom securerandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = securerandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}
