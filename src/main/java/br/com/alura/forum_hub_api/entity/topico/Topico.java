package br.com.alura.forum_hub_api.entity.topico;

import br.com.alura.forum_hub_api.entity.Curso;
import br.com.alura.forum_hub_api.entity.usuario.Usuario;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private Date dataCriacao;
    @Enumerated(EnumType.STRING)
    private TopicoStatus status = TopicoStatus.NAO_RESPONDIDO;

    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Curso curso;
    @OneToMany(mappedBy = "topico")
    private List<Resposta> resposta;

    private Boolean ativo = true;

    public Topico() {
    }

    public Topico(Long id, String titulo, String mensagem, Date dataCriacao, TopicoStatus status, Usuario usuario, Curso curso, List<Resposta> resposta, Boolean ativo) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.usuario = usuario;
        this.curso = curso;
        this.resposta = resposta;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public TopicoStatus getStatus() {
        return status;
    }

    public void setStatus(TopicoStatus status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Resposta> getResposta() {
        return resposta;
    }

    public void setResposta(List<Resposta> resposta) {
        this.resposta = resposta;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
