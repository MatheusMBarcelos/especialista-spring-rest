package com.algaworks.algafood.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

    public FormaPagamentoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
    public FormaPagamentoNaoEncontradaException(Long formaPagamentoId) {
        super(String.format("Não existe um cadastro de forma de pagamento com código %d", formaPagamentoId));
    }

}
