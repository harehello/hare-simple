package org.hare.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hare.framework.web.domain.R;
import org.hare.framework.web.domain.ResultStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权失败
 * @author hare
 */
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        R r = R.of(ResultStatus.FORBIDDEN);
        new ObjectMapper().writeValue(response.getWriter(), r);
    }
}
