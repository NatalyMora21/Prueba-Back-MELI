package Meli.Back.models;


import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class Item {

    private final String id;
    private final String title;
    private final Price price;
    private final String picture;
    private final String condition;
    private final Boolean free_shipping;


}
