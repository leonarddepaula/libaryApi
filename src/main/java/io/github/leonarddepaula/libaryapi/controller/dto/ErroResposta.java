package io.github.leonarddepaula.libaryapi.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int status, String mesagem, List<ErroCampo> erros) {

    public static ErroResposta respostaPadrao(String mesagem) {
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mesagem, List.of());
    }

    public static ErroResposta conflito(String mensagem) {
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }
}
