package ir.omidashouri.springbootbookseller.services;

import ir.omidashouri.springbootbookseller.model.UserEntity;

public interface AuthenticationService {
    UserEntity singInAndReturnJWT(UserEntity signInRequest);
}
