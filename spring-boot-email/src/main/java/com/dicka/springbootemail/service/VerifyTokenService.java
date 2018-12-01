package com.dicka.springbootemail.service;

import com.dicka.springbootemail.Users;
import com.dicka.springbootemail.UsersRepository;
import com.dicka.springbootemail.VerifyToken;
import com.dicka.springbootemail.VerifyTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerifyTokenService {

    private final UsersRepository usersRepository;
    private final VerifyTokenRepository verifyTokenRepository;
    private final EmailSenderService emailSenderService;

    @Autowired
    public VerifyTokenService(UsersRepository usersRepository, VerifyTokenRepository verifyTokenRepository,
                              EmailSenderService emailSenderService){
        this.usersRepository = usersRepository;
        this.verifyTokenRepository = verifyTokenRepository;
        this.emailSenderService = emailSenderService;
    }

    public void createVerification(String email){
        List<Users> users = usersRepository.findUsersByEmail(email);
        Users usr;
        if (users.isEmpty()){
            usr = new Users();
            usr.setEmail(email);
            usersRepository.save(usr);
        }else {
            usr = users.get(0);
        }

        List<VerifyToken> verifyTokens = verifyTokenRepository.findByUsersEmail(email);
        VerifyToken verifyToken;
        if (verifyTokens.isEmpty()){
            verifyToken = new VerifyToken();
            verifyToken.setUsers(usr);
            verifyTokenRepository.save(verifyToken);
        }else {
            verifyToken = verifyTokens.get(0);
        }

        emailSenderService.sendVerificationEmail(email,
                verifyToken.getToken());
    }

    public ResponseEntity<String> verifyEmail(String token){

        /** check token null **/
        List<VerifyToken> verifyTokens = verifyTokenRepository.findByToken(token);
        if (verifyTokens.isEmpty()){
            return ResponseEntity.badRequest()
                    .body("Invalid token.");
        }

        /** check expirate token **/
        VerifyToken verifyToken = verifyTokens.get(0);
        if(verifyToken.getExpiredDateTime().isBefore(LocalDateTime.now())){
            return ResponseEntity.unprocessableEntity()
                    .body("Sorry token is expirate.");
        }

        /** save data token **/
        verifyToken.setConfirmedDateTime(LocalDateTime.now());
        verifyToken.setStatus(VerifyToken.STATUS_VERIFIED);
        verifyToken.getUsers().setActive(true);
        verifyTokenRepository.save(verifyToken);

        return ResponseEntity
                .ok("Congratulations you have successfully verified your email.");
    }
}
