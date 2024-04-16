package org.hare.framework.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;

/**
 * @author wang cheng
 */
@Component
public class JwtBuilder {

    /**
     * 这个key的值会被填充到 {@link GrantedAuthority authorities} 所以要和
     * {@link JwtGrantedAuthoritiesConverter#WELL_KNOWN_AUTHORITIES_CLAIM_NAMES}
     * 保持一致
     * {@link JwtGrantedAuthoritiesConverter#convert}
     */
    private static final String WELL_KNOWN_AUTHORITIES_CLAIM_NAMES = "scope";


    /**
     * 过期时间（秒）
     */
    @Value("${jwt.expire-time}")
    private Long expireTime;

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public JwtBuilder(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    /**
     * 根据角色构建jwt令牌，authorities要使用 " "分割，参考 {@link JwtGrantedAuthoritiesConverter#getAuthorities}
     * @param userId 用户主键
     * @param authorities 用户权限或角色
     * @return jwt令牌
     */
    public String build(Long userId, Collection<? extends String> authorities) {

        String scope = String.join(" ", authorities);

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expireTime))
                .subject(userId.toString())
                .claim(WELL_KNOWN_AUTHORITIES_CLAIM_NAMES, scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    /**
     * 从令牌中获取数据声明
     * @param token 令牌
     * @return 数据声明
     */
    public Map<String, Object> getClaims(String token) {
        return jwtDecoder.decode(token).getClaims();
    }

    /**
     * 从令牌中获取主体数据
     * @param token 牌
     * @return 主体数据
     */
    public String getSubject(String token) {
        return jwtDecoder.decode(token).getSubject();
    }
}
