package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    @Query("from Restaurante r join fetch r.cozinha left join fetch r.formaPagamento")
    List<Restaurante> findAll();

    List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

    //List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    boolean existsByNome(String nome);

    int countByCozinhaId(long id);
    public List<Restaurante> consultar(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}
