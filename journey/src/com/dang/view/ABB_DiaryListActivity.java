package com.dang.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;











import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.serve.html.Article_info;
import com.serve.html.HtmlFriends;
import com.serve.html.HttpGetThread;
import com.serve.html.MyJson;
import com.serve.html.ThreadPoolUtils;
import com.xiang.lvyou.ABCA_PhotoUtilsActivity;
import com.xiang.lvyou.R;
import com.xiang.lvyou.adapter.AB_ArticleAdapter;
import com.xiang.lvyou.url.Data;
import com.xiang.lvyou.url.MyURL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ABB_DiaryListActivity extends Activity{

	private TextView txt_title;
	private ImageView img_back;
	private ImageView img_add;
	private PullToRefreshListView listview;
	private AB_ArticleAdapter adapter;
	private List<Article_info> list;
	private String getURL=new MyURL().getmyArticles();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aaa_activity_recommend);
		initview();
	}

	private void initview() {
		// TODO 自动生成的方法存根
		txt_title=(TextView) findViewById(R.id.aaa_txt_title);
		img_back=(ImageView) findViewById(R.id.aaa_img_back);
		img_add=(ImageView) findViewById(R.id.aaa_img_add);
		listview=(PullToRefreshListView) findViewById(R.id.aaa_listview);
		Intent intent=this.getIntent();
		String title=intent.getStringExtra("title");
		String userid=(String) intent.getStringExtra("userid");
		if(title!=null){
			txt_title.setText(title);
		}
		if(userid!=null){
			getURL=new MyURL().getArticles(userid);
		}
		MyOnclickListener mylistener= new MyOnclickListener();
		img_back.setOnClickListener(mylistener);
		img_add.setOnClickListener(mylistener);
		addlist();
	}
	
	private void addlist() {
		list = new ArrayList<Article_info>();
		adapter = new AB_ArticleAdapter(this, list);
		listview.setAdapter(adapter);
		
		ThreadPoolUtils.execute(new HttpGetThread(hand,
				getURL));
		//lisView的刷新部分
				listview.setOnRefreshListener(new OnRefreshListener<ListView>() {
					@Override
					public void onRefresh(PullToRefreshBase<ListView> refreshView) {
						new AsyncTask<Void, Void, Void>() {
							@Override
							protected Void doInBackground(Void... arg0) {
								try {
									Thread.sleep(3000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								list.clear();
								ThreadPoolUtils.execute(new HttpGetThread(hand,
										getURL));
								return null;
							}
							@SuppressLint("NewApi")
							protected void onPostExecute(Void result) {
								listview.onRefreshComplete();
							};
						}.execute();
					}
				});
				listview.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO 自动生成的方法存根
						Intent intent=new Intent(getApplicationContext(),ABI_DiaryItem.class);
						//Toast.makeText(getApplicationContext(), list.get(position-1).articleid+ list.get(position-1).imageList.length, 1).show();
						Data.article=list.get(position-1);
						startActivity(intent);
					}
				});
	}
	
	Handler hand = new Handler(){
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(getApplicationContext(), "找不到地址", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(getApplicationContext(), "传输失败", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				// 在activity当中获取网络交互的数据
				if (result != null) {
					// 1次网络请求返回的数据
					List<Article_info> ListInfo = new MyJson().getArticleInfo(result);
					if (ListInfo!= null) {
						for (Article_info info:ListInfo){
							list.add(info);
						}
						adapter.notifyDataSetChanged();
					}
				}
				adapter.notifyDataSetChanged();
			}
		};
	};


	private class MyOnclickListener implements View.OnClickListener {
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.aaa_img_back:
				finish();
				break;
			case R.id.aaa_img_add:
				Intent intent=new Intent(ABB_DiaryListActivity.this,ABCA_PhotoUtilsActivity.class);
				startActivity(intent);
				break;
			default:
				break;
			}
		}
	}
}

