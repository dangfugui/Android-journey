package com.xiang.lvyou.url;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.serve.html.Friend_info;
import com.serve.html.LoadImg;
import com.serve.html.LoadImg.ImageDownloadCallBack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.Toast;



public class MyURL {
	//public static String localhost="http://10.0.3.2:80/";
	public static String localhost="http://qihe.party/phproot/";
	public static String imagePath="journey/image/";
	public static String index="journey/��ҳ/index.html";
	
	public MyURL() {
		// TODO �Զ����ɵĹ��캯�����
	}

	/**
	 * ���ػ�ȡ�Ƽ���url
	 * @param start ��ʼ���	
	 * @param end	�������	
	 * @param title ����
	 * @return url
	 */
	public String getScenics(String title){
		//return "http://10.0.3.2:80/journey/Service/getScenics.php?start=1&end=5";
		return localhost+"journey/Service/getMessage.php?type="+title;
	}
	
	/**
	 * ��ȡ�����б�url
	 * @param userid �û�id
	 * @return ��ȡ�����б�url
	 */
	public String getFriends(){
		//http://localhost/journey/Service/getFriends.php?userid=2
		return localhost+"journey/Service/getFriends.php?userid="+Data.USER.userid;
	}
	/**
	 * ��ȡĳ������־url
	 * @param userid ����id
	 * @return ��ȡĳ������־url
	 */
	public String getArticles(String userid){
		//http://localhost/journey/Service/getArticles.php?userid=2
		return localhost+"journey/Service/getArticles.php?userid="+userid;
	}
	/**
	 * ��ȡ�ҵ���־
	 * @return ��ȡ�ҵ���־url
	 */
	public String getmyArticles() {
		// TODO �Զ����ɵķ������
		return localhost+"journey/Service/getArticles.php?userid="+Data.USER.userid;
	}
	
	/******************************************************
	 * ��ȡ�Һ��ѵ�ȫ����־
	 * @return ��ȡ�Һ��ѵ�ȫ����־url
	 */
	public String getFriendsArticles() {
		// TODO �Զ����ɵķ������
		return localhost+"journey/Service/getAllArticles.php?userid="+Data.USER.userid;
	}
	
	/**
	 * ��������ͼƬ
	 * @param context	������
	 * @param imageview		imageview�ؼ�
	 * @param url			ͼƬ��ַ
	 */
	public void setimage(Context context,final ImageView imageview, String url) {
		 LoadImg loadImg = new LoadImg(context);
		 Bitmap bit = loadImg.loadImage(imageview,localhost+url,
				new ImageDownloadCallBack() {
					@Override
					public void onImageDownload(ImageView imageView,Bitmap bitmap) {
						if(bitmap!=null){
							imageview.setImageBitmap(bitmap);
						}
					}
				});
	}

	/**
	 * ��ȡ�����û�
	 * @return url
	 */
	public String getAddFriends() {
		// TODO �Զ����ɵķ������
		return localhost+"journey/Service/getAllUsers.php";
	}
	/**
	 * ��Ӻ���
	 * @param id����id
	 * @return ������Ϣ
	 */
	public String addFriend(String id) {
		// TODO �Զ����ɵķ������
		return localhost+"journey/Service/addFriend.php?userid="+Data.USER.userid+"&friendid="+id;
	}
	
 
	/**�ϴ��ļ���Server�ķ��� 
	 *  * @param filePath	�����ļ�·��
	 * @param fileName  �����е��ļ�������
	 * @return �ϴ����
	 */
	@SuppressLint("NewApi")
	public String uploadFile(String filePath,String fileName){
		String result="ok";
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        try
        {
        	java.net.URL url = new java.net.URL(localhost+"journey/Service/upload.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
          /* Output to the connection. Default is false,
             set to true because post method must write something to the connection */
            con.setDoOutput(true);
          /* Read from the connection. Default is true.*/
            con.setDoInput(true);
          /* Post cannot use caches */
            con.setUseCaches(false);
          /* Set the post method. Default is GET*/
            con.setRequestMethod("POST");
          /* ������������ */
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
          /*����StrictMode ����HTTPURLConnection����ʧ�ܣ���Ϊ�������������н�����������*/
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
          /* ����DataOutputStream��getOutputStream��Ĭ�ϵ���connect()*/
            DataOutputStream ds = new DataOutputStream(con.getOutputStream());  //output to the connection
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; " +
                    "name=\"file\";filename=\"" +
                    fileName + "\"" + end);
            ds.writeBytes(end);
          /* ȡ���ļ���FileInputStream */
            FileInputStream fStream = new FileInputStream(filePath);
          /* ����ÿ��д��8192bytes */
            int bufferSize = 8192;
            byte[] buffer = new byte[bufferSize];   //8k
            int length = -1;
          /* ���ļ���ȡ������������ */
            while ((length = fStream.read(buffer)) != -1)
            {
            /* ������д��DataOutputStream�� */
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
          /* �ر�����д��Ķ����Զ�����Http����*/
            fStream.close();
          /* �ر�DataOutputStream */
            ds.close();
          /* �ӷ��ص���������ȡ��Ӧ��Ϣ */
            InputStream is = con.getInputStream();  //input from the connection ��ʽ����HTTP����
            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1)
            {
                b.append((char) ch);
            }
          /* ��ʾ��ҳ��Ӧ���� */
            result="PostSucceed";//Post�ɹ�
        } catch (Exception e)
        {
            /* ��ʾ�쳣��Ϣ */
        	 result="PostDefeated";//Postʧ��
        }
        return result;
    }
	/**
	 * 
	 * @param header	����	
	 * @param contents	����
	 * @param urlPath ͼƬurl
	 * @return
	 */
	public String addArticle(Context context,String header,String contents,ArrayList<File> files) {
		// TODO �Զ����ɵķ������
		String image="";
		if(files.size()>0){
			image=imagePath+Data.USER.username+"-a-"+files.get(0).getName()+",";
			for(int i=1;i<files.size()-1;i++){
				image+=imagePath+Data.USER.username+"-a-"+files.get(i).getName()+",";
			}
			image+=imagePath+Data.USER.username+"-a-"+files.get(files.size()-1).getName();
			Toast.makeText(context, image, 1).show();
		}
		return localhost+"journey/Service/addArticle.php?userid=+"+Data.USER.userid+
				"&articlemode=2&contents="+contents+"&image="+image+"&header="+header;
	}
	
	
	
}
