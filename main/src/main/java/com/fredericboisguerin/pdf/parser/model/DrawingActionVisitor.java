package com.fredericboisguerin.pdf.parser.model;

/**
 * Created by fred on 10/01/17.
 */
public interface DrawingActionVisitor {
    void visit(LineTo lineTo);

    void visit(MoveTo moveTo);
}
