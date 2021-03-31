package com.duan.demo.service.user;

import com.duan.demo.model.User;
import com.duan.demo.model.UserPrinciple;
import com.duan.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return UserPrinciple.build(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean checkUser(String username) {
        return userRepository.findByUsername(username) != null;

    }

    @Override
    public boolean checkPassword(String username, String password) {
        String userPassword = userRepository.findByUsername(username).getPassword();
        CharSequence passwordEncode = password;
        if (passwordEncoder.matches(passwordEncode, userPassword)) {
            return true;
        } else {
            return false;
        }
    }
}
