package org.hare.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hare.framework.web.domain.R;
import org.hare.framework.web.domain.ResultStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败
 * @author hare
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        R r = R.of(ResultStatus.UNAUTHORIZED);
        r.msg(authException.getMessage());
        new ObjectMapper().writeValue(response.getWriter(), r);
    }
}