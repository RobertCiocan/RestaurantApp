package com.gastrosfera.shared.v1.produs.dto;

import com.gastrosfera.shared.v1.produs.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdusDTO {
    private String name;
    private double price;
    private String description;
    private Category category;
    private String image;
    private List<String> ingredients;
}
