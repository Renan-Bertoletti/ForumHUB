package alura.desafio.ForumHub.controller;

import alura.desafio.ForumHub.domain.curso.Curso;
import alura.desafio.ForumHub.domain.curso.CursoRepository;
import alura.desafio.ForumHub.domain.topico.*;
import alura.desafio.ForumHub.domain.usuario.Usuario;
import alura.desafio.ForumHub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    @GetMapping
    public List<DadosDetalhamentoTopico> listar() {
        return repository.findAll()
                .stream()
                .map(DadosDetalhamentoTopico::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable Long id) {
        return repository.findById(id)
                .map(t -> ResponseEntity.ok(new DadosDetalhamentoTopico(t)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(
            @RequestBody @Valid DadosCadastroTopico dados,
            @AuthenticationPrincipal Usuario usuario) {

        if(repository.existsByTituloAndMensagem(
                dados.titulo(), dados.mensagem())) {
            return ResponseEntity.badRequest().body("Tópico duplicado");
        }

        Curso curso = cursoRepository.findByNome(dados.nomeCurso());

        Topico topico = new Topico(
                dados.titulo(),
                dados.mensagem(),
                usuario,
                curso);

        repository.save(topico);

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizacaoTopico dados) {

        var optional = repository.findById(id);
        if(optional.isEmpty()) return ResponseEntity.notFound().build();

        Topico topico = optional.get();
        topico.atualizar(dados.titulo(), dados.mensagem());

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        if(!repository.existsById(id))
            return ResponseEntity.notFound().build();

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}