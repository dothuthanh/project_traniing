package com.duan.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
//    @NotEmpty(message = "please enter username")
//    @Size(min = 2, max = 20, message = "Username length from 2 to 20 characters")
    private String username;

//    @NotEmpty(message = "Please enter password")
//    @Size(min = 6, max = 20, message = "Password length from 6 to 20 characters")
    private String password;
//
//    @NotEmpty(message = "please enter Full Name")
//    @Size(min = 2, max = 20, message = "Full Name length from 2 to 20 characters")
    private String fullName;
//
//    @NotEmpty(message = "please enter Phone")
//    @Size(min = 11, max = 13, message = "Phone length from 2 to 20 characters")
    private String phoneNumber;

//    @NotEmpty(message = "please enter email")
    private String email;
//
//    @NotEmpty(message = "please enter username")
//    @Size(min = 2, max = 20, message = "Username length from 2 to 20 characters")
    private String date;

//    @NotEmpty(message = "please enter address")
//    @Size(min = 2, max = 20, message = "Username length from 2 to 20 characters")
    private String address;

    private String img;

    @ManyToMany(fetch = FetchType.EAGER)

    private Set<Role> roles;
}
