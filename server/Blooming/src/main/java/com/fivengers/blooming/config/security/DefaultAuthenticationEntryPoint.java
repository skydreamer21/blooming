package com.fivengers.blooming.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import com.fivengers.blooming.global.exception.global.UnknownServerLogicException;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.global.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@RequiredArgsConstructor
public class DefaultAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    public static final String NOT_FOUND_SERVLET_MSG = "해당 접근은 유효하지 않습니다.";

    private final HttpRequestEndpointChecker endpointChecker;
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException arg2) throws IOException {
        if (!endpointChecker.existsEndpoint(request)) {
            responseException(response);
            return;
        }

        super.commence(request, response, arg2);
    }

    private void responseException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(
                ApiResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse(
                                ExceptionCode.UNREGISTERED_EXCEPTION.getErrorCode(),
                                NOT_FOUND_SERVLET_MSG))));
        writer.flush();
    }
}
