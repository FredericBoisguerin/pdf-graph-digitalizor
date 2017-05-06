package com.fredericboisguerin.wrapper;

import com.fredericboisguerin.graph.EditableXYGraph;
import com.fredericboisguerin.pdf.wrapper.DrawLines;

public interface DrawingLinesToXYGraphConverter {

    EditableXYGraph convert(DrawLines drawLines);
}
