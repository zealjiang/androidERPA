package com.jzg.erp.appraiser.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.jzg.erp.R;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.utils.BitmapUtils;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MTextUtils;
import com.jzg.erp.widget.crop.CropImageView;
import com.jzg.erp.widget.crop.FileUtilTool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CropImageActivity extends BaseActivity {
	private Bitmap bitmap = null;
	private int angle = 0;
	private String imgPath;
	@Override
	protected void initViews(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_image_crop);
		Intent uri1 = getIntent();
		angle = uri1.getIntExtra("Angle", 0);
		imgPath = uri1.getStringExtra("imgPath");
		cropImage1(uri1.getData());
	}

	@Override
	protected void setData() {

	}

	private void cropImage1(final Uri uri) {
		LogUtil.e(TAG,"uri:"+uri.toString());
		final CropImageView mCropImage = (CropImageView) findViewById(R.id.cropImg);
		if (bitmap != null) {
			bitmap.recycle();
			bitmap = null;
		}
		try {
			bitmap = BitmapFactory.decodeStream(getApplication().getContentResolver().openInputStream(uri));

			if (angle == 90) {
				bitmap = toRotationBitmap(bitmap, 90);
			} else if (angle == 180) {
				bitmap = toRotationBitmap(bitmap, 180);
			} else if (angle == 270) {
				bitmap = toRotationBitmap(bitmap, 270);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mCropImage.setDrawable((Drawable) new BitmapDrawable(bitmap), 720, 540);

		findViewById(R.id.save).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						String path = BitmapUtils.getImagePath(CropImageActivity.this,uri);
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS"); // 格式化时间
						String dataTake = format.format(date);
						FileUtilTool.deleteFile(imgPath);
						String savePath = FileUtilTool.SDCARD_PAHT+ FileUtilTool.APPROOT+"/photo_temp/" + dataTake+ MTextUtils.getName4Img(path);
						FileUtilTool.writeImage(mCropImage.getCropImage(),savePath, 100);
						Intent mIntent = new Intent();
						mIntent.putExtra("cropImagePath", savePath);
						setResult(RESULT_OK, mIntent);
						if (bitmap != null) {
							bitmap.recycle();
						}
						finish();

					}
				}).start();
			}
		});

	}

	/**
	 * 旋转图片 bitmap 原图 rotation 旋转角度
	 */
	private Bitmap toRotationBitmap(Bitmap bitmap, final int rotation) {
		if (rotation == 0)
			return bitmap;
		Matrix matrix = new Matrix();
		matrix.postRotate(rotation);
		int height = bitmap.getHeight();
		int width = bitmap.getWidth();
		float scale = 1f;
		// 防止内存溢出，先添加一个基本判断，根据测试情况更改
		while (height > 2200) {
			height /= 2;
			width /= 2;
			scale *= 0.5f;
		}
		matrix.postScale(scale, scale);
		int neHeight = height;
		int neWidth = width;
		if (rotation == 90 || rotation == 270) {
			neHeight = width;
			neWidth = height;
		}
		Bitmap canvasBitmap = Bitmap.createBitmap(neWidth, neHeight,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(canvasBitmap);
		float transX = height;
		float transY = 0;
		if (rotation == 90) {
			transX = height;
			transY = 0;
		} else if (rotation == 180) {
			transX = width;
			transY = height;
		} else if (rotation == 270) {
			transX = 0;
			transY = width;
		} else {
			transX = 0;
			transY = 0;
		}
		matrix.postTranslate(transX, transY);
		canvas.drawBitmap(bitmap, matrix, new Paint());
		if (bitmap != null) {
			bitmap.recycle();
			bitmap = null;
			System.gc();
		}
		return canvasBitmap;
	}

}
