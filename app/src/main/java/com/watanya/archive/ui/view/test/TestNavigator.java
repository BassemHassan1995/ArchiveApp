package com.watanya.archive.ui.view.test;

import com.watanya.archive.ui.CommonActions;

import java.util.ArrayList;

public interface TestNavigator extends CommonActions {

    void showGallery(ArrayList<String> list, int startPosition);
}