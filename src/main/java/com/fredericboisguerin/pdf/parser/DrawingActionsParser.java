package com.fredericboisguerin.pdf.parser;

import com.fredericboisguerin.pdf.parser.model.DrawingAction;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by fred on 10/01/17.
 */
public interface DrawingActionsParser {

    List<DrawingAction> parseDrawingActions(File file) throws IOException;

}
