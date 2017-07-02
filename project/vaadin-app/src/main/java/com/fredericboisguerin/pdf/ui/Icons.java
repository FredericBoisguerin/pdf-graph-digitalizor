package com.fredericboisguerin.pdf.ui;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;

public class Icons {

    private static final String BASE_DIR = "icons/";

    public static final Resource ARCHIVE = getThemeResource("archive.svg");
    public static final Resource FILE_ADD = getThemeResource("file-add.svg");
    public static final Resource FOLDER_ADD = getThemeResource("folder-add.svg");
    public static final Resource LIST_UL = getThemeResource("list-ul.svg");
    public static final Resource PENCIL = getThemeResource("pencil.svg");

    private static ThemeResource getThemeResource(String fileName) {
        return new ThemeResource(BASE_DIR + fileName);
    }
}
