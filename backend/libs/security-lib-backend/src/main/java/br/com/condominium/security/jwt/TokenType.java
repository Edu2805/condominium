package br.com.condominium.security.jwt;

public enum TokenType {

    /* Token externo */
    ACCESS,
    /* Token interno (comunicação entre serviços) */
    INTERNAL
}
