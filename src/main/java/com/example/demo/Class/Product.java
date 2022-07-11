package com.example.demo.Class;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    public String name;
    public String description;
    public String imageLink;
    public int price;
    public int rating;
    public String storeName;

}
