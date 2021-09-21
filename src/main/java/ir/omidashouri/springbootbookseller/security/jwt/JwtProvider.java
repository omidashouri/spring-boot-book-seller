package ir.omidashouri.springbootbookseller.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ir.omidashouri.springbootbookseller.security.UserPrincipal;
import ir.omidashouri.springbootbookseller.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtProvider implements JwtProviderInterface {

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_IN_MS;

    @Override
    public String generateToken(UserPrincipal userPrincipal) {

        String authorities = userPrincipal
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("roles", authorities)
                .claim("userId", userPrincipal.getId())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }


    @Override
    public Authentication getAuthentication(HttpServletRequest httpServletRequest) {
        Claims claims = this.extractClaims(httpServletRequest);

        if (claims == null)
            return null;

        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);

        Set<GrantedAuthority> grantedAuthorities =
                Arrays.stream(claims.get("roles").toString().split(","))
                        .map(SecurityUtil::convertToAuthority)
                        .collect(Collectors.toSet());

        UserDetails userDetails = UserPrincipal.builder()
                .username(username)
                .grantedAuthorities(grantedAuthorities)
                .id(userId)
                .build();

        if (username == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, grantedAuthorities);
    }

    @Override
    public boolean validateToken(HttpServletRequest httpServletRequest) {
        Claims claims = this.extractClaims(httpServletRequest);

        if (claims == null)
            return false;

        if (claims.getExpiration().before(new Date()))
            return false;

        return true;
    }

    protected Claims extractClaims(HttpServletRequest httpServletRequest) {
        String token = SecurityUtil.extractAuthTokenFromRequest(httpServletRequest);

        if (token == null) {
            return null;
        }

        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

}
