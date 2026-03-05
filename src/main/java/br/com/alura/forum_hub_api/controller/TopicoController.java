package br.com.alura.forum_hub_api.controller;

import br.com.alura.forum_hub_api.entity.topico.Topico;
import br.com.alura.forum_hub_api.entity.topico.TopicoStatus;
import br.com.alura.forum_hub_api.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Topico criar(@RequestBody @Valid Topico topico) {
        return topicoService.criar(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizar(@PathVariable Long id, @RequestBody @Valid Topico topicoAtualizado) {
        return ResponseEntity.ok(topicoService.atualizar(id, topicoAtualizado));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remover(@PathVariable Long id) {
        topicoService.remover(id);
    }

    @PutMapping("/{id}/fechar")
    @ResponseStatus(HttpStatus.OK)
    public void fecharTopico(@PathVariable Long id) {
        topicoService.fecharTopico(id);
    }

    @GetMapping
    public Page<Topico> listar(
            @RequestParam(required = false) String titulo,
            @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC) Pageable paginacao) {
        if (titulo != null && !titulo.isEmpty()) {
            return topicoService.buscarPorTitulo(titulo, paginacao);
        }
        return topicoService.listar(paginacao);
    }

    @GetMapping("/porTitulo")
    public ResponseEntity<Page<Topico>> buscarPorTitulo(
            @RequestParam String titulo,
            Pageable pageable) {
        return ResponseEntity.ok(
                topicoService.buscarPorTitulo(titulo, pageable)
        );
    }

    @GetMapping("/porStatus")
    public ResponseEntity<Page<Topico>> buscarPorStatus(
            @RequestParam TopicoStatus status,
            Pageable pageable) {
        return ResponseEntity.ok(
                topicoService.buscarPorStatus(status, pageable)
        );
    }

    @GetMapping("/porCurso")
    public ResponseEntity<Page<Topico>> buscarPorCurso(
            @RequestParam Long cursoId,
            Pageable pageable) {
        return ResponseEntity.ok(
                topicoService.buscarPorCurso(cursoId, pageable)
        );
    }
}