package Meli.Back.models;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class Author {

    private final String name;
    private final String lastName;

    public  Author(){
        this.name = "Nataly";
        this.lastName = "Mora Mancera";
    }

}