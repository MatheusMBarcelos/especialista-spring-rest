package com.algaworks.algafood.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public EstadoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EstadoNaoEncontradoException(Long estadoId) {
        super(String.format("Não existe um cadastro de estado com código %d", estadoId));
    }
}
