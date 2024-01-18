package com.gastrosfera.produse.model;

import com.gastrosfera.shared.v1.produs.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "produs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produs {
    @Id
    private String name;

    private double price;

    private String description;

    private Category category;

    private String image;

    private List<String> ingredients;

}
