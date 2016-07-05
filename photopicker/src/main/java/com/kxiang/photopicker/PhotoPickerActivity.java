package com.kxiang.photopicker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kxiang.photopicker.entity.Photo;
import com.kxiang.photopicker.event.OnItemCheckListener;
import com.kxiang.photopicker.fragment.ImagePagerFragment;
import com.kxiang.photopicker.fragment.PhotoPickerFragment;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class PhotoPickerActivity extends FragmentActivity implements View.OnClickListener {

    private PhotoPickerFragment pickerFragment;
    private ImagePagerFragment imagePagerFragment;

    public final static String EXTRA_MAX_COUNT = "MAX_COUNT";
    public final static String EXTRA_SHOW_CAMERA = "SHOW_CAMERA";
    public final static String EXTRA_SHOW_GIF = "SHOW_GIF";
    public final static String KEY_SELECTED_PHOTOS = "SELECTED_PHOTOS";
    public final static String EXTRA_GRID_COLUMN = "column";
    public final static String EXTRA_ORIGINAL_PHOTOS = "ORIGINAL_PHOTOS";

    public final static int DEFAULT_MAX_COUNT = 9;
    public final static int DEFAULT_COLUMN_NUMBER = 3;

    private int maxCount = DEFAULT_MAX_COUNT;

    private boolean showGif = false;
    private int columnNumber = DEFAULT_COLUMN_NUMBER;
    private ArrayList<String> originalPhotos = null;
    private ImageView iv_back;
    private TextView tv_photo_number;
    private View view_fill;
    private RelativeLayout rl_show;

    private boolean imagePagerFragmentShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean showCamera = getIntent().getBooleanExtra(EXTRA_SHOW_CAMERA, true);
        boolean showGif = getIntent().getBooleanExtra(EXTRA_SHOW_GIF, false);
        setShowGif(showGif);
        setContentView(R.layout.__picker_activity_photo_picker);

        maxCount = getIntent().getIntExtra(EXTRA_MAX_COUNT, DEFAULT_MAX_COUNT);
        columnNumber = getIntent().getIntExtra(EXTRA_GRID_COLUMN, DEFAULT_COLUMN_NUMBER);
        originalPhotos = getIntent().getStringArrayListExtra(EXTRA_ORIGINAL_PHOTOS);

        Log.e("columnNumber", "columnNumber:" + columnNumber);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_photo_number = (TextView) findViewById(R.id.tv_photo_number);
        view_fill = findViewById(R.id.view_fill);
        rl_show = (RelativeLayout) findViewById(R.id.rl_show);

        iv_back.setOnClickListener(this);
        tv_photo_number.setOnClickListener(this);


        pickerFragment = PhotoPickerFragment.newInstance(showCamera, showGif, columnNumber, maxCount, originalPhotos);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, pickerFragment)
                .commit();

        getSupportFragmentManager().executePendingTransactions();


        int size = originalPhotos.size();
        if (size == 0) {
            tv_photo_number.setEnabled(false);
        } else {
            tv_photo_number.setEnabled(true);
            String sAgeFormat = getResources().getString(R.string.__picker_done_with_count);
            String sFinalAge = String.format(sAgeFormat, size, maxCount);
            tv_photo_number.setText(sFinalAge);
        }


        pickerFragment.getPhotoGridAdapter().setOnItemCheckListener(new OnItemCheckListener() {
            @Override
            public void OnItemCheck(int position, Photo photo, boolean tempToast, int selectedItemCount) {

                if (selectedItemCount == 0) {
                    tv_photo_number.setEnabled(false);
                } else {
                    tv_photo_number.setEnabled(true);
                }

                if (maxCount <= 1) {
                    List<Photo> photos = pickerFragment.getPhotoGridAdapter().getSelectedPhotos();
                    if (!photos.contains(photo)) {
                        photos.clear();
                        pickerFragment.getPhotoGridAdapter().notifyDataSetChanged();
                    }

                }

                if (tempToast) {
                    String sAgeFormat = getResources().getString(R.string.__picker_done_with_count);
                    String sFinalAge = String.format(sAgeFormat, selectedItemCount, maxCount);
                    tv_photo_number.setText(sFinalAge);
                } else {
                    Toast.makeText(getActivity(), getString(R.string.__picker_over_max_count_tips, maxCount),
                            LENGTH_LONG).show();
                }

            }
        });

    }


    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void finish() {

        if (imagePagerFragmentShow) {

        } else {
            Intent intent = new Intent();
            ArrayList<String> selectedPhotos = pickerFragment.getPhotoGridAdapter().getSelectedPhotoPaths();
            intent.putStringArrayListExtra(KEY_SELECTED_PHOTOS, selectedPhotos);
            setResult(RESULT_OK, intent);
            super.finish();
        }


    }

    /**
     * Overriding this method allows us to run our exit animation first, then exiting
     * the activity when it complete.
     */
    @Override
    public void onBackPressed() {
        view_fill.setVisibility(View.VISIBLE);
        rl_show.setBackgroundColor(Color.parseColor("#19dd9f"));
        imagePagerFragmentShow = false;
        //  getSupportFragmentManager().beginTransaction().show(pickerFragment).commit();

        if (imagePagerFragment != null && imagePagerFragment.isVisible()) {
            imagePagerFragment.runExitAnimation(new Runnable() {
                public void run() {
                    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        getSupportFragmentManager().popBackStack();
                    }
                }
            });
        } else {
            super.onBackPressed();
        }
    }


    public void addImagePagerFragment(ImagePagerFragment imagePagerFragment) {
        //   getSupportFragmentManager().beginTransaction().hide(pickerFragment).commit();
        imagePagerFragmentShow = true;
        this.imagePagerFragment = imagePagerFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, this.imagePagerFragment)
                .addToBackStack(null)
                .commit();
        view_fill.setVisibility(View.GONE);
        rl_show.setBackgroundColor(Color.parseColor("#00000000"));
    }


    public PhotoPickerActivity getActivity() {
        return this;
    }

    public boolean isShowGif() {
        return showGif;
    }

    public void setShowGif(boolean showGif) {
        this.showGif = showGif;
    }


}
