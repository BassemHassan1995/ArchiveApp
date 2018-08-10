package com.watanya.archive.ui.view.viewfiles;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.watanya.archive.data.interfaces.ViewHolderInterface;
import com.watanya.archive.data.model.Category;
import com.watanya.archive.data.model.File;
import com.watanya.archive.ui.base.BaseViewModel;

public class ViewFilesViewModel extends BaseViewModel<ViewFilesNavigator> {

    public ViewHolderInterface viewHolderNavigator;
    public ObservableArrayList<File> files = new ObservableArrayList<>();
    public ObservableField<String> categoryName = new ObservableField<>();

    public ViewFilesViewModel(ViewFilesNavigator navigator) {
        super(navigator);

        initInterfaces ();
        getCategory();
    }

    private void initInterfaces() {
        viewHolderNavigator = new ViewHolderInterface() {
            @Override
            public void onItemClicked(Bundle extras) {
                getNavigator().openViewPapersActivity(extras);
            }
        };

    }

    private void getCategory() {
        Category category = getNavigator().getCategory();
        files.addAll(category.files);
        categoryName.set(category.name);
    }

}