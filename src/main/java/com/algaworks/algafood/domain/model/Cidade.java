package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.api.core.validation.Groups;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Valid
    @NotNull
    @ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
    @ManyToOne
    @JoinColumn(nullable = false)
    private Estado estado;
}
