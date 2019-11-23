package com.angevin.controller.admin;

import com.angevin.common.Constant;
import com.angevin.domain.Result;
import com.angevin.entity.User;
import com.angevin.service.UserService;
import com.angevin.utils.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther Angevin
 * @date 2019年11月1日 16:41:43
 */
@Controller
public class LoginController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password) {
        logger.info("username:" + username + ", password:" + password);
        User user = userService.findByName(username);
        if (user != null) {
            password = MD5.md5(Constant.SYS_AUTH_CODE_PREFIX+password+Constant.SYS_AUTH_CODE_SUFFIX);
            if (user.getPassword().equals(password)) {
                super.getRequest().getSession().setAttribute("user", user);
                return new Result(true, user.getUsername());
            }
        }
        return new Result(false, "登录失败");
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public Result register(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            userService.create(new User(username, password));
            super.getRequest().getSession().setAttribute("user", new User(username, password));
            return new Result(true, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, "发生未知错误");
    }

}
