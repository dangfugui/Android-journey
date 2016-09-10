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
	public static String index="journey/首页/index.html";
	
	public MyURL() {
		// TODO 自动生成的构造函数存根
	}

	/**
	 * 返回获取推荐的url
	 * @param start 开始序号	
	 * @param end	结束序号	
	 * @param title 标题
	 * @return url
	 */
	public String getScenics(String title){
		//return "http://10.0.3.2:80/journey/Service/getScenics.php?start=1&end=5";
		return localhost+"journey/Service/getMessage.php?type="+title;
	}
	
	/**
	 * 获取好友列表url
	 * @param userid 用户id
	 * @return 获取好友列表url
	 */
	public String getFriends(){
		//http://localhost/journey/Service/getFriends.php?userid=2
		return localhost+"journey/Service/getFriends.php?userid="+Data.USER.userid;
	}
	/**
	 * 获取某好友日志url
	 * @param userid 好友id
	 * @return 获取某好友日志url
	 */
	public String getArticles(String userid){
		//http://localhost/journey/Service/getArticles.php?userid=2
		return localhost+"journey/Service/getArticles.php?userid="+userid;
	}
	/**
	 * 获取我的日志
	 * @return 获取我的日志url
	 */
	public String getmyArticles() {
		// TODO 自动生成的方法存根
		return localhost+"journey/Service/getArticles.php?userid="+Data.USER.userid;
	}
	
	/******************************************************
	 * 获取我好友的全部日志
	 * @return 获取我好友的全部日志url
	 */
	public String getFriendsArticles() {
		// TODO 自动生成的方法存根
		return localhost+"journey/Service/getAllArticles.php?userid="+Data.USER.userid;
	}
	
	/**
	 * 设置网络图片
	 * @param context	上下文
	 * @param imageview		imageview控件
	 * @param url			图片地址
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
	 * 获取所有用户
	 * @return url
	 */
	public String getAddFriends() {
		// TODO 自动生成的方法存根
		return localhost+"journey/Service/getAllUsers.php";
	}
	/**
	 * 添加好友
	 * @param id好友id
	 * @return 反馈信息
	 */
	public String addFriend(String id) {
		// TODO 自动生成的方法存根
		return localhost+"journey/Service/addFriend.php?userid="+Data.USER.userid+"&friendid="+id;
	}
	
 
	/**上传文件至Server的方法 
	 *  * @param filePath	本地文件路径
	 * @param fileName  报文中的文件名参数
	 * @return 上传结果
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
          /* 设置请求属性 */
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
          /*设置StrictMode 否则HTTPURLConnection连接失败，因为这是在主进程中进行网络连接*/
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
          /* 设置DataOutputStream，getOutputStream中默认调用connect()*/
            DataOutputStream ds = new DataOutputStream(con.getOutputStream());  //output to the connection
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; " +
                    "name=\"file\";filename=\"" +
                    fileName + "\"" + end);
            ds.writeBytes(end);
          /* 取得文件的FileInputStream */
            FileInputStream fStream = new FileInputStream(filePath);
          /* 设置每次写入8192bytes */
            int bufferSize = 8192;
            byte[] buffer = new byte[bufferSize];   //8k
            int length = -1;
          /* 从文件读取数据至缓冲区 */
            while ((length = fStream.read(buffer)) != -1)
            {
            /* 将资料写入DataOutputStream中 */
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
          /* 关闭流，写入的东西自动生成Http正文*/
            fStream.close();
          /* 关闭DataOutputStream */
            ds.close();
          /* 从返回的输入流读取响应信息 */
            InputStream is = con.getInputStream();  //input from the connection 正式建立HTTP连接
            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1)
            {
                b.append((char) ch);
            }
          /* 显示网页响应内容 */
            result="PostSucceed";//Post成功
        } catch (Exception e)
        {
            /* 显示异常信息 */
        	 result="PostDefeated";//Post失败
        }
        return result;
    }
	/**
	 * 
	 * @param header	标题	
	 * @param contents	内容
	 * @param urlPath 图片url
	 * @return
	 */
	public String addArticle(Context context,String header,String contents,ArrayList<File> files) {
		// TODO 自动生成的方法存根
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
