package com.banamex.prueba.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document(collection = "cards")
public class Card {
    @Id
    private String id;

    private String modelo;
    private String color;
    private String marca;
}
