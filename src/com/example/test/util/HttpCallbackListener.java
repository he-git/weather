package com.example.test.util;

public interface HttpCallbackListener {//用HttpCallbackListener接口实现  回调（Java提供的回调机制）
	
	void onFinish(String response);// 解析出 response 中的requestURL
	
	void onError(Exception e); // 在这里对异常情况进行处理
}
