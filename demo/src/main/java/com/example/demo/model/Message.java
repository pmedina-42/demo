package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String content;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    private String author;
}
