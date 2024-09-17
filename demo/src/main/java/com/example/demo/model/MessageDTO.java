package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageDTO {

    @NonNull
    private String content;

    @NonNull
    private String category;

    private String author;
}
