package com.fredericboisguerin.wrapper;

import com.fredericboisguerin.graph.XYGraph;
import com.fredericboisguerin.pdf.drawlines.model.DrawLines;

public interface DrawingLinesToXYGraphConverter {

    XYGraph convert(DrawLines drawLines);
}
