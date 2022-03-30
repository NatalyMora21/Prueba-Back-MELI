package Meli.Back.models;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@Builder(toBuilder = true)
@ToString

public class Price {
    private final String currency;
    private final Integer amount;
    private final Float decimals;

}
