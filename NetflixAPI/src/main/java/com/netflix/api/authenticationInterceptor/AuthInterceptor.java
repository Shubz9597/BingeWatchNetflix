package com.netflix.api.authenticationInterceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthInterceptor implements HandlerInterceptor, BasicAuthInterceptor {

    @Override
    public boolean headerCheck(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String authHeader = request.getHeader("X-Auth_Token");
        if(authHeader != null) {
            return true;
        }

        else {
            response.sendError(401, "Unauthorized Access");
            return false;
        }
    }
}
