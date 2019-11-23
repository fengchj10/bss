package com.angevin.mapper;

import com.angevin.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther Angevin
 * @date 2019年11月1日
 */
@Mapper
public interface UserMapper {

    List<User> findAll();

    List<User> findById(Long id);

    void create(User user);

    void delete(Long id);

    void update(User user);

    User findByName(String name);
}
