package com.xiang.lvyou.login;

import java.io.File;
import java.net.URI;
import java.util.List;

import com.serve.html.Friend_info;
import com.serve.html.HttpPostThread;
import com.serve.html.MyJson;
import com.serve.html.ThreadPoolUtils;
import com.xiang.lvyou.R;
import com.xiang.lvyou.url.Data;
import com.xiang.lvyou.url.DataTool;
import com.xiang.lvyou.url.MyURL;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ADC_UserSetActivity extends Activity {
	private final String IMAGE_TYPE = "image/*";
	private final int IMAGE_CODE = 0;   //�����IMAGE_CODE���Լ����ⶨ���
	private File fileHeader;
	private String filePath;
	private ImageView img_back;
	private EditText edt_name;
	private EditText edt_introduce;
	private EditText edt_phone;
	private EditText edt_country;
	private Button btn_ok;
	private ImageView img_header;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adc_userset_activity);
		initview();
	}
	private void initview() {
		img_back=(ImageView) findViewById(R.id.adc_img_back);
		img_header=(ImageView) findViewById(R.id.adc_img_header);
		
		btn_ok=(Button) findViewById(R.id.adc_btn_ok);
		edt_name=(EditText) findViewById(R.id.adc_edt_name);
		edt_introduce=(EditText) findViewById(R.id.adc_edt_introduce);
		edt_phone=(EditText) findViewById(R.id.adc_edt_phone);
		edt_country=(EditText) findViewById(R.id.adc_edt_country);
		btn_ok=(Button) findViewById(R.id.adc_btn_ok);
		btn_ok.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				userset();
			}
		});
		img_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();	
			}
		});
		img_header.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				getimage();
			}
		});
		
		setdata();
	}
	

	private void setdata() {
		// TODO �Զ����ɵķ������

		new MyURL().setimage(getApplicationContext(), img_header,Data.USER.userheader);
		edt_name.setText(Data.USER.name);
		edt_introduce.setText(Data.USER.introduce);
		edt_phone.setText(Data.USER.phone);
		edt_country.setText(Data.USER.country);
		
		
	}
	private void userset() {
		String fileName = null;
		String userheader=Data.USER.userheader;
			if(fileHeader!=null){
				fileName=Data.USER.username+"-+-"+fileHeader.getName();
				userheader=MyURL.imagePath+fileName;
			}
			new MyURL().uploadFile(filePath, fileName);
			String url = Model.upDataUser;
			String userid=new DataTool(getApplicationContext()).getUser().userid;
			String name=edt_name.getText().toString();
			String phone=edt_phone.getText().toString();
			String country=edt_country.getText().toString();
			String introduce=edt_introduce.getText().toString();
			
			String value = "{          \"userid\":\"" + userid
					+ "\",             \"name\":\"" + name
					+ "\",             \"phone\":\"" + phone
					+ "\",             \"country\":\"" + country
					+ "\",             \"introduce\":\"" + introduce
					+ "\",             \"userheader\":\"" + userheader
					+ "\"}";
			//
			ThreadPoolUtils.execute(new HttpPostThread(hand, url, value));
		

		
	}
	Handler hand = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(getApplicationContext(), "����ʧ�ܣ�����������", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(getApplicationContext(), "����������Ӧ", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				List<Friend_info> users=new MyJson().getFriendInfo(result);
				Friend_info user=users.get(0);
				new DataTool(getApplicationContext()).saveUser(user);
			}
			Toast.makeText(getApplicationContext(), "set succeed", 1).show();
		};
	};
	
	 private void getimage() {
			// TODO �Զ����ɵķ������
			
			//ʹ��intent����ϵͳ�ṩ����Ṧ�ܣ�ʹ��startActivityForResult��Ϊ�˻�ȡ�û�ѡ���ͼƬ
			Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
			getAlbum.setType(IMAGE_TYPE);
			startActivityForResult(getAlbum, IMAGE_CODE);
		}
		
		

		//��дonActivityResult�Ի������Ҫ����Ϣ


	 protected void onActivityResult(int requestCode, int resultCode, Intent data){

		    if (resultCode != RESULT_OK) {        //�˴��� RESULT_OK ��ϵͳ�Զ����һ������ 
		        return;
		    }
		    //���ĳ������ContentProvider���ṩ���� ����ͨ��ContentResolver�ӿ�
		    ContentResolver resolver = getContentResolver();
		    //�˴��������жϽ��յ�Activity�ǲ�������Ҫ���Ǹ�
		    if (requestCode == IMAGE_CODE) {
		        Uri originalUri = data.getData(); //���ͼƬ��uri
		        if(originalUri.toString().indexOf("tent:")>1){
		        filePath=getRealPathFromURI(originalUri);
		        
		     }else{
		    	  filePath=originalUri.toString();
		     }
		        fileHeader=new File(filePath);
		        String str=fileHeader.toString();
		        int start=0;
		        if(str.indexOf(":")>1){
		        	start=str.indexOf(":")+1;
		        }
		        filePath=str.substring(start, str.length());
		        Toast.makeText(getApplicationContext(), filePath, 1).show();
		        //���ñ���ͼƬ
                Bitmap bm = BitmapFactory.decodeFile(filePath);
                img_header.setImageBitmap(bm);  
                
		    }

	 }
	 public String getRealPathFromURI( Uri contentUri) {
		   String[] proj = { MediaStore.Images.Media.DATA };
		   Cursor cursor = managedQuery(contentUri, proj, // Which columns to return
		     null, // WHERE clause; which rows to return (all rows)
		     null, // WHERE clause selection arguments (none)
		     null); // Order-by clause (ascending by name)
		   int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		   cursor.moveToFirst();
		   return cursor.getString(column_index);
		  } 
		 

}
