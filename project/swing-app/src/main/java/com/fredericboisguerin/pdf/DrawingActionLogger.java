package com.fredericboisguerin.pdf;

import com.fredericboisguerin.pdf.parser.model.CurveTo;
import com.fredericboisguerin.pdf.parser.model.DrawingActionVisitor;
import com.fredericboisguerin.pdf.parser.model.LineTo;
import com.fredericboisguerin.pdf.parser.model.MoveTo;

public class DrawingActionLogger implements DrawingActionVisitor {
    @Override
    public void visit(LineTo lineTo) {
        System.out.println("LineTo: " + lineTo.getDestination());
    }

    @Override
    public void visit(MoveTo moveTo) {
        System.out.println();
        System.out.println("MoveTo: " + moveTo.getDestination());
    }

    @Override
    public void visit(CurveTo curveTo) {
        System.out.println("CurveTo: " + curveTo.getP1() + ", " + curveTo.getP2() + ", " + curveTo
                .getP3());
    }
}
