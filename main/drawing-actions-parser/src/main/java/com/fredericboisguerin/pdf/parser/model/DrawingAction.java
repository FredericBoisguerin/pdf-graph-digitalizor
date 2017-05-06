package com.fredericboisguerin.pdf.parser.model;

/**
 * Created by fred on 10/01/17.
 */
public interface DrawingAction {

    void accept(DrawingActionVisitor visitor);

}
