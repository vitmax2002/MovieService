package com.example.movieservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @NotNull(message = "Choose one authority ADMINISTRATOR or USER")
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId == role.roleId && name == role.name && Objects.equals(users, role.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, name, users);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", name=" + name +
                ", users=" + users +
                '}';
    }
}
