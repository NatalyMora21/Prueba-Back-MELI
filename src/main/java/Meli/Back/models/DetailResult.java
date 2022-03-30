package Meli.Back.models;



import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class DetailResult {
    private final Author author;
    private final DetailItem item;
}