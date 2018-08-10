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
        urls.add("https://s22.postimg.cc/a2qq9tzy9/image.jpg");
        urls.add("https://homepages.cae.wisc.edu/~ece533/images/fruits.png");
        urls.add("https://homepages.cae.wisc.edu/~ece533/images/girl.png");
        urls.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        urls.add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png");
        urls.add("https://homepages.cae.wisc.edu/~ece533/images/cat.png");
        urls.add("https://homepages.cae.wisc.edu/~ece533/images/sails.png");
        urls.add("https://homepages.cae.wisc.edu/~ece533/images/tulips.png");


    }

}