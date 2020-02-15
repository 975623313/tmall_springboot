package com.lizhifeng.tmall.dao;

import com.lizhifeng.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/23 15:59
 */
public interface UserDAO extends JpaRepository<User,Integer>{

    User findByName(String name);

    User getByNameAndPassword(String name, String password);

}
