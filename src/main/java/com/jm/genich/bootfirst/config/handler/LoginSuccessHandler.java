package com.jm.genich.bootfirst.config.handler;

import com.jm.genich.bootfirst.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();
        if (user.getRoles().stream().map(t -> t.toString()).collect(Collectors.toSet()).contains("ROLE_ADMIN"))
        {
            httpServletResponse.sendRedirect("/admin");
        }
        else {
            httpServletResponse.sendRedirect("/user?id=" + user.getId());
        }
    }
}