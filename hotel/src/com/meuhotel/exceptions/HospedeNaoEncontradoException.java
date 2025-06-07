package com.meuhotel.exceptions;

public class HospedeNaoEncontradoException extends RuntimeException {
    public HospedeNaoEncontradoException(String message) {
        super(message);
    }
}