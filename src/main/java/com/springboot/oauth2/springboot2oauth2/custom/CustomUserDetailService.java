package com.springboot.oauth2.springboot2oauth2.custom;

import com.springboot.oauth2.springboot2oauth2.entity.Role;
import com.springboot.oauth2.springboot2oauth2.entity.User;
import com.springboot.oauth2.springboot2oauth2.repository.RepositoryUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Transactional
@Service
@Component
public class CustomUserDetailService implements UserDetailsService{

    private final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private RepositoryUser repositoryUser;

    @Override
    public UserDetails loadUserByUsername(String login) {

        logger.info("proses authentication user {} ", login);

        String lowerCaseLogin = login.toLowerCase();
        User entityUser;

        if(lowerCaseLogin.contains("@")){
            entityUser = repositoryUser.findByEmail(lowerCaseLogin);
        }else{
            entityUser = repositoryUser.findByUsernameInCaseSensitive(lowerCaseLogin);
        }

        if(entityUser == null){
            throw new CustomUserNotFoundException("User "+lowerCaseLogin+" Not found !");
        }else if(!entityUser.isActivated()){
            throw new CustomUserNotFoundException("User "+lowerCaseLogin+" Not active");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : entityUser.getRoles()){
            GrantedAuthority hasRole = new SimpleGrantedAuthority(role.getName());
            authorities.add(hasRole);
        }

        return new org.springframework.security.core.userdetails
                .User(entityUser.getUsername(), entityUser.getPassword(), authorities);
    }
}
