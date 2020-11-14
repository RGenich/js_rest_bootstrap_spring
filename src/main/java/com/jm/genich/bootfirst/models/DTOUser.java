package com.jm.genich.bootfirst.models;

import java.util.Set;

public class DTOUser {


    private Long id;
    private String fullName;
    private String login;
    private Integer age;
    private Sex sex;
    private Set<Role> roles;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }


    public Long getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public DTOUser() {}

    public DTOUser(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.login = user.getLogin();
        this.age = user.getAge();
        this.sex = user.getSex();
        this.roles = user.getRoles();
    }
}
