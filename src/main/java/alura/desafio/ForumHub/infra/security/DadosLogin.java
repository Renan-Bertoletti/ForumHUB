package alura.desafio.ForumHub.infra.security;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(

        @NotBlank
        String email,

        @NotBlank
        String senha
) {
}