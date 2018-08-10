package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@Table(name = "users", schema = "public")
public class User implements UserDetails, Serializable {

    @Id
    @Column(name = "username", unique = true)
    @NotNull
    @Size(min = 1, max = 15)
    private String username;

    @Column(name = "password", nullable = false)
    @NotNull
    @JsonIgnore
    @Size(min = 5)
    private String password;

    @Column(name = "non_expired")
    @JsonIgnore
    @NotNull
    private boolean accountNonExpired;

    @Column(name = "non_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    @JsonIgnore
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    @JsonIgnore
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
            mappedBy = "user")
    @JsonManagedReference
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<UserRole> authorities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
            mappedBy = "user")
    @JsonManagedReference
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Issue> issues = new ArrayList<>();

    public User() {
    }

    public User(@NotNull @Size(min = 1, max = 20) String username, @NotNull @Size(min = 5) String password) {
        this.username = username;
        this.password = password;
        this.authorities = Arrays.asList(new UserRole(this, "USER"));
        this.accountNonExpired = false;
        this.issues = null;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                ", issues=" + issues +
                '}';
    }
}
