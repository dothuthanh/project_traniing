package com.duan.demo.service.user;

import com.duan.demo.model.User;
import com.duan.demo.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);

}
