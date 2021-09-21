package ir.omidashouri.springbootbookseller.services;

import ir.omidashouri.springbootbookseller.model.UserEntity;
import ir.omidashouri.springbootbookseller.security.UserPrincipal;
import ir.omidashouri.springbootbookseller.security.jwt.JwtProviderInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final AuthenticationManager authenticationManager;
    private final JwtProviderInterface jwtProviderInterface;

    @Override
    public UserEntity singInAndReturnJWT(UserEntity signInRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProviderInterface.generateToken(userPrincipal);

        UserEntity signInUser = userPrincipal.getUserEntity();
        signInUser.setToken(jwt);

        return signInUser;
    }

}
