package com.serve.html;

import android.os.Handler;
import android.os.Message;


/**
 * ����Post������߳�
 * */

public class HttpPostThread implements Runnable {

	private Handler hand;
	private String url;
	private String value;
	private String img = "";
	private MyPost myGet = new MyPost();

	public HttpPostThread(Handler hand, String endParamerse, String value,
			String img) {
		this.hand = hand;
		// ƴ�ӷ��ʷ����������ĵ�ַ
		url = endParamerse;
		this.value = value;
		this.img = img;
	}

	public HttpPostThread(Handler hand, String endParamerse, String value) {
		this.hand = hand;
		// ƴ�ӷ��ʷ����������ĵ�ַ
		url = endParamerse;
		this.value = value;
	}

	@Override
	public void run() {
		// ��ȡ���ǻص���ui��message
		Message msg = hand.obtainMessage();
		String result = null;
		if (img.equalsIgnoreCase("")) {
			result = myGet.doPost(url, value);
		} else {
			result = myGet.doPost(url, img, value);
		}
		msg.what = 200;
		msg.obj = result;
		// ����ui������Ϣ��������
		hand.sendMessage(msg);

	}

}
