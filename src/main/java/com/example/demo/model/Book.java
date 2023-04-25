package com.example.demo.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int id;
    private String name;
    private int price;
    private String authorName;
}
