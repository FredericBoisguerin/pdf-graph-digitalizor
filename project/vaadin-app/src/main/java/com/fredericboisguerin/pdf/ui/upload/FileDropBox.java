package com.fredericboisguerin.pdf.ui.upload;

import static com.vaadin.ui.Notification.Type;
import static com.vaadin.ui.Notification.show;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.server.StreamVariable;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.Html5File;
import com.vaadin.ui.Layout;
import com.vaadin.ui.ProgressBar;

public class FileDropBox extends DragAndDropWrapper implements DropHandler {

    private static final long FILE_SIZE_LIMIT = 2 * 1024 * 1024; // 2MB

    private final ProgressBar progress;
    private FileUpdateListener fileUpdateListener;

    public FileDropBox(Layout root) {
        super(root);
        progress = new ProgressBar();
        progress.setIndeterminate(true);
        progress.setVisible(false);
        root.addComponent(progress);
        setDropHandler(this);
    }

    @Override
    public void drop(DragAndDropEvent dropEvent) {
        WrapperTransferable tr = (WrapperTransferable) dropEvent.getTransferable();
        Html5File[] files = tr.getFiles();
        if (files == null) {
            show("Illegal content", Type.ERROR_MESSAGE);
            return;
        }
        if (files.length > 1) {
            show("Please upload one and only one file", Type.ERROR_MESSAGE);
            return;
        }
        Html5File html5File = files[0];
        String fileName = html5File.getFileName();

        if (html5File.getFileSize() > FILE_SIZE_LIMIT) {
            show("File rejected. Max 2Mb files are accepted", Type.WARNING_MESSAGE);
            return;
        }
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        StreamVariable streamVariable = new StreamVariable() {

            @Override
            public OutputStream getOutputStream() {
                return bas;
            }

            @Override
            public boolean listenProgress() {
                return false;
            }

            @Override
            public void onProgress(StreamingProgressEvent event) {
            }

            @Override
            public void streamingStarted(StreamingStartEvent event) {
            }

            @Override
            public void streamingFinished(StreamingEndEvent event) {
                progress.setVisible(false);
                onFileUpdated(fileName, bas);
            }

            @Override
            public void streamingFailed(StreamingErrorEvent event) {
                progress.setVisible(false);
            }

            @Override
            public boolean isInterrupted() {
                return false;
            }
        };
        html5File.setStreamVariable(streamVariable);
        progress.setVisible(true);
    }



    private void onFileUpdated(String name, ByteArrayOutputStream outputStream) {
        show("File " + name + " uploaded!");
        byte[] bytes = outputStream.toByteArray();
        fileUpdateListener.onFileUpdated(name, bytes);
    }

    @Override
    public AcceptCriterion getAcceptCriterion() {
        return AcceptAll.get();
    }

    public void setFileUpdateListener(FileUpdateListener fileUpdateListener) {
        this.fileUpdateListener = fileUpdateListener;
    }
}
