package com.watanya.archive.ui.view.viewPapers.recyclerviews.papers;

import android.databinding.ObservableField;

import com.watanya.archive.data.model.Paper;

public class PaperItemViewModel {

    public Paper paper;
    public ObservableField<String> paperTitle = new ObservableField<>("");

    public PaperItemViewModel(Paper paper) {
        this.paper = paper;
        paperTitle.set(paper.title);
    }
}
