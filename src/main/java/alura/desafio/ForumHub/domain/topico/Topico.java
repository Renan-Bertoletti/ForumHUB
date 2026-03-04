package alura.desafio.ForumHub.domain.topico;

import alura.desafio.ForumHub.domain.curso.Curso;
import alura.desafio.ForumHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"titulo", "mensagem"})
})
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Curso curso;

    public Topico(String titulo, String mensagem, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusTopico.NAO_RESPONDIDO;
        this.autor = autor;
        this.curso = curso;
    }

    public void atualizar(String titulo, String mensagem) {
        if (titulo != null && !titulo.isBlank()) {
            this.titulo = titulo;
        }

        if (mensagem != null && !mensagem.isBlank()) {
            this.mensagem = mensagem;
        }
    }
}