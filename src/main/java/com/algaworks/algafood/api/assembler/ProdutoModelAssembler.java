package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.domain.model.Produto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProdutoModelAssembler {

    private ModelMapper modelMapper;

    public ProdutoModel toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoModel.class);
    }

    public List<ProdutoModel> toCollectionModel(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toModel).collect(Collectors.toList());
    }
}
