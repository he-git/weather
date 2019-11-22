package com.example.test.util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	//用static是因为可以不用创建实例，即可调用该方法。开子线程是因为网络获取数据非常耗时，防止堵塞
		//用到了HttpCallbackListener接口为了实现回调接收服务器返回的结果
		public static void sendHttpRequest(final String address,
				final HttpCallbackListener listener) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					HttpURLConnection connection = null;
					try {  
						URL url = new URL(address); ////获取 URL 对象并传入目标网络地址
						connection = (HttpURLConnection) url.openConnection();
						//用openConnection获取到了HttpURLConnection的实例
						//设置请求方法：GET 或者 POST
						//GET表示从服务器中获取数据，下面设置的是连接超时，读取超时毫秒数
						connection.setRequestMethod("GET");
						 //设置连接超时和读取超时
						connection.setConnectTimeout(8000);
						connection.setReadTimeout(8000);
						//获取服务器返回的流
						//定义一个输入流，用于读取connection中的信息
						InputStream in = connection.getInputStream();
						 //获取输入流进行读取数据
						BufferedReader reader = new BufferedReader(new InputStreamReader(in));
						StringBuilder response = new StringBuilder();
						String line;
						while ((line = reader.readLine()) != null) {//按行读取
							response.append(line);//依行添加到respond对象中
						}
						if (listener != null) {
							// 回调onFinish()方法
							listener.onFinish(response.toString());
						}
					} catch (Exception e) {
						if (listener != null) {
							// 回调onError()方法
							listener.onError(e);
						}
					} finally {
						if (connection != null) {
							connection.disconnect();//断开连接
						}
					}
				}
			}).start();//运行线程
		}

}
