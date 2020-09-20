package de.sbeddig.dynamodb.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Grocery {

    private String name;
    private float price;
    private Quantity quantity;

}

