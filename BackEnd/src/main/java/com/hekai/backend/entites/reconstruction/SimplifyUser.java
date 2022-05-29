package com.hekai.backend.entites.reconstruction;

import com.hekai.backend.entites.User;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
public class SimplifyUser {
    private int id;
    private String account;
    private String name;
    private String sex;
    private int age;
    private String phone;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public SimplifyUser ConvertFromUser(User user){
        setId(user.getId());
        setAccount(user.getAccount());
        setName(user.getName());
        if(user.getSex()==1){
            setSex("男");
        }else{
            setSex("女");
        }
        setAge(user.getAge());
        setPhone(user.getPhone());
        setEmail(user.getEmail());
        return this;
    }
}
