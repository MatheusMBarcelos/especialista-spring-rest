package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.algaworks.algafood.infrastructure.repository.spec.RestaurenteSpecs.comFreteGratis;
import static com.algaworks.algafood.infrastructure.repository.spec.RestaurenteSpecs.comNomeSemelhante;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome){
        return cozinhaRepository.findTodosByNomeContaining(nome);
    }
    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> cozinhaPorNome(@RequestParam("nome") String nome){
        return cozinhaRepository.findByNome(nome);
    }
    @GetMapping("/cozinhas/primeira")
    public Optional<Cozinha> cozinhaPrimeiro(){
        return cozinhaRepository.buscarPrimeiro();
    }

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantePorTaxaFrete( BigDecimal taxaInicial, BigDecimal taxaFinal){
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }
    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> restaurantePorTaxaFrete(String nome, Long cozinhaId){
        return restauranteRepository.consultarPorNome(nome, cozinhaId);
    }
    @GetMapping("/restaurantes/primeiro-por-nome")
    public Optional<Restaurante> restaurantePrimeiroPorNome(String nome){
        return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
    }
    @GetMapping("/restaurantes/top2-por-nome")
    public List<Restaurante> restauranteTop2PorNome(String nome){
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }
    @GetMapping("/restaurantes/exists")
    public boolean restauranteExistsPorNome(String nome){
        return restauranteRepository.existsByNome(nome);
    }
    @GetMapping("/cozinhas/exists")
    public boolean cozinhaExistsPorNome(String nome){
        return cozinhaRepository.existsByNome(nome);
    }
    @GetMapping("/restaurantes/count-por-cozinha")
    public int restauranteCountPorCozinha(Long cozinhaId){
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }
    @GetMapping("/restaurantes/por-nome-e-frete")
    public List<Restaurante> restaurantePorNomeEFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
        return restauranteRepository.consultar(nome, taxaFreteInicial, taxaFreteFinal);
    }
    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> restauranteComFreteGratis(String nome){
        return restauranteRepository.findComFreteGratis(nome);
    }
    @GetMapping("/restaurantes/primeiro")
    public Optional<Restaurante> restaurentePrimeiro(){
        return restauranteRepository.buscarPrimeiro();
    }
}