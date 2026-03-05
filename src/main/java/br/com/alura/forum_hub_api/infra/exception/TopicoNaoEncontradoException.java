package br.com.alura.forum_hub_api.infra.exception;

public class TopicoNaoEncontradoException extends RuntimeException {
    public TopicoNaoEncontradoException(Long id) {
        super(String.format("Tópico com ID %d não encontrado."));
    }
}
