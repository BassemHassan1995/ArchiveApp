package com.watanya.archive.ui.view.main;

import com.watanya.archive.data.model.Category;
import com.watanya.archive.data.model.File;
import com.watanya.archive.ui.CommonActions;
import com.watanya.archive.ui.base.BaseActivity;
import com.watanya.archive.ui.view.viewPapers.ViewPapersActivity;
import com.watanya.archive.ui.view.viewfiles.ViewFilesActivity;

public interface MainNavigator extends CommonActions {

    void openActivity(Class<? extends BaseActivity> activity);
    void openViewFileActivity(Class<ViewPapersActivity> viewFilesActivityClass, File selectedFile);
    void openViewFilesActivity(Class<ViewFilesActivity> viewFilesActivityClass, Category selectedCategory);
}