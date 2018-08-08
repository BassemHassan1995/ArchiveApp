package com.watanya.archive.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;
import com.watanya.archive.data.interfaces.SpinnerItemInterface;
import com.watanya.archive.data.model.File;
import com.watanya.archive.data.model.Paper;
import com.watanya.archive.ui.view.viewPapers.recyclerviews.papers.PapersAdapter;
import com.watanya.archive.ui.view.viewfiles.recyclerviews.files.FilesAdapter;

import java.util.ArrayList;


public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }

    @BindingAdapter("roundedImageUrl")
    public static void setRoundedImageUrl(final ImageView imageView, String url) {
//        imageView.setBackgroundResource(R.drawable.rounded_carousel_image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setClipToOutline(true);
        }
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(CircularImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }

    @BindingAdapter("visibleIf")
    public static void changeVisibility(@NonNull View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter(value={"pageCount","imageListener", "imageClickListener" , "pageChangeListener"})
    public static void initializeCarouselView(CarouselView carouselView, int count, ImageListener imageListener, ImageClickListener imageClickListener , ViewPager.OnPageChangeListener onPageChangeListener){
        carouselView.setImageListener(imageListener);
        carouselView.setImageClickListener(imageClickListener);
        carouselView.setPageCount(count);
        carouselView.addOnPageChangeListener(onPageChangeListener);

    }

    @BindingAdapter("layout_width")
    public static void setLayoutHeight(View view, int width) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter({"image_resource"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter("itemClickListener")
    public static void setSpinnerItemClickListener (final Spinner spinner, AdapterView.OnItemSelectedListener listener){

        spinner.setOnItemSelectedListener(listener);
    }

    @BindingAdapter(value = {"spinnerItemInterface"})
    public static void initSpinner(final Spinner spinner, final SpinnerItemInterface listener){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listener.onClick(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner.setSelection(0,false);
    }

    @BindingAdapter(value = {"spinnerItemInterface","selectionIndex"})
    public static void initSpinner(final Spinner spinner, final SpinnerItemInterface listener, ObservableInt selectionIndex){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listener.onClick(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner.setSelection(selectionIndex.get(),false);
    }

    @BindingAdapter("filesItems")
    public static void setFilesItems (RecyclerView recyclerView, ArrayList<File> files){
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false  ));
        FilesAdapter adapter = new FilesAdapter(files);
        recyclerView.setAdapter(adapter);
    }
    @BindingAdapter("papersItems")
    public static void setPapersItems (RecyclerView recyclerView, ArrayList<Paper> papers){
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false  ));
        PapersAdapter adapter = new PapersAdapter(papers);
        recyclerView.setAdapter(adapter);
    }


}
