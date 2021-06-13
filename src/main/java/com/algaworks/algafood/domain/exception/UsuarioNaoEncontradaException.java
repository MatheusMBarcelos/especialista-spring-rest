package com.algaworks.algafood.domain.exception;

public class UsuarioNaoEncontradaException extends EntidadeNaoEncontradaException {

    public UsuarioNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEncontradaException(Long usuarioId) {
        super(String.format("Não existe um cadastro de usuário com código %d", usuarioId));
    }
}
