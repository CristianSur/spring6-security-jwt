package com.example.sample.service;

import com.example.sample.entity.UserEntity;
import com.example.sample.exception.authentication.AlreadyRegisteredException;
import com.example.sample.model.authentication.AuthenticationRequest;
import com.example.sample.model.authentication.AuthenticationResponse;
import com.example.sample.model.authentication.RegisterRequest;
import com.example.sample.repository.UserRepository;
import com.example.sample.security.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {

        if (userRepository.findByEmail(registerRequest.email()).isPresent())
            throw new AlreadyRegisteredException("Email is already in use");

        if (userRepository.findByUsername(registerRequest.username()).isPresent())
            throw new AlreadyRegisteredException("User with same username is already registered");

        var user = UserEntity.builder()
                .email(registerRequest.email())
                .username(registerRequest.username())
                .password(passwordEncoder.encode(registerRequest.password()))
                .build();

        userRepository.save(user);

        var token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(),
                        authenticationRequest.password()
                )
        );

        var user = userRepository.findByUsername(authenticationRequest.username());

        return new AuthenticationResponse(jwtService.generateToken(user.orElseThrow()));
    }
}
