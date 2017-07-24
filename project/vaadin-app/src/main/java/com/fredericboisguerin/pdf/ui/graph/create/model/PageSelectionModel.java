package com.fredericboisguerin.pdf.ui.graph.create.model;

public class PageSelectionModel {
    private final int nbPages;
    private int selectedPage = 0;

    public PageSelectionModel(int nbPages) {
        this.nbPages = nbPages;
    }


    public void inc() {
        selectedPage++;
    }

    public void dec() {
        selectedPage--;
    }

    public boolean isDecable() {
        return selectedPage > 0 && nbPages > 0;
    }

    public boolean isIncable() {
        return selectedPage + 1 < nbPages;
    }

    public int getSelectedPage() {
        return selectedPage;
    }

    public String asString() {
        if (nbPages == 0) return "--/--";
        return String.format("%d/%d", selectedPage + 1, nbPages);
    }
}
