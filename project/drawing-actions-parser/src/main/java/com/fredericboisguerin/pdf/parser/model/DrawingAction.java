package com.fredericboisguerin.pdf.parser.model;

import com.fredericboisguerin.pdf.parser.BorderPoints;

public interface DrawingAction {

    void accept(DrawingActionVisitor visitor);

    boolean isContainedIn(BorderPoints borderPoints);
}
