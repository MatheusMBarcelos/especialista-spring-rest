package com.algaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {

    FotoRecuperada recuperar(String nomeArquivo);

    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
        armazenar(novaFoto);
        if (nomeArquivoAntigo != null) {
            remover(nomeArquivoAntigo);
        }
    }

    default String gerarNomeArquivo(String nomeArquivo) {
        return UUID.randomUUID().toString() + "_" + nomeArquivo;
    }

    @Builder
    @Getter
    class NovaFoto {

        private String nomeArquivo;
        private String contentType;
        private InputStream inputStream;

    }

    @Builder
    @Getter
    class FotoRecuperada {

        private InputStream inputStream;
        private String url;

        public boolean temInputStream() {
            return inputStream != null;
        }

        public boolean temUrl() {
            return url != null;
        }

    }
}
