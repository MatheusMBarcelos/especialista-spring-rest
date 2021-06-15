package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.CadastroGrupoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    private CadastroGrupoService cadastroGrupoService;

    private PermissaoModelAssembler permissaoModelAssembler;

    @GetMapping
    public List<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
        return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.desassociarPermissoes(grupoId, permissaoId);
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.associarPermissoes(grupoId, permissaoId);
    }

}
