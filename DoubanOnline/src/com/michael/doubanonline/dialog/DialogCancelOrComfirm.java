package com.michael.doubanonline.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.michael.doubanonline.R;


/**
 * 确认或取消对话框
 * 
 * */
public class DialogCancelOrComfirm implements OnClickListener
{
	
	public static final int BUTTON_CANCEL = 0;
	public static final int BUTTON_COMFIRM = 1;
	
	public DialogCancelOrComfirm(Context context)
	{
		initDialog(context);
	}

	private Dialog dialog;
	private TextView tvTitle;
	private Button btnCancel;
	private Button btnComfirm;

	private void initDialog(Context context)
	{
		LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
		View customDialog = layoutInflater.inflate(R.layout.layout_dialog_cancel_or_comfirm, new LinearLayout(context), false);
		tvTitle = (TextView) customDialog.findViewById(R.id.tvTitle);
		btnCancel = (Button) customDialog.findViewById(R.id.btnCancel);
		btnComfirm = (Button) customDialog.findViewById(R.id.btnComfirm);
		btnCancel.setOnClickListener(this);
		btnComfirm.setOnClickListener(this);
		dialog = new Dialog(context, R.style.custom_dialog);
		dialog.setContentView(customDialog);
	}

	public void showDialog(String title)
	{
		if (dialog != null)
		{
			tvTitle.setText(title);
			dialog.show();
		}
	}

	@Override
	public void onClick(View view)
	{
		int index = -1;
		switch (view.getId())
		{
		case R.id.btnCancel:
			index = BUTTON_CANCEL;
			break;
		case R.id.btnComfirm:
			index = BUTTON_COMFIRM;
			break;
		default:
			break;
		}
		if(onButtonClicked != null)
		{
			onButtonClicked.clicked(index);
		}
	}
	
	public void dismissDialog()
	{
		if (dialog != null && dialog.isShowing())
		{
			dialog.dismiss();
		}
	}

	private OnComfirmButtonClicked onButtonClicked;

	public void setOnComfirmButtonClicked(OnComfirmButtonClicked onButtonClicked)
	{
		this.onButtonClicked = onButtonClicked;
	}

	public interface OnComfirmButtonClicked
	{
		public void clicked(int buttonIndex);
	}

}
