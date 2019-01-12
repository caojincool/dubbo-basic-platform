package com.basic.framework.security.jwt.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.basic.framework.security.jwt.JwtUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * jwt-token工具类
 * @author lzj
 */
public class JwtUtils {

	public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";

	private static final String CLAIM_KEY_IP = "ip";
	private static final String CLAIM_KEY_USER_ID = "userId";
	private static final String CLAIM_KEY_ACCOUNTSET_ID = "accountSetId";
	private static final String CLAIM_KEY_USER_TEXT = "userText";
	private static final String CLAIM_KEY_USER_TYPE = "userType";
	//private static final String CLAIM_KEY_AUTHORITIES = "scope";
	//private static final String CLAIM_KEY_ACCOUNT_ENABLED = "enabled";
	//private static final String CLAIM_KEY_ACCOUNT_NON_LOCKED = "non_locked";
	//private static final String CLAIM_KEY_ACCOUNT_NON_EXPIRED = "non_expired";

	// 别人篡改数据，但是签名的密匙是在服务器存储，密匙不同，生成的sign也不同。
	// 所以根据sign的不同就可以知道是否篡改了数据。
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.access_token.expiration}")
	private Long accessTokenExpiration;

	@Value("${jwt.refresh_token.expiration}")
	private Long refreshTokenExpiration;

	private static final SignatureAlgorithm SINGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

	public JwtUserDetails getUserFromToken(String token) {
		JwtUserDetails user;
		try {
			String username = "";
			final Claims claims = getClaimsFromToken(token);
			long userId = getUserIdFromToken(token);
			if (claims != null) {
				username = claims.getSubject();
				/*List<?> roles = (List<?>) claims.get(CLAIM_KEY_AUTHORITIES);
				Collection<? extends GrantedAuthority> authorities = parseArrayToAuthorities(roles);*/
				//Collection<GrantedAuthority> grantedAuths = UserUtils.obtionGrantedAuthorities(username);
				String ip=(String) claims.get(CLAIM_KEY_IP);
				String userText=(String) claims.get(CLAIM_KEY_USER_TEXT);
				Long accountSetId=Long.valueOf(claims.get(CLAIM_KEY_ACCOUNTSET_ID).toString());
				Integer userType=Integer.valueOf(claims.get(CLAIM_KEY_USER_TYPE).toString());
				/*boolean accountEnabled = (Boolean) claims.get(CLAIM_KEY_ACCOUNT_ENABLED);
				boolean accountNonLocked = (Boolean) claims.get(CLAIM_KEY_ACCOUNT_NON_LOCKED);
				boolean accountNonExpired = (Boolean) claims.get(CLAIM_KEY_ACCOUNT_NON_EXPIRED);*/
				user = new JwtUserDetails(ip,userId, username, "password",accountSetId,userText,userType);
			} else {
				user = null;
			}
		} catch (Exception e) {
			user = null;
		}
		return user;
	}

	public long getUserIdFromToken(String token) {
		long userId;
		try {
			final Claims claims = getClaimsFromToken(token);
			if (claims != null) {
				//userId = (Long) claims.get(CLAIM_KEY_USER_ID);
				userId = Long.valueOf(claims.get(CLAIM_KEY_USER_ID).toString());
			} else {
				userId = 0;
			}

		} catch (Exception e) {
			userId = 0;
		}
		return userId;
	}

	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = getClaimsFromToken(token);
			if (claims != null) {
				username = claims.getSubject();
			} else {
				username = null;
			}
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = getClaimsFromToken(token);
			if (claims != null) {
				created = claims.getIssuedAt();
			} else {
				created = null;
			}
		} catch (Exception e) {
			created = null;
		}
		return created;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			if (claims != null) {
				expiration = claims.getExpiration();
			} else {
				expiration = null;
			}
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	private Date generateExpirationDate(long expiration) {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	public String generateAccessToken(UserDetails userDetails) {
		JwtUserDetails user = (JwtUserDetails) userDetails;
		Map<String, Object> claims = generateClaims(user);
		//claims.put(CLAIM_KEY_AUTHORITIES,
	    //JSON.toJSON(authoritiesToArray(user.getAuthorities())));
		return generateAccessToken(user.getUsername(), claims);
	}

	private Map<String, Object> generateClaims(JwtUserDetails user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_IP, user.getIp());
		claims.put(CLAIM_KEY_USER_ID, user.getUserId());
		claims.put(CLAIM_KEY_ACCOUNTSET_ID, user.getAccountSetId());
		claims.put(CLAIM_KEY_USER_TEXT, user.getUserText());
		claims.put(CLAIM_KEY_USER_TYPE, user.getUserType());
		/*claims.put(CLAIM_KEY_ACCOUNT_ENABLED, user.isEnabled());
		claims.put(CLAIM_KEY_ACCOUNT_NON_LOCKED, user.isAccountNonLocked());
		claims.put(CLAIM_KEY_ACCOUNT_NON_EXPIRED, user.isAccountNonExpired());*/
		return claims;
	}

	private String generateAccessToken(String subject, Map<String, Object> claims) {
		return generateToken(subject, claims, accessTokenExpiration);
	}

	/*
	 * private List authoritiesToArray(Collection<? extends GrantedAuthority>
	 * authorities) { List<String> list = new ArrayList<>(); for (GrantedAuthority
	 * ga : authorities) { list.add(ga.getAuthority()); } return list; }
	 */

	private Collection<? extends GrantedAuthority> parseArrayToAuthorities(List<?> roles) {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		SimpleGrantedAuthority authority;
		for (Object role : roles) {
			authority = new SimpleGrantedAuthority(role.toString());
			authorities.add(authority);
		}
		return authorities;
	}

	public String generateRefreshToken(UserDetails userDetails) {
		JwtUserDetails user = (JwtUserDetails) userDetails;
		Map<String, Object> claims = generateClaims(user);
		// 只授于更新 token 的权限
		String roles[] = new String[] { JwtUtils.ROLE_REFRESH_TOKEN };
		//claims.put(CLAIM_KEY_AUTHORITIES, JSON.toJSON(roles));
		return generateRefreshToken(user.getUsername(), claims);
	}

	private String generateRefreshToken(String subject, Map<String, Object> claims) {
		return generateToken(subject, claims, refreshTokenExpiration);
	}

	public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
		final Date created = getCreatedDateFromToken(token);
		return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset) && (!isTokenExpired(token));
	}

	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = getClaimsFromToken(token);
			if (claims != null) {
				refreshedToken = generateAccessToken(claims.getSubject(), claims);
			} else {
				refreshedToken = null;
			}

		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	private String generateToken(String subject, Map<String, Object> claims, long expiration) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setId(UUID.randomUUID().toString())
				.setIssuedAt(new Date()).setExpiration(generateExpirationDate(expiration))
				.compressWith(CompressionCodecs.DEFLATE).signWith(SINGNATURE_ALGORITHM, secret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		JwtUserDetails user = (JwtUserDetails) userDetails;
		final long userId = getUserIdFromToken(token);
		final String username = getUsernameFromToken(token);
		// final Date created = getCreatedDateFromToken(token);
		// final Date expiration = getExpirationDateFromToken(token);
		return (userId == user.getUserId() && username.equals(user.getUsername()) && !isTokenExpired(token));
	}

}