package com.fredericboisguerin.pdf.parser.model;

public interface DrawingActionVisitor {
    void visit(LineTo lineTo);

    void visit(MoveTo moveTo);

    void visit(CurveTo curveTo);
}
