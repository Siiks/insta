package com.example.inst.Controller;

import com.example.inst.Model.Accounts;
import com.example.inst.Model.Settings;
import com.example.inst.Payload.Request.LoginRequest;
import com.example.inst.Payload.Request.SignupRequest;
import com.example.inst.Payload.Response.JwtResponse;
import com.example.inst.Payload.Response.Response;
import com.example.inst.Repository.AccountsRepository;
import com.example.inst.Repository.SettingsRepository;
import com.example.inst.Security.Services.AccountDetailsImpl;
import com.example.inst.Security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AccountsRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private SettingsRepository settingsRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @ModelAttribute LoginRequest
                                                      loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        AccountDetailsImpl userDetails = (AccountDetailsImpl)
                authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getTlf()));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @ModelAttribute SignupRequest
                                                  signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(Response.errorResponse(HttpStatus.CONFLICT.value(),"Error: Username isalready taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(Response.errorResponse(HttpStatus.CONFLICT.value(),"Error: Email is already in use!"));
        }

        Accounts accounts = new Accounts(
                signUpRequest.getEmail(),signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getTlf());


        userRepository.save(accounts);
        settingsRepository.save(new Settings(true, true, true, accounts));
        return new ResponseEntity<>("User registered successfully!",
                HttpStatus.OK);
    }
}