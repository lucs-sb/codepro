package br.com.lucassoaresdev.codepro.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCourseRequestDTO(@NotBlank String name, @NotBlank String category) {
}
