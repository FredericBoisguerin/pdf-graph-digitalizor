package com.fredericboisguerin.pdf.parser;

import com.fredericboisguerin.pdf.parser.model.DrawingAction;
import com.fredericboisguerin.pdf.parser.model.DrawingActionVisitor;

import java.awt.image.BufferedImage;
import java.util.List;

public class DrawingActionsWithImage {
    private final List<DrawingAction> actionList;
    private final BufferedImage bufferedImage;

    public DrawingActionsWithImage(List<DrawingAction> actionList, BufferedImage bufferedImage) {
        this.actionList = actionList;
        this.bufferedImage = bufferedImage;
    }

    public List<DrawingAction> getActions() {
        return actionList;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    List<DrawingAction> getActionsIn(BorderPoints borderPoints) {
        DrawingActionVisitorBorderFilter borderFilter = new DrawingActionVisitorBorderFilter(borderPoints);
        return borderFilter.getFilteredActions(actionList);
    }
}
