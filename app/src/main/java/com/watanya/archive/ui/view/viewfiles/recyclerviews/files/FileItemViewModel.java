package com.watanya.archive.ui.view.viewfiles.recyclerviews.files;

import android.databinding.ObservableField;
import android.util.Log;

import com.watanya.archive.data.model.File;

public class FileItemViewModel {

   public File file;
    public ObservableField<String> fileName = new ObservableField<>("");
    public ObservableField<String> fileNote = new ObservableField<>("");


    public FileItemViewModel(File file) {
        this.file = file;
        fileName.set(file.name);
        fileNote.set(file.note);
    }

}
