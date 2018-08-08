package com.watanya.archive.ui.view.viewfiles;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.watanya.archive.data.model.Category;
import com.watanya.archive.data.model.File;
import com.watanya.archive.ui.base.BaseViewModel;

public class ViewFilesViewModel extends BaseViewModel<ViewFilesNavigator> {

    public ObservableArrayList<File> files = new ObservableArrayList<>();
    public ObservableField<String> categoryName = new ObservableField<>();

    public ViewFilesViewModel(ViewFilesNavigator navigator) {
        super(navigator);

        getCategory();
    }

    private void getCategory() {
        Category category = getNavigator().getCategory();
        files.addAll(category.files);
        categoryName.set(category.name);
    }

}