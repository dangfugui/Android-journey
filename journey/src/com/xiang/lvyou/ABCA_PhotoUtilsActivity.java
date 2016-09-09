package com.xiang.lvyou;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.serve.html.Friend_info;
import com.serve.html.HttpGetThread;
import com.serve.html.MyJson;
import com.serve.html.ThreadPoolUtils;
import com.xiang.lvyou.url.Data;
import com.xiang.lvyou.url.DataTool;
import com.xiang.lvyou.url.MyURL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author zhiwei.zhao
 * @project PhotoUtils
 * @created: 2013-3-14 ����5:13:00
 * @Description ���ա���ϵͳ���ѡ���ϴ�
 */
public class ABCA_PhotoUtilsActivity extends Activity {
	private static final int MAX_COUNT = 100;
	private Button mback;
	@SuppressWarnings("unused")
	private Button mcomit;
	private ImageButton mcamera;
	@SuppressWarnings("unused")
	private TextView mtitle;
	private EditText edt_headline;
	private EditText edt_content;
	



	private Bitmap bitmap;

	private LinearLayout ll_imgs;

	// private String fileName = "justin.txt"; //�����е��ļ�������
	// private String path =
	// Environment.getExternalStorageDirectory().getPath(); //Don't use
	// "/sdcard/" here
	// private String uploadFile = path + "/" + fileName; //���ϴ����ļ�·��
	//private String postUrl = MyURL.localhost+MyURL.imagePath; // ����POST�����ҳ��
	ArrayList<String> urlPath = new ArrayList<String>();
	private ArrayList<File> files = new ArrayList<File>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sos);
		ll_imgs = (LinearLayout) findViewById(R.id.ll_imgs);

		mback = (Button) findViewById(R.id.back);
		mcomit = (Button) findViewById(R.id.comit);
		mtitle = (TextView) findViewById(R.id.title);
		edt_headline=(EditText) findViewById(R.id.abca_headline);
		edt_content=(EditText) findViewById(R.id.abca_content);


		

		mcamera = (ImageButton) findViewById(R.id.camera);


		mback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ABCA_PhotoUtilsActivity.this.finish();
			}
		});

		mcamera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showCustomAlertDialog();
			}

		});

		mcomit.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unused")
			@Override
			public void onClick(View v) {
				upload();
			}

		});

	}
