package org.hare.core.sys.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import org.hare.core.sys.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author hare
 */
@ToString
public class LoginUserDTO implements UserDetails {
    private static final long serialVersionUID = -5082095911171826416L;

    private SysUser user;
    private Set<GrantedAuthority> authorities;

    public LoginUserDTO() {
    }

    public LoginUserDTO(SysUser userDO, Set<? extends GrantedAuthority> authorities) {
        this.user = userDO;
        this.authorities = Collections.unmodifiableSet(authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public SysUser getUser() {
        return user;
    }

}
