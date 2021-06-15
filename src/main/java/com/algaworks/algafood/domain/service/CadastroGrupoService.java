package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CadastroGrupoService {

    private GrupoRepository grupoRepository;

    private CadastroPermissaoService cadastroPermissaoService;

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void excluir(Long gurpoId) {
        try {
            grupoRepository.deleteById(gurpoId);
        } catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEncontradoException(gurpoId);
        }
    }

    @Transactional
    public void desassociarPermissoes(Long grupoId, Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
        grupo.removerPermissao(permissao);
    }

    @Transactional
    public void associarPermissoes(Long grupoId, Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
        grupo.adicionarPermissao(permissao);
    }

    public Grupo buscarOuFalhar(Long grupoId) {
        return grupoRepository.findById(grupoId)
                .orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }
}
