package com.example.test.util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	//��static����Ϊ���Բ��ô���ʵ�������ɵ��ø÷����������߳�����Ϊ�����ȡ���ݷǳ���ʱ����ֹ����
		//�õ���HttpCallbackListener�ӿ�Ϊ��ʵ�ֻص����շ��������صĽ��
		public static void sendHttpRequest(final String address,
				final HttpCallbackListener listener) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					HttpURLConnection connection = null;
					try {  
						URL url = new URL(address); ////��ȡ URL ���󲢴���Ŀ�������ַ
						connection = (HttpURLConnection) url.openConnection();
						//��openConnection��ȡ����HttpURLConnection��ʵ��
						//�������󷽷���GET ���� POST
						//GET��ʾ�ӷ������л�ȡ���ݣ��������õ������ӳ�ʱ����ȡ��ʱ������
						connection.setRequestMethod("GET");
						 //�������ӳ�ʱ�Ͷ�ȡ��ʱ
						connection.setConnectTimeout(8000);
						connection.setReadTimeout(8000);
						//��ȡ���������ص���
						//����һ�������������ڶ�ȡconnection�е���Ϣ
						InputStream in = connection.getInputStream();
						 //��ȡ���������ж�ȡ����
						BufferedReader reader = new BufferedReader(new InputStreamReader(in));
						StringBuilder response = new StringBuilder();
						String line;
						while ((line = reader.readLine()) != null) {//���ж�ȡ
							response.append(line);//������ӵ�respond������
						}
						if (listener != null) {
							// �ص�onFinish()����
							listener.onFinish(response.toString());
						}
					} catch (Exception e) {
						if (listener != null) {
							// �ص�onError()����
							listener.onError(e);
						}
					} finally {
						if (connection != null) {
							connection.disconnect();//�Ͽ�����
						}
					}
				}
			}).start();//�����߳�
		}

}
