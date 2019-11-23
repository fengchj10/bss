package com.angevin.service;

import com.angevin.entity.User;

/**
 * @auther Angevin
 * @date 2019年11月1日
 */
public interface UserService extends BaseService<User> {

    User findByName(String name);
}
