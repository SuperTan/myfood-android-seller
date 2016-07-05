package com.kxiang.photopicker.event;

import com.kxiang.photopicker.entity.Photo;

/**
 *
 */
public interface OnItemCheckListener {

  /***
   *
   * @param position 所选图片的位置
   * @param path     所选的图片
   * @param selectedItemCount  已选数量
   * @return enable check
   */
  void OnItemCheck(int position, Photo path,boolean isCheck ,int selectedItemCount);

}
