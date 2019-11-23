package com.angevin.controller.admin;

import com.angevin.entity.User;
import com.angevin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther Angevin
 * @date 2019年11月1日 16:41:43
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

}
