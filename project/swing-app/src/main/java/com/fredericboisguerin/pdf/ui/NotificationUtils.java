package com.fredericboisguerin.pdf.ui;

import javax.swing.*;
import java.awt.*;

public class NotificationUtils {

    public static void showInfo(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showError(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
