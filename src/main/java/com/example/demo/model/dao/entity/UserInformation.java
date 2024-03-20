package com.example.demo.model.dao.entity;

import jakarta.persistence.*;
import jakarta.websocket.ClientEndpoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name="m_user_information")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInformation implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pk",nullable = false)
    private int idPk;

    @Length(min = 10, max = 255)
    @Column(name="email",nullable = false, length = 255)
    private String email;

    @Length(min = 5,max = 30)
    @Column(name = "username",nullable = false)
    private String username;

    @Length(min=7,max = 100)
    @Column(name="password",nullable = false,length = 255)
    private String password;

    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "reg_id",nullable = false)
    private String regId;

    @Column(name="reg_date",nullable = false)
    private Timestamp regDate;

    @Column(name="update_id",nullable = false)
    private String updateId;

    @Column(name="update_date",nullable = false)
    private Timestamp updateDate;

    @Column(name="delete_flg", nullable = false)
    private boolean deleteFlg = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
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
