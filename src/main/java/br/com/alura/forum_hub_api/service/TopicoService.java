package br.com.alura.forum_hub_api.service;

import br.com.alura.forum_hub_api.entity.topico.Topico;
import br.com.alura.forum_hub_api.entity.topico.TopicoStatus;
import br.com.alura.forum_hub_api.infra.exception.TopicoNaoEncontradoException;
import br.com.alura.forum_hub_api.repository.TopicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class TopicoService {

    @Autowired
    private final TopicoRepositorio topicoRepositorio;

    public TopicoService(TopicoRepositorio topicoRepositorio) {
        this.topicoRepositorio = topicoRepositorio;
    }

    @Transactional(readOnly = true)
    public Page<Topico> listar(Pageable paginacao) {
        return topicoRepositorio.findAllAtivos(paginacao);
    }

    @Transactional(readOnly = true)
    public Topico buscarPorId(Long id) {
        return topicoRepositorio.findById(id)
                .orElseThrow(() -> new TopicoNaoEncontradoException(id));
    }

    @Transactional
    public Topico criar(Topico topico) {
        topico.setDataCriacao(new Date());
        topico.setStatus(TopicoStatus.NAO_RESPONDIDO);
        return topicoRepositorio.save(topico);
    }

    @Transactional
    public Topico atualizar(Long id, Topico topicoAtualizacao) {
        return topicoRepositorio.findById(id)
                .map(topico -> {
                    topico.setTitulo(topicoAtualizacao.getTitulo());
                    topico.setMensagem(topicoAtualizacao.getMensagem());
                    topico.setStatus(topicoAtualizacao.getStatus());
                    topico.setCurso(topicoAtualizacao.getCurso());
                    return topicoRepositorio.save(topico);
                })
                .orElseThrow(() -> new TopicoNaoEncontradoException(id));
    }

    @Transactional
    public void remover(Long id) {
        if (!topicoRepositorio.existByIdAndAtivoTrue(id)) {
            throw new TopicoNaoEncontradoException(id);
        }
        topicoRepositorio.desativar(id);
    }

    @Transactional
    public void fecharTopico(Long id) {
        Topico topico = buscarPorId(id);
        topico.setStatus(TopicoStatus.FECHADO);
        topicoRepositorio.save(topico);
    }

    @Transactional(readOnly = true)
    public Page<Topico> buscarPorUsuario(Long usuarioId, Pageable paginacao) {
        return topicoRepositorio.findByUsuarioId(usuarioId, paginacao);
    }

    @Transactional(readOnly = true)
    public Page<Topico> buscarPorCurso(Long cursoId, Pageable paginacao) {
        return topicoRepositorio.findByCursoId(cursoId, paginacao);
    }

    @Transactional(readOnly = true)
    public Page<Topico> buscarPorStatus(TopicoStatus status, Pageable paginacao) {
        return topicoRepositorio.findByStatus(status, paginacao);
    }

    @Transactional(readOnly = true)
    public Page<Topico> buscarPorTitulo(String titulo, Pageable paginacao) {
        return topicoRepositorio.findByTituloContainingIgnoreCase(titulo, paginacao);
    }

}