////�ϴ�/////////////////////////////////////////////
	private void upload() {
		// TODO �Զ����ɵķ������
		for (int i = 0; i <files.size(); i++) {
			/*String filepath=urlPath.get(i);
			String filename=filepath.substring(filepath.lastIndexOf("/"), filepath.length());*/
			new MyURL().uploadFile(files.get(i).getPath(), 
					Data.USER.username+"-a-"+files.get(i).getName());
		}
		String header=edt_headline.getText().toString();
		String contents=edt_content.getText().toString();
		ThreadPoolUtils.execute(new HttpGetThread(hand,
				new MyURL().addArticle(getApplicationContext(),header, contents, files)));

		
	}
	
	Handler hand = new Handler(){
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(getApplicationContext(), "�Ҳ�����ַ", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(getApplicationContext(), "����ʧ��", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				// ��activity���л�ȡ���罻��������
				edt_content.setText(result);
				if (result != null) {
					Toast.makeText(getApplicationContext(), result, 1).show();
				}
			
			}
		};
	};

	private void showCustomAlertDialog() {
		final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.show();
		Window win = alertDialog.getWindow();

		WindowManager.LayoutParams lp = win.getAttributes();
		win.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		lp.alpha = 0.7f;
		win.setAttributes(lp);
		win.setContentView(R.layout.dialog);

		Button cancelBtn = (Button) win.findViewById(R.id.camera_cancel);
		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.cancel();
			}
		});
		Button camera_phone = (Button) win.findViewById(R.id.camera_phone);
		camera_phone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				systemPhoto();
			}

		});
		Button camera_camera = (Button) win.findViewById(R.id.camera_camera);
		camera_camera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cameraPhoto();
			}

		});

	}

	private final int SYS_INTENT_REQUEST = 0XFF01;
	private final int CAMERA_INTENT_REQUEST = 0XFF02;

	/**
	 * ��ϵͳ���
	 */
	private void systemPhoto() {

		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, SYS_INTENT_REQUEST);

	}

	/**
	 * �����������
	 */
	private void cameraPhoto() {
		String sdStatus = Environment.getExternalStorageState();
		/* ���sd�Ƿ���� */
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "SD�������ã�", Toast.LENGTH_SHORT).show();
			return;
		}
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		startActivityForResult(intent, CAMERA_INTENT_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == SYS_INTENT_REQUEST && resultCode == RESULT_OK
				&& data != null) {
			try {
				Uri uri = data.getData();
				Cursor cursor = getContentResolver().query(uri, null, null,
						null, null);
				cursor.moveToFirst();

				String imageFilePath = cursor.getString(1);

				Log.e("lushun", imageFilePath);
				System.out.println("File path is----->" + imageFilePath);

				FileInputStream fis = new FileInputStream(imageFilePath);
				bitmap = BitmapFactory.decodeStream(fis);

				int width = bitmap.getWidth()*3;
				int height = bitmap.getHeight()*3;
				System.out.println("ѹ��ǰ�Ŀ��----> width: " + width + " height:"
						+ height);

				/* ѹ����ȡ��ͼ�� */
				showImgs(bitmap, false);
				File f=new File(imageFilePath);
				files.add(f);
				urlPath.add(imageFilePath);

				fis.close();
				cursor.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (requestCode == CAMERA_INTENT_REQUEST
				&& resultCode == RESULT_OK && data != null) {
			cameraCamera(data);
		}
		super.onActivityResult(requestCode, resultCode, data);

	}

	/**
	 * @param bitmap
	 * @return ѹ�����bitmap
	 */
	private Bitmap compressionBigBitmap(Bitmap bitmap, boolean isSysUp) {
		Bitmap destBitmap = null;
		/* ͼƬ��ȵ���Ϊ100��������������ģ���һ���������ŵ����Ϊ100 */
		if (bitmap.getWidth() > 80) {
			float scaleValue = (float) (80f / bitmap.getWidth());
			System.out.println("���ű���---->" + scaleValue);

			Matrix matrix = new Matrix();
			/* ���ϵͳ���գ���ת90�� */
			if (isSysUp)
				matrix.setRotate(90);
			matrix.postScale(scaleValue, scaleValue);

			destBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);
			int widthTemp = destBitmap.getWidth();
			int heightTemp = destBitmap.getHeight();
			Log.i("zhiwei.zhao", "ѹ����Ŀ��----> width: " + heightTemp
					+ " height:" + widthTemp);
		} else {
			return bitmap;
		}
		return destBitmap;

	}

	/**
	 * @param data
	 *            ���պ��ȡ��Ƭ
	 */
	private void cameraCamera(Intent data) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String name = formatter.format(System.currentTimeMillis()) + ".jpg";
		Log.i("zhiwei.zhao", "image name:" + name);
		Toast.makeText(this, name, Toast.LENGTH_LONG).show();
		Bundle bundle = data.getExtras();
		/* ��ȡ������ص����ݣ���ת��ΪBitmapͼƬ��ʽ */
		Bitmap bitmap = (Bitmap) bundle.get("data");
		FileOutputStream b = null;

		String path = Environment.getExternalStorageDirectory().getPath();
		File file = new File(path + "/myImage/");
		/** ����ļ����Ƿ���ڣ��������򴴽��ļ��� **/
		if (!file.exists() && !file.isDirectory())
			file.mkdirs();
		String fileName = file.getPath() + "/" + name;
		Log.i("zhiwei.zhao", "camera file path:" + fileName);
		try {
			b = new FileOutputStream(fileName);
			/* ������д���ļ� */
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (b == null)
					return;
				b.flush();
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		showImgs(bitmap, true);
		File f=new File(fileName);
		files.add(f);
		urlPath.add(fileName);

	}

	/**
	 * չʾѡ���ͼƬ
	 * 
	 * @param bitmap
	 * @param isSysUp
	 */
	private void showImgs(Bitmap bitmap, boolean isSysUp) {
		if (ll_imgs.getChildCount() > 10) {
			Toast.makeText(this, "����ϴ�����ͼƬ���ɵ��ɾ����ѡ���ͼƬ��", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		Bitmap _bitmap = compressionBigBitmap(bitmap, isSysUp);
		final ImageView im = new ImageView(this);
		im.setPadding(50, 0, 0, 0);
		im.setImageBitmap(_bitmap);
		ll_imgs.addView(im);

		//
		// im.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// BitmapDrawable bitmapDrawable = (BitmapDrawable) im
		// .getDrawable();
		// if (bitmapDrawable != null
		// && !bitmapDrawable.getBitmap().isRecycled()) {
		// bitmapDrawable.getBitmap().recycle();
		// }
		// ll_imgs.removeView(im);
		// }
		// });
	}

	

	private long calculateLength(CharSequence c) {
		double len = 0;
		for (int i = 0; i < c.length(); i++) {
			int tmp = (int) c.charAt(i);
			if (tmp > 0 && tmp < 127) {
				len += 0.5;
			} else {
				len++;
			}
		}
		return Math.round(len);
	}

	
	
	/* �ϴ��ļ���Server�ķ��� */
	
	

}
