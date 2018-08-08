package com.watanya.archive.ui.view.main;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import com.watanya.archive.data.interfaces.SpinnerItemInterface;
import com.watanya.archive.data.model.Category;
import com.watanya.archive.data.model.File;
import com.watanya.archive.ui.base.BaseViewModel;
import com.watanya.archive.ui.view.createCategory.CreateCategoryActivity;
import com.watanya.archive.ui.view.createFile.CreateFileActivity;
import com.watanya.archive.ui.view.test.TestActivity;
import com.watanya.archive.ui.view.viewfiles.ViewFilesActivity;

import java.util.ArrayList;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private Category selectedCategory;
    private File selectedFile;
    public ObservableInt selectIndex = new ObservableInt(0);
    private ArrayList<Category> categories = new ArrayList<>();
    public ObservableArrayList<String> categoryNames = new ObservableArrayList<>();
    public ObservableArrayList<String> fileNames = new ObservableArrayList<>();
    public SpinnerItemInterface categoryItemInterface;
    public SpinnerItemInterface fileItemInterface;

    public MainViewModel(MainNavigator navigator) {
        super(navigator);
        initListeners();
        getCategories();
    }

    private void initListeners() {
        setCategorySelectedListener();
        setFileSelectedListener();
    }

    private void setCategorySelectedListener() {
        categoryItemInterface = new SpinnerItemInterface() {
            @Override
            public void onClick(int position) {

                selectedCategory = categories.get(position);
                selectIndex.set(0);
                fileNames.clear();
                fileNames.addAll(selectedCategory.getFilesNames());
            }
        };
    }

    private void setFileSelectedListener() {
        fileItemInterface = new SpinnerItemInterface() {
            @Override
            public void onClick(int position) {
                selectIndex.set(position);
                selectedFile = position==0 ? null : selectedCategory.files.get(position-1);
            }
        };
    }

    private void getCategories() {
        categories.add(new Category("Category 1"));
        categories.add(new Category("Category 2"));
        categories.add(new Category("Category 3"));
        categories.add(new Category("Category 4"));
        categories.add(new Category("Category 5"));

        for (Category c : categories) {
            categoryNames.add(c.name);
        }

    }

    public void onViewFilesButtonClicked() {
        if (selectedFile == null)
        {
            getNavigator().openViewFilesActivity(ViewFilesActivity.class , selectedCategory);
        }
        else
        {
            //TODO: Make ViewFileActivity
            getNavigator().openViewFileActivity(ViewFilesActivity.class, selectedFile);
        }
    }

    public void onAddFileButtonClicked() {
        getNavigator().openActivity(CreateFileActivity.class);
    }

    public void onAddCategoryButtonClicked() {
        getNavigator().openActivity(CreateCategoryActivity.class);
    }

    public void onGalleryButtonClicked()
    {
        getNavigator().openActivity(TestActivity.class);
    }
}