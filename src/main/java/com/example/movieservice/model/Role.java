package com.example.movieservice.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Enumerated(EnumType.STRING)
    private Authority name;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;

    public Role(Authority name, List<UserEntity> users) {
        this.name = name;
        this.users = users;
    }

    public Role() {

    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Authority getName() {
        return name;
    }

    public void setName(Authority name) {
        this.name = name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }


}
