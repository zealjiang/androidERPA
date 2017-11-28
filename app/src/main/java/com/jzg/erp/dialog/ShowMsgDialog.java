package com.jzg.erp.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.jzg.erp.R;

/**
 * 自定义弹出框显示
 * @author jzg
 * @Date 2015-05-12
 */
public class ShowMsgDialog extends Dialog {

	private ShowMsgDialog(Context context, boolean cancelable,OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}
	/**
	 * 定义的Dialog.   theme 调用R.style.dialog
	 * @param context
	 * @param theme
	 */
	public ShowMsgDialog(Context context, int theme) {
		super(context, theme);
	}

	private ShowMsgDialog(Context context) {
		super(context);
	}


	/**
	 * 带取消和确定按钮的dialog
	 * @param activity
	 * @param title
	 * @param content
	 * @param cancelclickListener
	 * @param okclickListener
     * @return
     */
	public static Dialog showAlert2Btn (Activity activity,String title,String content,View.OnClickListener cancelclickListener
			, View.OnClickListener okclickListener){
		final Dialog dialog = new Dialog(activity, R.style.MDialog);
		dialog.setContentView(R.layout.dialog_2_button);
		dialog.setCancelable(false);
		setWidth(activity,dialog);
		//标题
		TextView tvTitle =(TextView) dialog.findViewById(R.id.alert_title);
		tvTitle.setText(title);
		//内容
		TextView tvContent =(TextView) dialog.findViewById(R.id.alert_content);
		tvContent.setText(content);
		//按钮（取消、确定）
		Button alert_cancel_btn =(Button) dialog.findViewById(R.id.alert_cancel_btn);
		alert_cancel_btn.setOnClickListener(cancelclickListener);
		Button alert_ok_btn =(Button) dialog.findViewById(R.id.alert_ok_btn);
		alert_ok_btn.setOnClickListener(okclickListener);

		dialog.show();
		return dialog;
	}

	/**
	 * 不带取消和确定按钮的dialog
	 * @param activity
	 * @param title
	 * @param content
	 * @return
	 */
	public static Dialog showAlertNoBtn(Activity activity,String title,String content){
		final Dialog dialog = new Dialog(activity, R.style.MDialog);
		setWidth(activity,dialog);
		dialog.setContentView(R.layout.dialog_no_btn);
		dialog.setCancelable(true);

		//标题
		TextView tvTitle =(TextView) dialog.findViewById(R.id.alert_title);
		tvTitle.setText(title);
		//内容
		TextView tvContent =(TextView) dialog.findViewById(R.id.alert_content);
		tvContent.setText(content);


		dialog.show();
		return dialog;
	}

	private static Dialog setWidth(Activity activity,Dialog dialog){
		/*
         * 将对话框的大小按屏幕大小的百分比设置
         */
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
		Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.7); // 宽度设置为屏幕的0.7
        dialogWindow.setAttributes(p);
		return dialog;
	}


	/**
	 * 这是兼容的 AlertDialog 带取消和确定按钮
	 * @author zealjiang
	 * @time 2016/8/11 11:31
	 */
	public static AlertDialog.Builder showMaterialDialog2Btn(Activity activity, String title, String content, DialogInterface.OnClickListener cancelclickListener
			, DialogInterface.OnClickListener okclickListener) {
		  /*
		  这里使用了 android.support.v7.app.AlertDialog.Builder
		  可以直接在头部写 import android.support.v7.app.AlertDialog
		  那么下面就可以写成 AlertDialog.Builder
		  */
		android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
		builder.setTitle(title);
		builder.setMessage(content);
		builder.setNegativeButton("取消", cancelclickListener);
		builder.setPositiveButton("确定", okclickListener);
		builder.setCancelable(false);
		builder.show();
		return builder;
	}

	/**
	 * 这是兼容的 AlertDialog
	 */

	/**
	 * 这是兼容的 AlertDialog 不带取消和确定按钮
	 * @author zealjiang
	 * @time 2016/8/11 11:31
	 */
	public static AlertDialog.Builder showMaterialDialogNoBtn(Activity activity, String title, String content) {
		  /*
		  这里使用了 android.support.v7.app.AlertDialog.Builder
		  可以直接在头部写 import android.support.v7.app.AlertDialog
		  那么下面就可以写成 AlertDialog.Builder
		  */
		android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
		builder.setTitle(title);
		builder.setMessage(content);
		builder.setCancelable(true);
		builder.show();
		return builder;
	}


	/**
	 * 这是兼容的 AlertDialog
	 * @author zealjiang
	 * @time 2016/8/11 11:31
	 */
	public static void showMDPhone(Activity activity, String name, String shop,String phoneName,String phoneNum,int phoneColor, final DialogClickListener phoneListener) {

		View view = LayoutInflater.from(activity).inflate(R.layout.dialog_appraiser,null);
		android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
		builder.setCancelable(true);
		builder.setView(view);

		TextView tvName = (TextView) view.findViewById(R.id.tv_name);
		TextView tvShop =(TextView) view.findViewById(R.id.tv_shop);
		TextView tvPhoneName = (TextView) view.findViewById(R.id.tv_phone_name);
		TextView tvPhone = (TextView) view.findViewById(R.id.tv_phone);

		tvName.setText(name);
		tvShop.setText(shop);
		tvPhoneName.setText(phoneName);
		tvPhone.setText(phoneNum);
		tvPhone.setTextColor(phoneColor);
		final AlertDialog dialog = builder.show();
		tvPhone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				phoneListener.onClick(dialog);
			}
		});

	}

	public static interface DialogClickListener{
		void onClick(DialogInterface dialog);
	}
}
