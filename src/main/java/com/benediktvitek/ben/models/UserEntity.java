package com.benediktvitek.ben.models;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;



    @Column(unique = true)
    private String username;
    private String password;

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)
    private Set<Comment> comments = new LinkedHashSet<>();

    @ManyToMany //could be many to one as right now only USER role exists
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "userEntity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    private Set<Role> roles = new LinkedHashSet<>();

}


