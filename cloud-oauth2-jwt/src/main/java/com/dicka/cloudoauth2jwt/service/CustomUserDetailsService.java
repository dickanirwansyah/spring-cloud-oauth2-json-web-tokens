package com.dicka.cloudoauth2jwt.service;

import com.dicka.cloudoauth2jwt.entity.Users;
import com.dicka.cloudoauth2jwt.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** userDetailsService fetching websecurityconfiguration **/
@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username);
        /** check users **/
        if (users == null){
            throw new BadCredentialsException("BAD CREDENTIALS.");
        }

        new AccountStatusUserDetailsChecker()
                .check(users);

        return users;
    }
}
