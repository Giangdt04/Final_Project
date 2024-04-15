package com.t3h.controller;

import com.t3h.model.dto.RoleDto;
import com.t3h.model.dto.UserDto;
import com.t3h.service.IUserService;
import com.t3h.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = {"/", "/login"})
    public String loginPage(){return "/auth/sign_in";}

    @GetMapping(value = "/process-after-login")
    public String processAfterLogin(){
        // lấy ra quyền của user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String findName = authentication.getName();
        // nếu là role admin
        UserDto userDto = userService.findUserByUsername(findName);

        if(CollectionUtils.isEmpty(userDto.getRoleDtos())){
            return "redirect:/logout";
        }
        boolean isAdmin = false;
        boolean isStaff = false;
        for (int i = 0; i < userDto.getRoleDtos().size(); i++) {
            RoleDto roleDto = userDto.getRoleDtos().get(i);
            if (Constant.ROLE_ADMIN.equalsIgnoreCase(roleDto.getName())){
                isAdmin=true;
            }
            if (Constant.ROLE_STAFF.equalsIgnoreCase(roleDto.getName())){
                isStaff=true;
            }
        }
        if(isAdmin){
            return "redirect:/views/home/admin";
        }
        if(isStaff){
            return "redirect:/staff/home";
        }
        return "redirect:/logout";
    }
}
