/**
 * Copyright (C) 2020 Deveryware S.A. All Rights Reserved.
 */
package es.ethcero.mca.practica2.model.exception;

/**
 * @author fran
 */
public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(Long clientId) {
        super(String.format("Client %d not found", clientId));
    }
}
