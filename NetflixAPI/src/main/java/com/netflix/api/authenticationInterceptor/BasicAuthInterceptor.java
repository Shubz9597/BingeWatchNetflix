package com.netflix.api.authenticationInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BasicAuthInterceptor {
    boolean headerCheck(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException;
}
