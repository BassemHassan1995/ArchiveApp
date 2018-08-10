package com.watanya.archive.ui.view.viewPapers;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.watanya.archive.data.interfaces.ViewHolderInterface;
import com.watanya.archive.data.model.File;
import com.watanya.archive.data.model.Paper;
import com.watanya.archive.ui.base.BaseViewModel;

public class ViewPapersViewModel extends BaseViewModel<ViewPapersNavigator> {

    public ViewHolderInterface viewHolderNavigator ;
    public ObservableArrayList<Paper> papers = new ObservableArrayList<>();
    public ObservableField<String> fileName = new ObservableField<>();

    public ViewPapersViewModel(ViewPapersNavigator navigator) {
        super(navigator);

        initInterfaces();
        getFile();
    }

    private void initInterfaces() {
        viewHolderNavigator = new ViewHolderInterface() {
            @Override
            public void onItemClicked(Bundle extras) {
                getNavigator().OpenPaperActivity(extras);
            }
        };
    }

    private void getFile() {
        File file = getNavigator().getFile ();
        papers.addAll(file.papers);
        fileName.set(file.name);
    }

}