package com.lizhifeng.tmall.service;

import com.lizhifeng.tmall.dao.AdminDao;
import com.lizhifeng.tmall.pojo.Admin;
import com.lizhifeng.tmall.util.Page4Navigator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/29 16:56
 */
@Service
//@CacheConfig(cacheNames="admins")
public class SuperService {
    @Autowired
    private AdminDao adminDAO;
//    @Cacheable(key="'admins-id-'+#p0+'-page-'+#p1 + '-' + #p2 ")
    public Page4Navigator<Admin> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(start, size,sort);

        Page pageFromJPA =adminDAO.findAll(pageable);


        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public boolean isExistAdmin(String name) {
//        UserService userService = SpringContextUtil.getBean(UserService.class);
        Admin admin = adminDAO.findByName(name);
        return null!=admin;
    }
//    @CacheEvict(allEntries=true)
    public void add(Admin admin) {
        adminDAO.save(admin);
    }

//    @CacheEvict(allEntries=true)
    public void delete(int id) {
        adminDAO.delete(id);
    }
//    @Cacheable(key="'admin-one-'+ #p0")
    public Admin get(int id) {
        System.out.println(id);
        return adminDAO.getOne(id);
    }

    @CacheEvict(allEntries=true)
    public void update(Admin bean) {

        String password = bean.getAdminpwd();
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";

        String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();

        bean.setSalt(salt);
        bean.setAdminpwd(encodedPassword);
        adminDAO.save(bean);
    }


}
