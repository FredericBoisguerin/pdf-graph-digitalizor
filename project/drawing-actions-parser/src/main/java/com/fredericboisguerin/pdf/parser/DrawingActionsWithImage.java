package com.fredericboisguerin.pdf.parser;

import com.fredericboisguerin.pdf.parser.model.DrawingAction;

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
}
