package com.watanya.archive.ui.view.viewPapers.recyclerviews.papers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.watanya.archive.data.interfaces.ViewHolderInterface;
import com.watanya.archive.data.model.Paper;
import com.watanya.archive.databinding.FileListItemBinding;
import com.watanya.archive.databinding.PaperListItemBinding;
import com.watanya.archive.ui.base.BaseViewHolder;
import com.watanya.archive.ui.view.viewfiles.recyclerviews.files.FileItemViewModel;

import java.util.ArrayList;

public class PapersAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public ArrayList<Paper> papers;
    public ViewHolderInterface navigator;

    public PapersAdapter(ArrayList<Paper> papers) {
        this.papers = papers;
    }

    public PapersAdapter(ArrayList<Paper> papers, ViewHolderInterface navigator) {
        this.papers = papers;
        this.navigator = navigator;
        }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PaperListItemBinding mBinding = PaperListItemBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new PapersAdapter.PaperViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (papers == null)
            return 0;
        return papers.size();
    }

    public class PaperViewHolder extends BaseViewHolder {

        private PaperListItemBinding mBinding;
        private PaperItemViewModel mPaperItemViewModel;

        public PaperViewHolder(PaperListItemBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }

        @Override
        public void onBind(int position) {
            Paper paper = papers.get(position);
            mPaperItemViewModel = new PaperItemViewModel(paper, navigator);
            mBinding.setViewModel(mPaperItemViewModel);

            mBinding.executePendingBindings();

        }
    }
}
