package com.metropolitan.rentacar.service;

/**
 * The type Email already used exception.
 */
public class EmailAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Email already used exception.
     */
    public EmailAlreadyUsedException() {
        super("Email is already in use!");
    }

}
