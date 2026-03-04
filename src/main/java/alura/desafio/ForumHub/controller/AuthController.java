package alura.desafio.ForumHub.controller;

import alura.desafio.ForumHub.domain.usuario.Usuario;
import alura.desafio.ForumHub.infra.security.DadosLogin;
import alura.desafio.ForumHub.infra.security.DadosToken;
import alura.desafio.ForumHub.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosToken> login(
            @RequestBody @Valid DadosLogin dados) {

        var authToken =
                new UsernamePasswordAuthenticationToken(
                        dados.email(),
                        dados.senha());

        var authentication = manager.authenticate(authToken);

        var usuario = (Usuario) authentication.getPrincipal();
        var token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new DadosToken(token));
    }
}