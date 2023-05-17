package pl.nlo.jira.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author marcin
 * @since 15.03.2023
 */
@Service
public class JwtService {

	private static final String SECRET_KEY = "5A7134743677397A24432646294A404E635266556A586E327235753878214125";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public JwtToken generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	public JwtToken generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		LocalDateTime currentDate = LocalDateTime.now();
		Date issuedAt = java.sql.Timestamp.valueOf(currentDate);
		Date expiresAt = java.sql.Timestamp.valueOf(currentDate.plusMinutes(30));
		String tokenStr = Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(issuedAt)
				.setExpiration(expiresAt)
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
		return JwtToken.builder()
				.token(tokenStr)
				.expiration(expiresAt.toString())
				.build();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);

		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();

	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
