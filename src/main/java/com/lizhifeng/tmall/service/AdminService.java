package com.lizhifeng.tmall.service;

import com.lizhifeng.tmall.dao.AdminDao;
import com.lizhifeng.tmall.pojo.Admin;
import com.lizhifeng.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/25 23:04
 */
@Service
@CacheConfig(cacheNames="admins")
public class AdminService {

    @Autowired
    private AdminDao adminDAO;


    public Admin getByAdminname(String name) {
        return adminDAO.findByName(name);
    }
    @Cacheable(key="'admins-page-'+#p0+ '-' + #p1")
    public Page4Navigator<Admin> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(start, size,sort);

        Page pageFromJPA =adminDAO.findAll(pageable);
        List<Admin> content = pageFromJPA.getContent();
        for (Admin admin:content){
            System.out.println(admin+"11122");

        }
//        System.out.println("66222"+content);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
}
