package com.watanya.archive.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.watanya.archive.ui.view.test.TestNavigator;
import com.watanya.archive.ui.view.test.TestViewModel;
import com.watanya.archive.ui.view.viewPapers.ViewPapersNavigator;
import com.watanya.archive.ui.view.viewPapers.ViewPapersViewModel;
import com.watanya.archive.ui.view.viewfiles.ViewFilesNavigator;
import com.watanya.archive.ui.view.viewfiles.ViewFilesViewModel;
import com.watanya.archive.ui.view.createCategory.CreateCategoryNavigator;
import com.watanya.archive.ui.view.createCategory.CreateCategoryViewModel;
import com.watanya.archive.ui.view.createFile.CreateFileNavigator;
import com.watanya.archive.ui.view.createFile.CreateFileViewModel;
import com.watanya.archive.ui.view.main.MainNavigator;
import com.watanya.archive.ui.view.main.MainViewModel;
import com.watanya.archive.ui.view.splash.SplashNavigator;
import com.watanya.archive.ui.view.splash.SplashViewModel;
public class ViewModelFactory implements ViewModelProvider.Factory {

    private Object navigator;

    public ViewModelFactory(Object navigator) {
        this.navigator = navigator;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class))
            return (T) new MainViewModel((MainNavigator) navigator);
        else if (modelClass.isAssignableFrom(SplashViewModel.class))
            return (T) new SplashViewModel((SplashNavigator) navigator);
        else if (modelClass.isAssignableFrom(ViewFilesViewModel.class))
            return (T) new ViewFilesViewModel((ViewFilesNavigator) navigator);
        else if (modelClass.isAssignableFrom(ViewPapersViewModel.class))
            return (T) new ViewPapersViewModel((ViewPapersNavigator) navigator);
        else if (modelClass.isAssignableFrom(CreateFileViewModel.class))
            return (T) new CreateFileViewModel((CreateFileNavigator) navigator);
        else if (modelClass.isAssignableFrom(CreateCategoryViewModel.class))
            return (T) new CreateCategoryViewModel((CreateCategoryNavigator) navigator);
        else if (modelClass.isAssignableFrom(TestViewModel.class))
            return (T) new TestViewModel((TestNavigator) navigator);

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
