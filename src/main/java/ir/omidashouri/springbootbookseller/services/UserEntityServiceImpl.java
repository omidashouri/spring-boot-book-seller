package ir.omidashouri.springbootbookseller.services;


import ir.omidashouri.springbootbookseller.model.Role;
import ir.omidashouri.springbootbookseller.model.UserEntity;
import ir.omidashouri.springbootbookseller.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserEntityServiceImpl implements UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity saveUser(UserEntity newUser) {

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole(Role.USER);
        newUser.setCreateTime(LocalDateTime.now());
        return userEntityRepository.save(newUser);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userEntityRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public void makeAdmin(String username) {
        userEntityRepository.updateUserRole(username, Role.ADMIN);
    }
}
