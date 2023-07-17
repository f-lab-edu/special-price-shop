package com.specialpriceshop.common.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.specialpriceshop.common.response.ErrorCode;
import com.specialpriceshop.common.response.ErrorResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response,
        final AuthenticationException authException) throws IOException {

        log.error("AuthenticationExcpeiton {}", authException);
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        final ObjectMapper mapper = new ObjectMapper();
        response.getWriter()
            .print(mapper.writeValueAsString(ErrorResponse.of(ErrorCode.UNAUTHORIZED)));
    }
}
