package com.watanya.archive.ui.view.viewfiles.recyclerviews.files;

import android.databinding.ObservableField;
import android.os.Bundle;

import com.watanya.archive.data.interfaces.ViewHolderInterface;
import com.watanya.archive.data.model.File;

public class FileItemViewModel {

    public File file;
    public ViewHolderInterface navigator;
    public ObservableField<String> fileName = new ObservableField<>("");
    public ObservableField<String> fileNote = new ObservableField<>("");

    public FileItemViewModel(File file, ViewHolderInterface navigator) {
        this.file = file;
        this.navigator = navigator;
        fileName.set(file.name);
        fileNote.set(file.note);

    }

    public void onItemClicked() {
        Bundle extras = new Bundle();
        extras.putSerializable("file", file);
        navigator.onItemClicked(extras);
    }

}
