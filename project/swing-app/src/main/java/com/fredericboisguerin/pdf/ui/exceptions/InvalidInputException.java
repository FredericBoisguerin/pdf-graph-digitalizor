package com.fredericboisguerin.pdf.ui.exceptions;

public class InvalidInputException extends Exception {

    public InvalidInputException(String inputId) {
        super(String.format("InvalidInputException: %s", inputId));
    }
}
