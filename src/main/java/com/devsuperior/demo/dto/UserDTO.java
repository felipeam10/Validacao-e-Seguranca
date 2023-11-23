package com.devsuperior.demo.dto;

import com.devsuperior.demo.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long id;
    @NotEmpty(message = "Campo obrigatório")
    private String name;
    @Email(message = "Favor inserir e-mail válido")
    private String email;
    private Set<RoleDTO> roles = new HashSet<>();
    public UserDTO(){}

    public UserDTO(Long id, String firstName, String email) {
        this.id = id;
        this.name = firstName;
        this.email = email;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
