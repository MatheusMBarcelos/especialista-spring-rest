package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @GetMapping
    public List<GrupoModel> listar() {
        List<Grupo> grupos = grupoRepository.findAll();
        List<GrupoModel> grupoModels = grupoModelAssembler.toCollectionModel(grupos);
        return grupoModels;
    }

    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
        return grupoModelAssembler.toModel(grupo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
        return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupo));
    }

    @PutMapping("/{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        grupoInputDisassembler.copyToDomainObject(grupoInput, grupo);

        return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupo));
    }

}
