package ir.omidashouri.springbootbookseller.utils;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SecurityUtil {

    public static final String ROLE_PREFIX = "ROLE_";

    public static SimpleGrantedAuthority convertToAuthority(String role) {

        String formattedRole = role.startsWith(ROLE_PREFIX) ? role : ROLE_PREFIX + role;
        return new SimpleGrantedAuthority(formattedRole);

    }
}
