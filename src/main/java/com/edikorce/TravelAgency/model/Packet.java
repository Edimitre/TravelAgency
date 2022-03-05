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
@ToString
@Entity
public class Packet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nrOfDays;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    private Integer maxNrOfUsers;

    private Integer nrOfTimesBooked;

    private String description;

    private Double price;

    private Boolean isOffer;

    private Boolean isValid;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "packetList", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<User> userList = new ArrayList<>();

}