package com.fredericboisguerin.pdf.ui.exceptions;

/**
 * Created by fred on 11/01/17.
 */
public class InvalidInputException extends Exception {

    public InvalidInputException(String inputId) {
        super(String.format("InvalidInputException: %s", inputId));
    }
}
