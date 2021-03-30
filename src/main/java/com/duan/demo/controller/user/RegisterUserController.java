package com.duan.demo.controller.user;

import com.duan.demo.model.Role;
import com.duan.demo.model.User;
import com.duan.demo.service.role.IRoleService;
import com.duan.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/register")
public class RegisterUserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
            Role role = iRoleService.findRoleByRoleName("ROLE_USER");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setImg("https://iupac.org/wp-content/uploads/2018/05/default-avatar.png");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (iUserService.checkUser(user.getUsername())) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                iUserService.save(user);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }

        }

//    public ResponseEntity<User> create(@RequestBody User user) {
//        Role role = iRoleService.findRoleByRoleName("ROLE_USER");
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//        user.setRoles(roles);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setUsername(user.getUsername());
//        user.setFullName(user.getFullName());
//        user.setPhoneNumber(user.getPhoneNumber());
//        user.setDate(user.getDate());
//        user.setAddress(user.getAddress());
//        user.setImg(user.getImg());
//        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
//        user.setCreatedAt(java.time.LocalDate.now());
//        user.setImageSource(user.getImageSource());
//        user.setPhone(user.getPhone());
//        user.setGender(user.getGender());
//        user.setEmail(user.getEmail());
//        user.setTitle(user.getTitle());
//        user.setIsDeleted(1);
//        iUserService.save(user);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PostMapping("/check")
    public ResponseEntity<Boolean> checkUserName(@RequestBody String username) {
        boolean isValid = iUserService.checkUser(username);
        return new ResponseEntity<>(!isValid, HttpStatus.OK);
    }
}
