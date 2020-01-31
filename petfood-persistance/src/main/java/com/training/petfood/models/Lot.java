package com.training.petfood.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "LOT")
public class Lot {

    @Id
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "total_amount")
    private Integer totalAmount;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "expiration_date")
    private Date expirationDate;
    @Column(name = "campus_id")
    private Integer campusId;

}
