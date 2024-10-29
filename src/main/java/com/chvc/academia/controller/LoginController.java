package com.chvc.academia.controller;

//Clase S9

import com.chvc.academia.security.AuthRequest;
import com.chvc.academia.security.AuthResponse;
import com.chvc.academia.security.ErrorLogin;
import com.chvc.academia.security.JwtUtil;
import com.chvc.academia.service.IUserService;

import java.util.Date;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtUtil jwtUtil;

    private final IUserService service;

    @PostMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest authRequest) {
        return service.searchByUser(authRequest.getUsername())
                .doOnNext(rest -> System.out.println("valor user: " + rest.getUsername() + "valor user: " + rest.getPassword()))
                .map(userDetails -> {
                    //if (BCrypt.checkpw(authRequest.getPassword(), userDetails.getPassword())) {
                    if (authRequest.getPassword().equals(userDetails.getPassword()) ) {
                        String token = jwtUtil.generateToken(userDetails);
                        Date expiration = jwtUtil.getExpirationDateFromToken(token);

                        return ResponseEntity.ok(new AuthResponse(token, expiration));
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(new ErrorLogin("Bad Credentials", new Date()));
                    }
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
