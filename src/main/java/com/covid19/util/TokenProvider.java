package com.covid19.util;

import com.covid19.app.AppProperties;
import com.covid19.common.UserTokenIssue;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Ayaz.Ahmed
 * @description A class that provide token and for validations, validity of token is five.
 */
@Component
public class TokenProvider {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Autowired
    private AppProperties appProperties;

    public String getUsernameFromToken(final String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssueAtFromToken(final String token) {
        Claims claims = getAllClaimsFromToken(token);
        return new Date((long)claims.get("issueAt"));
    }

    public Date getExpirationDateFromToken(final String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(final String token) {
        return Jwts.parser().setSigningKey(appProperties.getJwtSecret()).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(final String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, final String subject) {
        Date issueAt = new Date();
        Date expiration = new Date(issueAt.getTime() + JWT_TOKEN_VALIDITY * 1000);

        UserTokenIssue.setUserTokenIssue(subject, issueAt);
        claims.put("issueAt", issueAt);

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(issueAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, appProperties.getJwtSecret()).compact();
    }

    public Boolean validateToken(final String token, final UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)
                && isLatestToken(token,username));
    }

    private Boolean isLatestToken(final String token, final String userName){
        Date issueAt = getIssueAtFromToken(token);
        Date latestIssueDate = UserTokenIssue.getLatestDate(userName);

        return latestIssueDate != null && issueAt.compareTo(latestIssueDate) == 0;
    }
}
