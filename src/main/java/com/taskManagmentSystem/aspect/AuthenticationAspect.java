package com.taskManagmentSystem.aspect;

import com.taskManagmentSystem.model.Authentication;
import com.taskManagmentSystem.model.DTO.response.AuthenticationResponse;
import com.taskManagmentSystem.service.AuthenticationService;
import com.taskManagmentSystem.service.JwtService;
import com.taskManagmentSystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(0)
@Slf4j
public class AuthenticationAspect {

    private final JwtService jwtService;
    private final AuthenticationService userService;

    @Autowired
    HttpServletRequest request;

    public AuthenticationAspect(JwtService jwtService, AuthenticationService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }
    @Pointcut("@annotation(Authorized)")
    private void AuthenticateMethod() {
    }

    @Around("AuthenticateMethod()")

    public Object AuthenticationMethod(ProceedingJoinPoint joinPoint ) throws Throwable {
        Authorized auth = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Authorized.class);
        log.info("Inizio dell'aspect : " +getClass() );
        log.info("Prendo la richiesta dall header");
        String authHeader = request.getHeader("Authorization");
        if(authHeader==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthenticationResponse.builder());
        }
        var jwt = authHeader.substring(7); //substring per escludere bearer più uno spazio "bearer "
        if(!jwtService.isTokenValid(jwt)){
            log.error("The Token is not valid. Verified in class: " + getClass());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthenticationResponse.builder());
        }
        log.info("Il token è valido!");
        log.info("Extracting the id from the token");
        int id = Integer.parseInt(jwtService.extractStringId(jwt));
        log.info("Finding the user with the extracted id!");
        Authentication userById = null;
        try{
            userById = userService.findByid(id);
            if(!Arrays.asList(auth.roles()).isEmpty() && !Arrays.stream(auth.roles()).toList().contains(userById.getRole())){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(AuthenticationResponse.builder());
            }
            log.info("Ho trovato l'user!");
        }catch(NullPointerException e){
            log.error("User not found!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthenticationResponse.builder());
        }
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Authentication) {
                args[i] = userById;
            }
        }
        log.info("Passo l'user al controller");
        return joinPoint.proceed(args);
    }
}
