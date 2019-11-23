package com.angevin.controller;

import com.angevin.controller.admin.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页控制器
 *
 * @auther Angevin
 * @date 2019年11月1日 16:41:43
 */
@Controller
public class HomeController extends BaseController {

    /**
     * 系统首页
     *
     * @return
     */
    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "home/index";
    }

    /**
     * 商品列表页
     *
     * @return
     */
    @GetMapping(value = {"/goods"})
    public String user() {
        return "site/goods";
    }

    /**
     * 商品列表页
     *
     * @return
     */
    @GetMapping(value = {"/reconcilia"})
    public String reconcilia() {
        return "site/reconcilia";
    }
    /**
     * 登录页
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    /**
     * 注册页面
     *
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "home/register";
    }

    /**
     * 注销
     *
     * @return
     */
    @GetMapping("/logout")
    public String logout() {
        super.getRequest().getSession().removeAttribute("user");
        return "home/login";
    }

}
