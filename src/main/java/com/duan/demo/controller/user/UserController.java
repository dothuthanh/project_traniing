package com.duan.demo.controller.user;

import com.duan.demo.model.User;
import com.duan.demo.service.role.IRoleService;
import com.duan.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public ResponseEntity<Iterable<User>> findAllUser(){
        return new ResponseEntity<>(iUserService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> findByUserName(@PathVariable("username") String username){
        return new ResponseEntity<>(iUserService.findByUsername(username), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User user1 = iUserService.findByUsername(user.getUsername());
        user1.setFullName(user.getFullName());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setDate(user.getDate());
        user1.setEmail(user.getEmail());
        user1.setAddress(user.getAddress());
        user1.setImg(user.getImg());
        iUserService.save(user1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/resetPassword/{username}")
    public ResponseEntity<User> resetPassword(@PathVariable("username") String username , @RequestBody String password ) {
        User userFind = iUserService.findByUsername(username);
        userFind.setPassword(passwordEncoder.encode(password));
        iUserService.save(userFind);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PostMapping("/resetPassword")
    public ResponseEntity<Boolean> checkPassword(@RequestBody User user) {
        User userFind = iUserService.findByUsername(user.getUsername());
        userFind.setComfirmPassword(passwordEncoder.encode(user.getComfirmPassword()));
        iUserService.save(userFind);
        boolean isMatched = iUserService.checkPassword(user.getUsername(), user.getComfirmPassword());
        return new ResponseEntity<>(isMatched, HttpStatus.OK);
    }

}
