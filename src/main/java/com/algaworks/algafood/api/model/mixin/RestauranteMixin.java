package com.algaworks.algafood.api.model.mixin;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {

    @JsonIgnore
    private OffsetDateTime dataCadastro;

    @JsonIgnore
    private OffsetDateTime dataAtualizacao;

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private List<FormaPagamento> formaPagamento = new ArrayList<>();

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();
}
