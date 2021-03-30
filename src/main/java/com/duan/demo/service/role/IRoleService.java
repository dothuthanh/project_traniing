package com.duan.demo.service.role;

import com.duan.demo.model.Role;
import com.duan.demo.service.IGeneralService;
import org.springframework.stereotype.Repository;


public interface IRoleService extends IGeneralService<Role> {
    Role findRoleByRoleName(String roleName);
}
