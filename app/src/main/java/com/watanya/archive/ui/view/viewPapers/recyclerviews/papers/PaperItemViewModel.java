package com.watanya.archive.ui.view.viewPapers.recyclerviews.papers;

import android.databinding.ObservableField;
import android.os.Bundle;

import com.watanya.archive.data.interfaces.ViewHolderInterface;
import com.watanya.archive.data.model.Paper;

public class PaperItemViewModel {

    public Paper paper;
    public ViewHolderInterface navigator;
    public ObservableField<String> paperTitle = new ObservableField<>("");

    public PaperItemViewModel(Paper paper) {
        this.paper = paper;
        paperTitle.set(paper.title);
    }

    public PaperItemViewModel(Paper paper, ViewHolderInterface navigator) {
        this.paper = paper;
        this.navigator = navigator;
        paperTitle.set(paper.title);
    }

    public void onItemClicked ()
    {
        Bundle extras = new Bundle();
        extras.putSerializable("paper", paper);
        navigator.onItemClicked(extras);
    }

}
