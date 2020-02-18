package com.lizhifeng.tmall.service;

import com.lizhifeng.tmall.dao.UserDAO;
import com.lizhifeng.tmall.pojo.Admin;
import com.lizhifeng.tmall.pojo.User;
import com.lizhifeng.tmall.util.Page4Navigator;
import com.lizhifeng.tmall.util.SpringContextUtil;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/23 16:02
 */
@Service
//@CacheConfig(cacheNames="users")
public class UserService {

    @Autowired
    UserDAO userDAO;

//    @Cacheable(key="'users-one-id-'+ #p0")
    public User get(int id) {
        return userDAO.findOne(id);
    }
    public boolean isExist(String name) {
        UserService userService = SpringContextUtil.getBean(UserService.class);
        User user = userService.getByName(name);
        return null!=user;
    }

//    @Cacheable(key="'users-one-name-'+ #p0")
    public User getByName(String name) {
        return userDAO.findByName(name);
    }



//    @Cacheable(key="'users-one-name-'+ #p0 +'-password-'+ #p1")
    public User get(String name, String password) {
        return userDAO.getByNameAndPassword(name,password);
    }

//    @Cacheable(key="'users-page-'+#p0+ '-' + #p1")
    public Page4Navigator<User> list(int start, int size, int navigatePages) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA =userDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
//    @Cacheable(key="'users-page-'+#p0+ '-' + #p1")
    public Page4Navigator<User> listBySign(int start, int size, int navigatePages,int sign) {

        sign--;
        int signFlag=sign;
        System.out.println("signFlag-----------"+signFlag);
        //查询条件
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //sign是个坑来的
                Predicate p1 = criteriaBuilder.equal(root.get("sign"),signFlag);
                return p1;
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA =userDAO.findAll(spec,pageable);
        List content = pageFromJPA.getContent();
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
//    @CacheEvict(allEntries=true)
    public void add(User user) {
        userDAO.save(user);
    }
//    @CacheEvict(allEntries=true)
    public void delete(int id) {
        userDAO.delete(id);
    }

//    @CacheEvict(allEntries=true)
    public void update(User bean) {

        String password = bean.getPassword();
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";

        String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();

        bean.setSalt(salt);
        bean.setPassword(encodedPassword);
        userDAO.save(bean);
    }
}
