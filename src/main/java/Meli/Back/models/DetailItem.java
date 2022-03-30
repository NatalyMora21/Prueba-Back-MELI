package Meli.Back.models;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@Builder(toBuilder = true)
@ToString

public class DetailItem {

    private final String id;
    private final String title;
    private final Price price;
    private final String picture;
    private final String condition;
    private final Boolean free_shipping;
    private final Integer sold_quantity;
    private final String description;

}
