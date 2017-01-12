package com.fredericboisguerin.pdf;

import com.fredericboisguerin.pdf.parser.model.DrawingActionVisitor;
import com.fredericboisguerin.pdf.parser.model.LineTo;
import com.fredericboisguerin.pdf.parser.model.MoveTo;

/**
 * Created by fred on 10/01/17.
 */
public class DrawingActionLogger implements DrawingActionVisitor {
    @Override
    public void visit(LineTo lineTo) {
        System.out.println("LineTo: " + lineTo.getDestination());
    }

    @Override
    public void visit(MoveTo moveTo) {
        System.out.println("MoveTo: " + moveTo.getDestination());
    }
}
