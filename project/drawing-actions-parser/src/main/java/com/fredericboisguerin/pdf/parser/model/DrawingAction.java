package com.fredericboisguerin.pdf.parser.model;

public interface DrawingAction {

    void accept(DrawingActionVisitor visitor);

}
