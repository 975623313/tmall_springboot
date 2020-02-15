package com.lizhifeng.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @description:管理员登陆
 * @author: 李志峰
 * @time: 2019/12/25 0:55
 */
@Entity
@Table(name = "admin")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "adminpwd")
    private String adminpwd;
    @Column(name = "salt")
    private String salt;
    @Column(name = "sign")
    private String sign;

    @Transient
    private String anonymousName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminpwd() {
        return adminpwd;
    }

    public void setAdminpwd(String adminpwd) {
        this.adminpwd = adminpwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAnonymousName(){
        if(null!=anonymousName)
            return anonymousName;
        if(null==name)
            anonymousName= null;
        else if(name.length()<=1)
            anonymousName = "*";
        else if(name.length()==2)
            anonymousName = name.substring(0,1) +"*";
        else {
            char[] cs =name.toCharArray();
            for (int i = 1; i < cs.length-1; i++) {
                cs[i]='*';
            }
            anonymousName = new String(cs);
        }
        return anonymousName;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminname='" + name + '\'' +
                ", adminpwd='" + adminpwd + '\'' +
                ", salt='" + salt + '\'' +
                ", sign='" + sign + '\'' +
                ", anonymousName='" + anonymousName + '\'' +
                '}';
    }

    public void setAnonymousName(String anonymousName) {
        this.anonymousName = anonymousName;
    }

}
