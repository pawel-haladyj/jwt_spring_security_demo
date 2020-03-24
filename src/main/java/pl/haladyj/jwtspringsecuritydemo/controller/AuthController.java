package pl.haladyj.jwtspringsecuritydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.haladyj.jwtspringsecuritydemo.exception.AppException;
import pl.haladyj.jwtspringsecuritydemo.model.Role;
import pl.haladyj.jwtspringsecuritydemo.model.RoleName;
import pl.haladyj.jwtspringsecuritydemo.model.User;
import pl.haladyj.jwtspringsecuritydemo.payload.ApiResponse;
import pl.haladyj.jwtspringsecuritydemo.payload.JwtAuthenticationResponce;
import pl.haladyj.jwtspringsecuritydemo.payload.LoginRequest;
import pl.haladyj.jwtspringsecuritydemo.payload.SignUpRequest;
import pl.haladyj.jwtspringsecuritydemo.repository.RoleRepository;
import pl.haladyj.jwtspringsecuritydemo.repository.UserRepository;
import pl.haladyj.jwtspringsecuritydemo.security.JwtTokenProvider;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generetaToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponce(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        if(userRepository.existsByUserName(signUpRequest.getUserName())){
            return new ResponseEntity(new ApiResponse(false, "Username is already taken"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            return new ResponseEntity(new ApiResponse(false,"Email is already in use"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(
                signUpRequest.getName(),
                signUpRequest.getUserName(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword()
        );

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findRoleByName(RoleName.USER_ROLE).orElseThrow(
                ()-> new AppException("User role not set.")
        );

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/users/{username}")
                .buildAndExpand(result.getUserName())
                .toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "User Registered succesfully"));
    }

}
