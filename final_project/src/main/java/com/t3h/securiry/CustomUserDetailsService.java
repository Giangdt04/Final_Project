package com.t3h.securiry;

import com.t3h.entity.RoleEntity;
import com.t3h.entity.UserEntity;
import com.t3h.repository.RoleRepository;
import com.t3h.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<RoleEntity> roleEntities = roleRepository.getRoleByUsername(username);
        UserEntity userEntity = userRepository.findUserByUsername(username);
        if(username != null || !(username.trim().isEmpty())){
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    userEntity.getUsername(),
                    userEntity.getPassword(),
                    convertStrToAuthor(roleEntities)
            );
            return userDetails;
        }else {
            throw new UsernameNotFoundException("Invalid user with username!");
        }
    }

    private Collection<? extends GrantedAuthority> convertStrToAuthor(Collection<RoleEntity> roles) {
        List<SimpleGrantedAuthority> roleConfigSecurity = new ArrayList<>();
        List<RoleEntity> roleEntities = roles.stream().toList();
        for (int i = 0; i < roles.size(); i++) {
            RoleEntity roleEntity = roleEntities.get(i);
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleEntity.getName());
            roleConfigSecurity.add(simpleGrantedAuthority);
        }
        return roleConfigSecurity;
    }

}
