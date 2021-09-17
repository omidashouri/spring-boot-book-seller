package ir.omidashouri.springbootbookseller.security;

import ir.omidashouri.springbootbookseller.model.UserEntity;
import ir.omidashouri.springbootbookseller.services.UserEntityService;
import ir.omidashouri.springbootbookseller.utils.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    UserEntityService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userEntityService.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));

        Set<GrantedAuthority> grantedAuthorities = Set.of(SecurityUtil.convertToAuthority(userEntity.getRole().name()));

        return UserPrincipal.builder()
                .userEntity(userEntity)
                .username(username)
                .password(userEntity.getPassword())
                .grantedAuthorities(grantedAuthorities)
                .build();
    }
}
