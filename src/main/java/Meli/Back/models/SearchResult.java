package Meli.Back.models;
import java.util.List;
import lombok.*;


@EqualsAndHashCode
@Getter
@Setter
@Builder(toBuilder = true)
@ToString

public class SearchResult {
    private final Author author;
    private final List<Item> items;
}
