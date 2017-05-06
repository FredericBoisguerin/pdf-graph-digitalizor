package com.fredericboisguerin.pdf.wrapper;

import com.fredericboisguerin.pdf.parser.model.DrawingAction;

import java.util.List;

public interface DrawingActionsToDrawLinesConverter {

    DrawLines convert(List<DrawingAction> drawingActions);
}
