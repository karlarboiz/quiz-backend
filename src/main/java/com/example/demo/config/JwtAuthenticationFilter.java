package com.example.demo.config;

import com.example.demo.model.dto.UserInformationInOutDto;
import com.example.demo.model.service.UserInformationService;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserInformationService userInformationService;
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(
            @NonNull  HttpServletRequest request,
            @NonNull  HttpServletResponse response,
            @NonNull  FilterChain filterChain) throws ServletException, IOException,FileUploadException{

        try {
            final String authHeader = request.getHeader("Authorization");

            final String jwt;

            final String userEmail;

            final String username;

            if(authHeader == null ||
                    !authHeader.startsWith("Bearer")){
                return;
            }

            jwt = authHeader.substring(7);
            username = jwtService.extractUsername(jwt);

            UserInformationInOutDto userInformationInOutDto = userInformationService.getUserDetailsUsingUsername(username);
            userEmail = userInformationInOutDto.getUserInformationObj().getEmail();
            if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null &&
                    userInformationInOutDto.getUserInformationObj() != null){

                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

                if(jwtService.isTokenValid(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

        }catch (MalformedJwtException | InvalidAlgorithmParameterException | NoSuchPaddingException |
                IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            System.out.println(e.getMessage());
        }

        filterChain.doFilter(request,response);

    }
}
