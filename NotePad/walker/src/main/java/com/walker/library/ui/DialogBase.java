package com.walker.library.ui;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.walker.library.R;

public class DialogBase extends Dialog
{

	private boolean isAutoDismiss = true;
	private boolean isCanceledOnTouchOutside = false;

	private Button mBtnConfirm;
	private Button mBtnCancel;

	private View lineSpilt;
	public Activity mContext;

	protected OnButtonClickListener confirmListener;
	protected OnButtonClickListener cancelListener;

	public DialogBase(Activity context) {
		this(context, R.style.no_background_dialog);
	}

	public DialogBase(Activity context, int theme) {
		super(context, theme);
		this.mContext = context;
		setContentView(R.layout.layout_custom_dialog);
		WindowManager m = getWindow().getWindowManager();
		Display d = m.getDefaultDisplay();
		LayoutParams p = getWindow().getAttributes();
		p.width = (int) (d.getWidth() * 0.8);
		getWindow().setAttributes(p);
		setCanceledOnTouchOutside(true);

		mBtnConfirm = (Button) findViewById(R.id.btn_confirm);
		mBtnCancel = (Button) findViewById(R.id.btn_cancle);
		lineSpilt = findViewById(R.id.view_dialog_split);
	}

	@Override
	public void setCanceledOnTouchOutside(boolean cancel) {
		super.setCanceledOnTouchOutside(cancel);
		isCanceledOnTouchOutside = cancel;
		View v1 = findViewById(R.id.dialog_free_1);
		View v2 = findViewById(R.id.dialog_free_2);

		if (null != v1 && null != v2) {
			v1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (isAutoDismiss && isCanceledOnTouchOutside && DialogBase.this.isShowing()) {
						DialogBase.this.dismiss();
					}
				}
			});
			v2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (isAutoDismiss && isCanceledOnTouchOutside && DialogBase.this.isShowing()) {
						DialogBase.this.dismiss();
					}
				}
			});
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
	};
	/**
	 * @param btnTxt
	 * @param onClicklistener
	 */
	public DialogBase defSetConfirmBtn(String btnTxt,
			OnButtonClickListener onClicklistener) {
		confirmListener = onClicklistener;
		findViewById(R.id.view_line).setVisibility(View.VISIBLE);
		findViewById(R.id.layout_btn_panel).setVisibility(View.VISIBLE);
		if (mBtnCancel.getVisibility() == View.VISIBLE) {
			lineSpilt.setVisibility(View.VISIBLE);
		}

		mBtnConfirm.setVisibility(View.VISIBLE);
		mBtnConfirm.setText(btnTxt);
		mBtnConfirm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				confirmBtnOpt();
				if (isAutoDismiss && DialogBase.this.isShowing()) {
					DialogBase.this.dismiss();
				}
			}

		});
		return this;
	}

	/**
	 *
	 */
	protected void confirmBtnOpt() {
		if (null != confirmListener) {
			confirmListener.onClick(DialogBase.this);
		}
	}

	public DialogBase defSetIsAutoDismiss(boolean isAutoDismiss) {
		this.isAutoDismiss = isAutoDismiss;
		return this;
	}

	/**
	 * @param btnTxt
	 * @param onClicklistener
	 */
	public DialogBase defSetCancelBtn(String btnTxt,
			OnButtonClickListener onClicklistener) {
		cancelListener = onClicklistener;
		findViewById(R.id.view_line).setVisibility(View.VISIBLE);
		findViewById(R.id.layout_btn_panel).setVisibility(View.VISIBLE);
		if (mBtnConfirm.getVisibility() == View.VISIBLE) {
			lineSpilt.setVisibility(View.VISIBLE);
		}
		mBtnCancel.setVisibility(View.VISIBLE);
		mBtnCancel.setText(btnTxt);
		mBtnCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				cancelOpt();
				if (isAutoDismiss && DialogBase.this.isShowing()) {
					DialogBase.this.dismiss();
				}
			}

		});
		return this;
	}


	protected void cancelOpt() {
		if (null != cancelListener) {
			cancelListener.onClick(DialogBase.this);
		}
	}
	/**
	 *
	 * @param v
	 */
	public DialogBase defSetCenterContentView(View v) {
		FrameLayout layout = (FrameLayout) findViewById(R.id.layout_content);
		layout.setVisibility(View.VISIBLE);
		layout.addView(v);
		return this;
	}

	public DialogBase defSetContentTxt(String str) {
		TextView tv = (TextView) findViewById(R.id.tv_dialog_content);
		tv.setVisibility(View.VISIBLE);
		tv.setText(Html.fromHtml(str));
		return this;
	}
	public DialogBase defSetContentTxt(String str, boolean isUseHtmlFormat) {
		TextView tv = (TextView) findViewById(R.id.tv_dialog_content);
		tv.setVisibility(View.VISIBLE);
		if(isUseHtmlFormat){
			tv.setText(Html.fromHtml(str));
		}else {
			tv.setText(str.replace("\\n", "\n"));
		}
		return this;
	}

	/**v@see android.view.Gravity
	 * @param str
	 * @param Gravity
	 * @return
	 */
	public DialogBase defSetContentTxt(String str, int Gravity  ) {
		TextView tv = (TextView) findViewById(R.id.tv_dialog_content);
		tv.setVisibility(View.VISIBLE);
		tv.setText(str.replace("\\n", "\n"));
		tv.setGravity(Gravity);
		return this;
	}

	public DialogBase setContentGravity(int gravity)
	{
		TextView tv = (TextView) findViewById(R.id.tv_dialog_content);
		tv.setGravity(gravity);
		return this;
	}

	public DialogBase defSetTitleTxt(CharSequence str) {
		findViewById(R.id.ll_title).setVisibility(View.VISIBLE);
		TextView tv = (TextView) findViewById(R.id.tv_title);
		tv.setText(str);
		return this;
	}

	public interface OnButtonClickListener {
		/**
		 * @param dialog
		 * @param which
		 */
		public void onClick(DialogBase dialog);
	}
}
