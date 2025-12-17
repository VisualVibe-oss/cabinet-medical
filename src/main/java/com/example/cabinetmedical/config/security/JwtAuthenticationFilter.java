package com.example.cabinetmedical.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        try{
            String jwt=getJwtFromRequest(req);

            if(!StringUtils.hasText(jwt)) {
                chain.doFilter(req, res);
                return;
            }


            if(jwtTokenProvider.validateToken(jwt)) {
                String email=jwtTokenProvider.getEmailFromToken(jwt);
                String role=jwtTokenProvider.getRoleFromToken(jwt);


                SimpleGrantedAuthority authority=new SimpleGrantedAuthority("ROLE_"+role);
                UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(email,null,Collections.singletonList(authority));

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {;
        }

        chain.doFilter(req, res);
    }

    private String getJwtFromRequest(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}