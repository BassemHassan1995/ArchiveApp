package com.watanya.archive.ui.view.viewPapers;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.watanya.archive.data.model.File;
import com.watanya.archive.data.model.Paper;
import com.watanya.archive.ui.base.BaseViewModel;

public class ViewPapersViewModel extends BaseViewModel<ViewPapersNavigator> {

    public ObservableArrayList<Paper> papers = new ObservableArrayList<>();
    public ObservableField<String> fileName = new ObservableField<>();

    public ViewPapersViewModel(ViewPapersNavigator navigator) {
        super(navigator);

        getFile();
    }

    private void getFile() {
        File file = getNavigator().getFile ();
        papers.addAll(file.papers);
        fileName.set(file.name);
    }

}