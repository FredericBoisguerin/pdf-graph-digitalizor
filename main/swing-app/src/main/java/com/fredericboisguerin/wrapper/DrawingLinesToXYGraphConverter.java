package com.fredericboisguerin.wrapper;

import com.fredericboisguerin.graph.EditableXYGraph;
import com.fredericboisguerin.pdf.wrapper.DrawLines;

/**
 * Created by fred on 10/01/17.
 */
public interface DrawingLinesToXYGraphConverter {

    EditableXYGraph convert(DrawLines drawLines);
}
