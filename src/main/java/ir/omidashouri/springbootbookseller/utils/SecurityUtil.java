package ir.omidashouri.springbootbookseller.utils;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

public class SecurityUtil {

    public static final String ROLE_PREFIX = "ROLE_";
    private static final String AUTH_HEADER = "authorization";
    private static final String AUTH_TOKEN_TYPE = "Bearer";
    private static final String AUTH_TOKEN_PREFIX = AUTH_TOKEN_TYPE + " ";

    public static SimpleGrantedAuthority convertToAuthority(String role) {

        String formattedRole = role.startsWith(ROLE_PREFIX) ? role : ROLE_PREFIX + role;
        return new SimpleGrantedAuthority(formattedRole);

    }

    public static String extractAuthTokenFromRequest(HttpServletRequest httpServletRequest) {

        String bearerToken = httpServletRequest.getHeader(AUTH_HEADER);
        if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
