package ir.omidashouri.springbootbookseller.controller;

import ir.omidashouri.springbootbookseller.model.UserEntity;
import ir.omidashouri.springbootbookseller.services.AuthenticationService;
import ir.omidashouri.springbootbookseller.services.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserEntityService userEntityService;

    @PostMapping("sign-up")
    public ResponseEntity<UserEntity> signUp(@RequestBody UserEntity userEntity){
        return null;
    }


}
