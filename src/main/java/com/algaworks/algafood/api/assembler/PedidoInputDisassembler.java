package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.domain.model.Pedido;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PedidoInputDisassembler {

    private ModelMapper modelMapper;

    public Pedido toDomainObject(PedidoModel pedidoModel) {
        return modelMapper.map(pedidoModel, Pedido.class);
    }

    public void copyToDoamsinObject(PedidoModel pedidoModel, Pedido pedido) {
        modelMapper.map(pedidoModel, pedido);
    }
}
