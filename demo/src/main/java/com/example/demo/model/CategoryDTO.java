package com.example.demo.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {

    @NonNull
    private String name;

    private List<MessageDTO> messages;

}
