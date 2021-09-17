package ir.omidashouri.springbootbookseller.security.jwt;

import ir.omidashouri.springbootbookseller.security.UserPrincipal;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface JwtProviderInterface {
    String generateToken(UserPrincipal userPrincipal);

    Authentication getAuthentication(HttpServletRequest httpServletRequest);

    boolean validateToken(HttpServletRequest httpServletRequest);
}
