package com.edikorce.TravelAgency.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "nationality", referencedColumnName = "id")
    Country country;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Packet> packetList = new ArrayList<>();

}