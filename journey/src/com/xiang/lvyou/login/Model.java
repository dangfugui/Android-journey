package com.xiang.lvyou.login;

import com.serve.html.Friend_info;
import com.serve.html.UserInfo;
import com.xiang.lvyou.url.MyURL;

public class Model {

//	public static String HTTPURL = "http://10.0.3.2:9096/recommend/Service/";
	
	public static  String HTTPURL=MyURL.localhost+"recommend/Service/";
	public static String REGISTET = "registerProcess.php";
	public static String LOGIN = "loginProcess.php";
	public static String upDataUser="updateUser.php";
	
	public static boolean IMGFLAG = false;
	public static Friend_info MYUSERINFO = null;
	// APP�ͷ�KEY
	public static String APPKEY = "f241ebf4d4a1e1dfae6f1a3e49aad2f5";
	/** ��ǰ DEMO Ӧ�õ� APP_KEY��������Ӧ��Ӧ��ʹ���Լ��� APP_KEY �滻�� APP_KEY */
	public static final String APP_KEY = "3987368837";

	/**
	 * ��ǰ DEMO Ӧ�õĻص�ҳ��������Ӧ�ÿ���ʹ���Լ��Ļص�ҳ��
	 * 
	 * <p>
	 * ע��������Ȩ�ص�ҳ���ƶ��ͻ���Ӧ����˵���û��ǲ��ɼ��ģ����Զ���Ϊ������ʽ������Ӱ�죬 ����û�ж��彫�޷�ʹ�� SDK ��֤��¼��
	 * ����ʹ��Ĭ�ϻص�ҳ��https://api.weibo.com/oauth2/default.html
	 * </p>
	 */
	public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

	/**
	 * Scope �� OAuth2.0 ��Ȩ������ authorize �ӿڵ�һ��������ͨ�� Scope��ƽ̨�����Ÿ����΢��
	 * ���Ĺ��ܸ������ߣ�ͬʱҲ��ǿ�û���˽�������������û����飬�û����� OAuth2.0 ��Ȩҳ����Ȩ�� ѡ����Ӧ�õĹ��ܡ�
	 * 
	 * ����ͨ������΢������ƽ̨-->��������-->�ҵ�Ӧ��-->�ӿڹ������ܿ�������Ŀǰ������Щ�ӿڵ� ʹ��Ȩ�ޣ��߼�Ȩ����Ҫ�������롣
	 * 
	 * Ŀǰ Scope ֧�ִ����� Scope Ȩ�ޣ��ö��ŷָ���
	 * 
	 * �й���Щ OpenAPI ��ҪȨ�����룬��鿴��http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI
	 * ���� Scope ���ע�������鿴��http://open.weibo.com/wiki/Scope
	 */
	public static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
			+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
			+ "follow_app_official_microblog," + "invitation_write";
}
