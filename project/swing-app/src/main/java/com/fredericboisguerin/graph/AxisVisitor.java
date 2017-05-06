package com.fredericboisguerin.graph;

public interface AxisVisitor {
    void visit(LinearAxis linearAxis);

    void visit(LogAxis logAxis);
}
