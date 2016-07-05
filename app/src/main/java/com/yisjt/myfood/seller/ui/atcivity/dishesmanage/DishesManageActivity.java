package com.yisjt.myfood.seller.ui.atcivity.dishesmanage;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kxiang.photopicker.PhotoPickerActivity;
import com.kxiang.photopicker.utils.PhotoPickerIntent;
import com.yisjt.myfood.seller.R;
import com.yisjt.myfood.seller.base.BaseActivity;
import com.yisjt.myfood.seller.ui.atcivity.dishesmanage.presenter.IDishesManagePresenterImpl;
import com.yisjt.myfood.seller.ui.atcivity.dishesmanage.view.IDishesManageView;
import com.yisjt.myfood.seller.utils.DensityUtil;
import com.yisjt.myfood.seller.utils.MyFoodFoodFields;
import com.yisjt.myfood.seller.utils.view.PromptDialog;
import com.yisjt.myfood.seller.utils.view.TitleFirstBar;

import java.util.ArrayList;
import java.util.List;


public class DishesManageActivity extends BaseActivity
        implements View.OnClickListener, IDishesManageView,
        CheckBox.OnCheckedChangeListener {

    public final static int REQUEST_CODE = 1;
    /**
     * 菜品系
     */
    private TextView tv_dishes_kind;
    private CheckBox cb_dishes_kind_first;
    private CheckBox cb_dishes_kind_second;
    private CheckBox cb_dishes_kind_thirdly;
    private CheckBox cb_dishes_kind_other;
    /**
     * 图片上传
     */
    private TextView tv_dishes_image;
    /**
     * 菜品介绍
     */
    private TextView tv_dishes_explain_first;
    private TextView tv_dishes_explain_second;
    /**
     * 就餐方式
     */
    private TextView tv_eat_way;
    /**
     * 上门取餐
     */
    private CheckBox cb_eat_go;

    /**
     * 上门就餐
     */
    private CheckBox cb_eat_here;
    /**
     * 送餐上门
     */
    private CheckBox cb_eat_home;
    /**
     * 上架
     */
    private TextView tv_dishes_up;
    /**
     * 下架
     */
    private TextView tv_dishes_down;
    private Button btn_ok;
    private EditText et_dishes_name;
    private EditText et_dishes_price;
    private RecyclerView rlv_dishes_image;
    private IDishesManagePresenterImpl dishesManage;
    private PromptDialog dialog;

    private TitleFirstBar titleBar;

    private DishesImageAdaper imageAdaper;
    private ImageView iv_delete_name;
    private ImageView iv_delete_price;
    private RadioGroup tg_up_or_down;

    ArrayList<String> selectedPhotos;
    private List<String> dishes_kind;
    private List<String> eat_way;
    private boolean dishesUpOrDwon = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_manage);
        initView(savedInstanceState);
        initBLL(savedInstanceState);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        dishes_kind = new ArrayList<>();
        eat_way = new ArrayList<>();

        dialog = new PromptDialog(this);
        dishesManage = new IDishesManagePresenterImpl(this);
        titleBar = new TitleFirstBar(this);
        selectedPhotos = new ArrayList<>();
        tv_dishes_kind = (TextView) findViewById(R.id.tv_dishes_kind);
        cb_dishes_kind_first = (CheckBox) findViewById(R.id.cb_dishes_kind_first);
        cb_dishes_kind_second = (CheckBox) findViewById(R.id.cb_dishes_kind_second);
        cb_dishes_kind_thirdly = (CheckBox) findViewById(R.id.cb_dishes_kind_thirdly);
        cb_dishes_kind_other = (CheckBox) findViewById(R.id.cb_dishes_kind_other);
        tv_dishes_image = (TextView) findViewById(R.id.tv_dishes_image);
        tv_dishes_explain_first = (TextView) findViewById(R.id.tv_dishes_explain_first);
        tv_dishes_explain_second = (TextView) findViewById(R.id.tv_dishes_explain_second);
        tv_dishes_image = (TextView) findViewById(R.id.tv_dishes_image);
        tv_eat_way = (TextView) findViewById(R.id.tv_eat_way);
        cb_eat_go = (CheckBox) findViewById(R.id.cb_eat_go);
        cb_eat_here = (CheckBox) findViewById(R.id.cb_eat_here);
        cb_eat_home = (CheckBox) findViewById(R.id.cb_eat_home);
        tv_dishes_up = (TextView) findViewById(R.id.tv_dishes_up);
        tv_dishes_down = (TextView) findViewById(R.id.tv_dishes_down);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        et_dishes_name = (EditText) findViewById(R.id.et_dishes_name);
        et_dishes_price = (EditText) findViewById(R.id.et_dishes_price);
        rlv_dishes_image = (RecyclerView) findViewById(R.id.rlv_dishes_image);
        iv_delete_name = (ImageView) findViewById(R.id.iv_delete_name);
        iv_delete_price = (ImageView) findViewById(R.id.iv_delete_price);
        tg_up_or_down = (RadioGroup) findViewById(R.id.tg_up_or_down);


        cb_dishes_kind_first.setOnCheckedChangeListener(this);
        cb_dishes_kind_second.setOnCheckedChangeListener(this);
        cb_dishes_kind_thirdly.setOnCheckedChangeListener(this);
        cb_dishes_kind_other.setOnCheckedChangeListener(this);

        cb_eat_go.setOnCheckedChangeListener(this);
        cb_eat_here.setOnCheckedChangeListener(this);
        cb_eat_home.setOnCheckedChangeListener(this);

        btn_ok.setOnClickListener(this);
        titleBar.title_imgbtn_left.setOnClickListener(this);
        tg_up_or_down.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.e("checkedId", "checkedId:" + checkedId);
            }
        });
    }

    @Override
    protected void initBLL(Bundle savedInstanceState) {


        cb_dishes_kind_first.setChecked(true);
        cb_eat_home.setChecked(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rlv_dishes_image.setLayoutManager(gridLayoutManager);

        imageAdaper = new DishesImageAdaper(this);
        rlv_dishes_image.setAdapter(imageAdaper);
        List<String> list = new ArrayList<>();
        imageAdaper.SetData(list);

        Resources res = getResources();
        dishesManage.pTextStyle(new String[]{
                res.getString(R.string.dishes_kind),
                res.getString(R.string.dishes_image),
                res.getString(R.string.dishes_explain),
                res.getString(R.string.eat_way)
        }, new String[]{
                res.getString(R.string.multiple_choices),
                res.getString(R.string.at_least_one),
                res.getString(R.string.select_fill),
                res.getString(R.string.multiple_choices)
        }, R.color.color_9a9a9a);

        titleBar.title_txt_center.setText(getResources().getString(R.string.new_dishes));

        setDishesNameChange(et_dishes_name, iv_delete_name);
        setPricePoint(et_dishes_price, iv_delete_price);

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.cb_dishes_kind_first:
                dishesManage.selectKind(dishes_kind, MyFoodFoodFields.SICHUAN_CUISINE, isChecked);
                checkBackgroudColor(cb_dishes_kind_first, isChecked);
                break;
            case R.id.cb_dishes_kind_second:
                dishesManage.selectKind(dishes_kind, MyFoodFoodFields.NORTHEASTERN_CHINESE_CUISINE, isChecked);
                checkBackgroudColor(cb_dishes_kind_second, isChecked);

                break;
            case R.id.cb_dishes_kind_thirdly:
                dishesManage.selectKind(dishes_kind, MyFoodFoodFields.HUNAN_CUISINE, isChecked);
                checkBackgroudColor(cb_dishes_kind_thirdly, isChecked);
                break;
            case R.id.cb_dishes_kind_other:
                dishesManage.selectKind(dishes_kind, MyFoodFoodFields.OTHER_CUISINE, isChecked);
                checkBackgroudColor(cb_dishes_kind_other, isChecked);
                break;
            case R.id.cb_eat_home:
                dishesManage.selectKind(eat_way, MyFoodFoodFields.DELIVER, isChecked);
                checkBackgroudColor(cb_eat_home, isChecked);
                break;
            case R.id.cb_eat_here:
                dishesManage.selectKind(eat_way, MyFoodFoodFields.COME, isChecked);
                checkBackgroudColor(cb_eat_here, isChecked);
                break;
            case R.id.cb_eat_go:
                dishesManage.selectKind(eat_way, MyFoodFoodFields.TAKE, isChecked);
                checkBackgroudColor(cb_eat_go, isChecked);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("photos", "requestCode:" + requestCode);
        List<String> photos = null;
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
            }
            selectedPhotos.clear();

            if (photos != null) {
                selectedPhotos.addAll(photos);
                imageAdaper.SetData(selectedPhotos);
            }
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.title_imgbtn_left:

                finish();
                break;
            case R.id.btn_ok:

                dishesManage.pDishUpLoading(dishes_kind,
                        et_dishes_name.getText().toString(), et_dishes_price.getText().toString(),
                        selectedPhotos, eat_way, dishesUpOrDwon);
                break;


        }
    }


    public void setDishesNameChange(final EditText editText, final ImageView imageView) {

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    imageView.setImageResource(R.drawable.error);
                } else {
                    imageView.setImageResource(R.drawable.delete);
                }
            }

        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

    }


    public void setPricePoint(final EditText editText, final ImageView imageView) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    imageView.setImageResource(R.drawable.error);
                } else {
                    imageView.setImageResource(R.drawable.delete);
                }
            }

        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }


    /**
     * 设置对应位置字体颜色
     *
     * @param v
     * @param b
     */
    private void checkBackgroudColor(TextView v, boolean b) {
        if (b) {
            v.setTextColor(getResources().getColor(R.color.color_ffffff));
        } else {
            v.setTextColor(getResources().getColor(R.color.color_686C6B));
        }
    }

    @Override
    public void vTextKindStyle(SpannableStringBuilder ssb) {
        tv_dishes_kind.setText(ssb);
    }

    @Override
    public void vTextExplainFirstStyle(SpannableStringBuilder ssb) {
        tv_dishes_explain_first.setText(ssb);
    }

    @Override
    public void vEatWay(SpannableStringBuilder ssb) {
        tv_eat_way.setText(ssb);
    }

    @Override
    public void vDishesImage(SpannableStringBuilder ssb) {
        tv_dishes_image.setText(ssb);
    }

    @Override
    public void vDishesKindSizeZero() {
        dialog.showLoginDialog(getResources().getString(R.string.sure),
                getResources().getString(R.string.cancel),
                getResources().getString(R.string.at_least_dishes));
    }

    @Override
    public void vDishesNameEmpty() {
        dialog.showLoginDialog(getResources().getString(R.string.sure),
                getResources().getString(R.string.cancel),
                getResources().getString(R.string.input_dishes_name));
    }

    @Override
    public void vDishesPriceEmpty() {
        dialog.showLoginDialog(getResources().getString(R.string.sure),
                getResources().getString(R.string.cancel),
                getResources().getString(R.string.input_dishes_price));
    }

    @Override
    public void vDishesImageSizeZero() {
        dialog.showLoginDialog(getResources().getString(R.string.sure),
                getResources().getString(R.string.cancel),
                getResources().getString(R.string.at_least_image));
    }

    @Override
    public void vEatWaySizeZero() {
        dialog.showLoginDialog(getResources().getString(R.string.sure),
                getResources().getString(R.string.cancel),
                getResources().getString(R.string.select_eat_way));
    }

    @Override
    public void vDishUpLoading(List<String> dishes_kind, String dishes_name,
                               int dishes_price, List<String> selectedPhotos,
                               List<String> eat_way, boolean dishesUpOrDwon) {


    }


    protected class DishesImageAdaper extends RecyclerView.Adapter {

        private List<String> imagePath;
        private LayoutInflater inflater;
        private Context context;
        private int imageMeasure;

        public DishesImageAdaper(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            imageMeasure = DensityUtil.dipTopx(context, 80);
        }

        public void SetData(List<String> imagePath) {
            this.imagePath = imagePath;
            notifyDataSetChanged();
        }

        public class ImageShowHoler extends RecyclerView.ViewHolder {
            public ImageView iv_dishes_one;

            public ImageShowHoler(View itemView) {
                super(itemView);
                iv_dishes_one = (ImageView) itemView.findViewById(R.id.iv_dishes_one);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ImageShowHoler(inflater.inflate(R.layout.item_dishes_image, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ImageShowHoler imageShowHoler = (ImageShowHoler) holder;

            if (imagePath.size() == 6) {
                Glide.with(context)
                        .load(imagePath.get(position))
                        .centerCrop()
                        .override(imageMeasure, imageMeasure)
                        .into(imageShowHoler.iv_dishes_one);
            } else {
                if (position == imagePath.size()) {
                    imageShowHoler.iv_dishes_one.setImageResource(R.drawable.upload);
                } else {
                    Glide.with(context)
                            .load(imagePath.get(position))
                            .centerCrop()
                            .override(imageMeasure, imageMeasure)
                            .into(imageShowHoler.iv_dishes_one);
                }

            }
            imageShowHoler.iv_dishes_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DishesManageActivity.this, PhotoPickerActivity.class);
                    PhotoPickerIntent.setPhotoCount(intent, 6);
                    PhotoPickerIntent.setShowCamera(intent, true);
                    PhotoPickerIntent.setSelected(intent, selectedPhotos);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            });

        }

        @Override
        public int getItemCount() {
            if (imagePath == null) {
                return 0;
            } else if (imagePath.size() == 6) {
                return imagePath.size();
            } else {
                return imagePath.size() + 1;
            }

        }
    }
}
