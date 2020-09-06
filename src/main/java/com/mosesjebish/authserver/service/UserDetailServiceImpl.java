package com.mosesjebish.authserver.service;

import com.mosesjebish.authserver.model.AuthUserDetail;
import com.mosesjebish.authserver.model.User;
import com.mosesjebish.authserver.repository.UserDetailRepository;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserDetailRepository userDetailRepository;

    public UserDetailServiceImpl(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDetailRepository.findByUsername(name);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("User or password wrong"));

        UserDetails userDetails = new AuthUserDetail(optionalUser.get());

        new AccountStatusUserDetailsChecker().check(userDetails);

        return userDetails;
    }
}
