package com.edikorce.TravelAgency.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String name;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality", referencedColumnName = "id")
    Country country;

}