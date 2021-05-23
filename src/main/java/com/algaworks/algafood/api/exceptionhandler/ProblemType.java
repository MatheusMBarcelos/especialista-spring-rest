package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de Sistema"),
    PARAMETRO_INVALIDO("/paramentro-invalido", "Parâmetro inválido"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensgem imcompreensível"),
    RECURSO_NAO_ENCONTRADA("/recurso-nao-encontrada", "Recurso não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");

    private String uri;
    private String title;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
