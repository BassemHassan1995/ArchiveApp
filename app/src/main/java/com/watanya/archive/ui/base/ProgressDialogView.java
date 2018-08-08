package com.watanya.archive.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.watanya.archive.R;

public class ProgressDialogView
{
    private Context context;
	private ProgressDialog dialog;
	private DialogInterface.OnCancelListener onCancelListener;
	public ProgressDialogView(Context mContext)
	{
		this.context = mContext;
		dialog = new ProgressDialog(context);
	}
	
	public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener)
	{
		this.onCancelListener = onCancelListener;
		if(dialog != null) 
			dialog.setOnCancelListener(onCancelListener);
	}

	public void showProgressDialog(String msg)
	{
		try
		{
			dialog.setMessage(msg);
			dialog.setTitle(context.getResources().getString(R.string.loading));
			dialog.setIndeterminate(true);
			dialog.setCancelable(true);
			if (!dialog.isShowing()) dialog.show();

			if (onCancelListener != null)
				setOnCancelListener(onCancelListener);
			else {
				setOnCancelListener(new DialogInterface.OnCancelListener() {

					@Override
					public void onCancel(DialogInterface dialog) {
						Log.v("Dialog", "Canceled ");
					}
				});
			}
		}
		catch (Exception e)
		{
			Log.v("showProgressDialog", e + "");
		}
	}

	public void hideDialog()
	{
		if (dialog != null)
		{
			try
			{
				dialog.dismiss();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				setDialogCancelable(true);
			}
		}
	}

	public boolean isShowing()
	{
		return dialog.isShowing();
	}
	public void setCanceledOnTouchOutside(boolean cancel) {
		dialog.setCanceledOnTouchOutside(cancel);
	}

	public void setDialogCancelable(boolean isCancelable)
	{
		if (dialog != null)
		{
			try
			{
				dialog.setCancelable(isCancelable);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}