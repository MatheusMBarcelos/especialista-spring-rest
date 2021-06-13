package com.algaworks.algafood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProdutoNaoEncontradoException(Long estadoId, Long restauranteId) {
        super(String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d", estadoId, restauranteId));
    }
}
