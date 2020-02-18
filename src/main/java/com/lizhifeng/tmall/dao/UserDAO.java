package com.lizhifeng.tmall.dao;

import com.lizhifeng.tmall.pojo.User;
import com.lizhifeng.tmall.util.Page4Navigator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/23 15:59
 */
public interface UserDAO extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    User findByName(String name);

    User getByNameAndPassword(String name, String password);



}
