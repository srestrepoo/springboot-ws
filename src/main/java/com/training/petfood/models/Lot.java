package com.training.petfood.models;

import lombok.*;

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
    @JoinColumn(name = "productId")
    private Product product;

    private Integer totalAmount;
    private Date creationDate;
    private Date expirationDate;
    private Integer campusId;

}
