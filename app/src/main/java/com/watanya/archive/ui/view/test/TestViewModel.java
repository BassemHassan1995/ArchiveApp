package com.watanya.archive.ui.view.test;

import com.watanya.archive.ui.base.BaseViewModel;

import java.util.ArrayList;

public class TestViewModel extends BaseViewModel<TestNavigator> {

    public ArrayList<String> urls = new ArrayList<>();

    public TestViewModel(TestNavigator navigator) {
        super(navigator);
        initUrlList();

        getNavigator().showGallery(urls, 0);
    }

    private void initUrlList() {
        urls.add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png");
        urls.add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png");
        urls.add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png");
        urls.add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png");
        urls.add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png");
    }

}