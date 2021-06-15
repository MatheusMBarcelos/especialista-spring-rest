package com.algaworks.algafood.domain.exception;

public class PermissaoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public PermissaoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PermissaoNaoEncontradoException(Long permissaoId) {
        super(String.format("Não existe um cadastro de permissao com código %d", permissaoId));
    }
}
