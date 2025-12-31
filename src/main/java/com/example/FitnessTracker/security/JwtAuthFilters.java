package com.example.FitnessTracker.security;

import io.jsonwebtoken.Claims;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilters extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("AuthTokenFilter Called");

        try{
                String jwt = parseJwt(request);
                if(jwt !=null && jwtUtils.validateJwtToken(jwt)){
                    System.out.println("Token THERE : " + jwt);

                    String userId = jwtUtils.getUserIdFromToken(jwt);

                    Claims claims = jwtUtils.getAllClaims(jwt);
                    List<String> roles;
                    roles = claims.get("roles",List.class);


                    List<GrantedAuthority> authorities = List.of();
                    if(roles !=null){
                        authorities = roles
                                .stream()
                                .map(role -> (GrantedAuthority) new SimpleGrantedAuthority(jwt)).toList();
                    }

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userId,null,authorities);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }
        }catch(Exception e){
            log.error("Error while authorization");
            e.printStackTrace();
        }
        filterChain.doFilter(request,response);
    }


    private String parseJwt(HttpServletRequest request){
        String jwt = jwtUtils.getJwtFromHeader(request);
        return  jwt;

}

}
