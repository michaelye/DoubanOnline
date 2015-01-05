package com.michael.doubanonline.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.michael.doubanonline.R;

/**
 * 用于主界面的欢迎对话框
 * 
 * */
public class DialogUtil
{

	/**
	 * 显示一个AlertDialog，默认触摸外面不自动关闭
	 * 
	 * */
	public static void show(Context context, String title, String message, String buttonText, DialogInterface.OnClickListener listener)
	{

		show(context, title, message, buttonText, listener, true);
	}

	/**
	 * 显示一个AlertDialog
	 * 
	 * 如果不想要有按钮，buttonText和listener设置为null即可
	 * 
	 * */
	public static void show(Context context, String title, String message, String buttonText, DialogInterface.OnClickListener listener, boolean cancelAble)
	{

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		AlertDialog dialog = builder.create();
		dialog.setTitle(title);
		dialog.setMessage(message);
		// dialog.setIcon(R.drawable.ic_launcher);
		if (buttonText != null && listener != null)
		{

			dialog.setButton(DialogInterface.BUTTON_POSITIVE, buttonText, listener);
		}
		dialog.show();
		if (cancelAble)
		{

			dialog.setCanceledOnTouchOutside(false);// 触摸对话框外面不会消失
		}
	}

	/**
	 * 显示一个类似于Android4.0效果的对话框
	 * 
	 * */
	public static void show(Context context, String title, String message)
	{

		LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
		View customDialog = layoutInflater.inflate(R.layout.layout_dialog_online_desc, new LinearLayout(context), false);

		TextView tvTitle = (TextView) customDialog.findViewById(R.id.tv_title);
		TextView tvContent = (TextView) customDialog.findViewById(R.id.tv_content);
		tvContent.setMovementMethod(new ScrollingMovementMethod());
		tvTitle.setText(title);
		tvContent.setText(message);

		new AlertDialog.Builder(context).setView(customDialog).create().show();
	}

}
