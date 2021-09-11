package ir.omidashouri.springbootbookseller.services;

import ir.omidashouri.springbootbookseller.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserEntityService {

    UserEntity saveUser(UserEntity newUser);

    Optional<UserEntity> findByUsername(String username);

    void makeAdmin(String username);
}
