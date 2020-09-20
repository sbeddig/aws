package de.sbeddig.dynamodb.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Quantity {

    private float value;
    private String unit;

}
