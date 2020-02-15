package com.lizhifeng.tmall.dao;

import com.lizhifeng.tmall.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/25 23:04
 */
public interface AdminDao  extends JpaRepository<Admin,Integer> {
    Admin findByName(String name);

    Admin getByNameAndAdminpwd(String name, String adminpwd);
}
