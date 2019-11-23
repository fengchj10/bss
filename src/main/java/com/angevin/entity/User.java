package com.angevin.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @auther Angevin
 * @date 2019年11月1日
 */
public class User implements Serializable {

    private Long id; //编号
    @NotNull
    private String username; //用户名
    @NotNull
    private String password; //密码

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
