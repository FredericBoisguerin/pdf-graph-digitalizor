package com.fredericboisguerin.pdf.graph;

public interface AxisVisitor {
    void visit(LinearAxis linearAxis);

    void visit(LogAxis logAxis);
}
