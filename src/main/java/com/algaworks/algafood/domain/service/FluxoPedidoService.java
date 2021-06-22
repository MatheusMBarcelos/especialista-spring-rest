package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class FluxoPedidoService {

    private CadastroPedidoService cadastroPedidoService;

    @Transactional
    public void confirmar(Long pedidoId){
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(pedidoId);
        pedido.confirmar();
    }

    @Transactional
    public void entregar(Long pedidoId){
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(pedidoId);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(Long pedidoId){
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(pedidoId);
        pedido.cancelar();
    }
}
