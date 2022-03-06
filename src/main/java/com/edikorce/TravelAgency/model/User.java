package com.edikorce.TravelAgency.model;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name ;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private  String email;

    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    // kjo duhet te meret nga banka
    private Double amountOfMoney;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_packets",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "packet_id", referencedColumnName = "id"))
    @Fetch(value = FetchMode.SUBSELECT)
    @ToString.Exclude
    private List<Packet> packetList = new ArrayList<>();


    public void addRole(Role role) {
        this.roles.add(role);
    }



}
