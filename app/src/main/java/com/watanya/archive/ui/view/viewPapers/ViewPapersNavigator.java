package com.watanya.archive.ui.view.viewPapers;

import android.os.Bundle;

import com.watanya.archive.data.model.File;
import com.watanya.archive.ui.CommonActions;

public interface ViewPapersNavigator extends CommonActions {

    File getFile();

    void OpenPaperActivity(Bundle extras);
}