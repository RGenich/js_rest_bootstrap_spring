package com.jm.genich.bootfirst.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class User implements UserDetails {

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column
    private Integer age;

    @Column
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_autority", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public User(DTOUser dtoUser) {
        this.id = dtoUser.getId();
        this.fullName = dtoUser.getFullName();
        this.login = dtoUser.getLogin();
        this.age = dtoUser.getAge();
        this.sex = dtoUser.getSex();
        this.roles = dtoUser.getRoles();
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(String fullName, String login, String password, int age, Sex sex, Set<Role> roles) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.roles = roles;
    }

    public User(String fullName, Integer age, Sex sex) {
        this.fullName = fullName;
        this.age = age;
        this.sex = sex;
    }

    public User() {
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
