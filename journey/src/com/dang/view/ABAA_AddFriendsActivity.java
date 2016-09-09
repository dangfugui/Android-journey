package com.dang.view;


import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import org.jivesoftware.smack.packet.Privacy;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.serve.html.Friend_info;
import com.serve.html.HttpGetThread;
import com.serve.html.MyJson;
import com.serve.html.ThreadPoolUtils;
import com.xiang.lvyou.R;
import com.xiang.lvyou.adapter.ABA_FriendsListAdapter;
import com.xiang.lvyou.url.MyURL;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;



public class ABAA_AddFriendsActivity extends Activity{
	private ImageView img_back;
	private PullToRefreshListView listview;
	private ABA_FriendsListAdapter adapter;
	private List<Friend_info> list;
	private String geturl=new MyURL().getAddFriends();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.abaa_addfriends);
		initview();
	}

	private void initview() {
		// TODO 自动生成的方法存根
		img_back=(ImageView) findViewById(R.id.abaa_img_back);
		listview=(PullToRefreshListView) findViewById(R.id.abaa_listview);
		MyOnclickListener mylistener= new MyOnclickListener();
		img_back.setOnClickListener(mylistener);
		addlist();
	}
	

	
	private void addlist() {
		// TODO 自动生成的方法存根
		
		list = new ArrayList<Friend_info>();
		adapter = new ABA_FriendsListAdapter(this, list);
		listview.setAdapter(adapter);

		ThreadPoolUtils.execute(new HttpGetThread(hand,
				geturl));
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				dialog(list.get((int) id));
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
					List<Friend_info> ListInfo = new MyJson().getFriendInfo(result);
					if (ListInfo!= null) {
						for (Friend_info info:ListInfo){
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
			case R.id.abaa_img_back:
				finish();
				break;

			default:
				break;
			}
		}
	}
	private void dialog(final Friend_info user) {
		Builder builder=new AlertDialog.Builder(this);
		//AlertDialog.Builder builder=new Builder(this);
		//AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle(R.string.abaa_txt_addfriends);
		builder.setMessage("add "+user.name+"?");
		builder.setPositiveButton("ok",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO 自动生成的方法存根
				ThreadPoolUtils.execute(new HttpGetThread(addfriendhand,
						new MyURL().addFriend(user.userid)));
				
			}
		});
		builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO 自动生成的方法存根
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
	
	Handler addfriendhand = new Handler(){
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
						Toast.makeText(getApplicationContext(), result, 1).show();
					}
				}
				
			}
	};
	
}
