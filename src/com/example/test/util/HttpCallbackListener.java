package com.example.test.util;

public interface HttpCallbackListener {//��HttpCallbackListener�ӿ�ʵ��  �ص���Java�ṩ�Ļص����ƣ�
	
	void onFinish(String response);// ������ response �е�requestURL
	
	void onError(Exception e); // ��������쳣������д���
}
