package com.watanya.archive.ui.view.viewfiles;

import android.os.Bundle;

import com.watanya.archive.data.model.Category;
import com.watanya.archive.ui.CommonActions;

public interface ViewFilesNavigator extends CommonActions {

    Category getCategory();

    void openViewPapersActivity(Bundle extras);
}