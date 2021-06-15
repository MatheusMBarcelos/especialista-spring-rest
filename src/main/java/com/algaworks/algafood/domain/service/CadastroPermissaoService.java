package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.PermissaoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CadastroPermissaoService {

    private PermissaoRepository permissaoRepository;

    @Transactional(readOnly = true)
    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradoException(permissaoId));
    }
}
