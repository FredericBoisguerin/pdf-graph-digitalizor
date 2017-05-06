package com.fredericboisguerin.pdf.ui;

import java.awt.*;

import javax.swing.*;

/**
 * Created by fred on 11/01/17.
 */
public class MainUI extends JFrame {

    private final GraphEditor graphEditorForm = new GraphEditor();

    public MainUI() throws HeadlessException {
        super("Datasheet digitalizor");
        setLayout(new BorderLayout());

        add(graphEditorForm.getMainPanel());

        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public GraphEditor getGraphEditorForm() {
        return graphEditorForm;
    }
}
