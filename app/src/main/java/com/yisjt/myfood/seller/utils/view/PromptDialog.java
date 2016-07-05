package com.yisjt.myfood.seller.utils.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.yisjt.myfood.seller.R;

/**
 * Created by Administrator on 2016/6/30.
 */
public class PromptDialog {

    private Dialog dialog;
    private Button btn_cancel;
    private Button btn_sure;
    private TextView tv_the_clues;

    public PromptDialog(Context context) {
        dialog = new Dialog(context,R.style.CustomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.prompt_dialog);

        dialog.setCanceledOnTouchOutside(true);
        tv_the_clues = (TextView) dialog.findViewById(R.id.tv_the_clues);
        btn_sure = (Button) dialog.findViewById(R.id.btn_sure);
        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onSureFinishListener != null) {
                    onSureFinishListener.onSureFinish();
                }

            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public interface OnSureFinishListener {
        void onSureFinish();
    }

    private OnSureFinishListener onSureFinishListener;

    public void setOnSureFinishListener(OnSureFinishListener onSureFinishListener) {
        this.onSureFinishListener = onSureFinishListener;
    }

    public void showLoginDialog(String sure, String cancel, String clues) {

        btn_sure.setText(sure);
        btn_cancel.setText(cancel);
        tv_the_clues.setText(clues);
        dialog.show();

    }
}
