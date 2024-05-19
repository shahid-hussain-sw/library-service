package com.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BorrowerDto {
    private Long id;
    @NotBlank(message = "name is required field")
    private String name;
    @NotBlank(message = "email is required field")
    @Email
    private String email;
}
