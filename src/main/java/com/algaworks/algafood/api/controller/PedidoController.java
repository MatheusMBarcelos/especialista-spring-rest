package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PedidoInputDisassembler;
import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.core.data.PageableTranslator;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.repository.filter.PedidoFilter;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoRepository pedidoRepository;
    private PedidoModelAssembler pedidoModelAssembler;
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;
    private PedidoInputDisassembler pedidoInputDisassembler;
    private EmissaoPedidoService emissaoPedidoService;

//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
//        List<Pedido> pedidos = pedidoRepository.findAll();
//        List<PedidoResumoModel> pedidosResumo = pedidoResumoModelAssembler.toCollectionModel(pedidos);
//        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosResumo);
//        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
//        simpleFilterProvider.addFilter("pedidosFilter", SimpleBeanPropertyFilter.serializeAll());
//
//        if(StringUtils.isNoneBlank(campos)){
//            simpleFilterProvider.addFilter("pedidosFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//        }
//        pedidosWrapper.setFilters(simpleFilterProvider);
//        return pedidosWrapper;
//    }

    @GetMapping
    public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable) {
        pageable = traduzirPageable(pageable);
        Page<Pedido> pedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);
        List<PedidoResumoModel> pedidoResumoModelList = pedidoResumoModelAssembler.toCollectionModel(pedidos.getContent());
        Page<PedidoResumoModel> pedidoResumoModelPage = new PageImpl<>(pedidoResumoModelList, pageable, pedidos.getTotalElements());
        return pedidoResumoModelPage;
    }

    @GetMapping("{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedidoService.emitir(novoPedido);
            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
    private Pageable traduzirPageable(Pageable pageable){
        var mapeamento = Map.of(
                "codigo", "codigo",
                "valorTotal", "valorTotal",
                "subtotal", "subtotal",
                "taxaFrete", "taxaFrete",
                "status", "status",
                "dataCriacao", "dataCriacao",
                "restaurante.nome", "restaurante.nome",
                "restaurante.id", "restaurante.id",
                "cliente.id", "cliente.id",
                "cliente.nome", "cliente.nome"
        );
        return PageableTranslator.translate(pageable, mapeamento);
    }
}
