package com.bentoo.taskman.middlewares;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.bentoo.taskman.models.User;
import com.bentoo.taskman.repositories.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class AuthMiddleware extends OncePerRequestFilter {
    @Autowired
    IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        if(path.equals("/task")){
            String authorizationHeader = request.getHeader("Authorization");
            if(authorizationHeader==null){
                response.sendError(401);
                return;
            }
            String[] parts = authorizationHeader.split(" ");
            if(parts.length!=2){
                response.sendError(401,"Invalid token");
                return;
            }
            String shema = parts[0], token = parts[1];
            if(!shema.matches("Basic")){
                response.sendError(401,"Invalid token schema");
                return;
            }

            byte[] decoded = Base64.getDecoder().decode(token);
            String[] auth = new String(decoded).split(":");
            if(auth.length!=2){
                response.sendError(401,"Invalid token format");
                return;
            }

            String email = auth[0], password = auth[1];
            User userExists = userRepository.findByEmail(email);
            if(userExists==null){
                response.sendError(401,"User doesn't exists");
                return;
            }

            var compare = BCrypt.verifyer().verify(password.toCharArray(),userExists.password.toCharArray());
            if(!compare.verified){
                response.sendError(401,"User doesn't exists");
                return;
            }
            filterChain.doFilter(request,response);
        }
        filterChain.doFilter(request,response);
    }
}
