package com.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookDto {

    private Long id;
    @NotBlank(message = "author is required field")
    private String author;
    @NotBlank(message = "title is required field")
    private String title;
    @NotBlank(message = "isbn is required field")
    private String isbn;
}
