package com.angevin.service.impl;

import com.angevin.common.Constant;
import com.angevin.entity.User;
import com.angevin.mapper.UserMapper;
import com.angevin.service.UserService;
import com.angevin.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther Angevin
 * @date 2019年11月1日
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public void create(User user) {
        String pwd = Constant.SYS_AUTH_CODE_PREFIX+user.getPassword()+Constant.SYS_AUTH_CODE_SUFFIX;
        user.setPassword(MD5.md5(pwd));
        userMapper.create(user);
    }

    @Override
    public void delete(Long... ids) {
        for (Long id : ids) {
            userMapper.delete(id);
        }
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }
}
