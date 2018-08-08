package com.watanya.archive.ui.view.viewfiles.recyclerviews.files;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.watanya.archive.data.model.File;
import com.watanya.archive.databinding.FileListItemBinding;
import com.watanya.archive.ui.base.BaseViewHolder;
import com.watanya.archive.ui.view.viewfiles.recyclerviews.files.FileItemViewModel;

import java.util.ArrayList;

public class FilesAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<File> files;

    public FilesAdapter(ArrayList<File> files) {
        this.files = files;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FileListItemBinding mBinding = FileListItemBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new FileViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (files == null)
            return 0;
        return files.size();
    }



    public class FileViewHolder extends BaseViewHolder {

        private FileListItemBinding mBinding;
        private FileItemViewModel mFileItemViewModel;

        public FileViewHolder(FileListItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final File selected = files.get(position);
            mFileItemViewModel = new FileItemViewModel(selected);
            mBinding.setViewModel(mFileItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }


    }
}
