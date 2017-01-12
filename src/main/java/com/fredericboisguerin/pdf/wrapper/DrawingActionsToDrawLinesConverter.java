package com.fredericboisguerin.pdf.wrapper;

import com.fredericboisguerin.pdf.parser.model.DrawingAction;

import java.util.List;

/**
 * Created by fred on 10/01/17.
 */
public interface DrawingActionsToDrawLinesConverter {

    DrawLines convert(List<DrawingAction> drawingActions);
}
