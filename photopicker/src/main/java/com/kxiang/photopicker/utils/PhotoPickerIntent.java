package com.kxiang.photopicker.utils;

import android.content.Intent;

import com.kxiang.photopicker.PhotoPickerActivity;

import java.util.ArrayList;

/**
 *
 */
public class PhotoPickerIntent {
  public static void setPhotoCount(Intent intent, int photoCount) {
    intent.putExtra(PhotoPickerActivity.EXTRA_MAX_COUNT, photoCount);
  }

  public static void setShowCamera(Intent intent, boolean showCamera) {
    intent.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, showCamera);
  }

  public static void setShowGif(Intent intent, boolean showGif) {
    intent.putExtra(PhotoPickerActivity.EXTRA_SHOW_GIF, showGif);
  }

  public static void setColumn(Intent intent, int column) {
    intent.putExtra(PhotoPickerActivity.EXTRA_GRID_COLUMN, column);
  }

  /**
   * To set some photos that have been selected before
   * @param intent
   * @param imagesUri Selected photos
     */
  public static void setSelected(Intent intent, ArrayList<String> imagesUri) {
    intent.putExtra(PhotoPickerActivity.EXTRA_ORIGINAL_PHOTOS, imagesUri);
  }
}
