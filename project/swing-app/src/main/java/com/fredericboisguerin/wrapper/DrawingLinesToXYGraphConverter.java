package com.fredericboisguerin.wrapper;

import com.fredericboisguerin.graph.EditableXYGraph;
import com.fredericboisguerin.pdf.drawlines.model.DrawLines;

public interface DrawingLinesToXYGraphConverter {

    EditableXYGraph convert(DrawLines drawLines);
}
