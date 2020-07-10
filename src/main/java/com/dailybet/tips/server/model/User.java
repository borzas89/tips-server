package com.dailybet.tips.server.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

    @Basic
    @Column(name="subscription_start")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date subscriptionStart;

    @Basic
    @Column(name="subscription_end")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date subscriptionEnd;

    @Transient
    private String token;
}
