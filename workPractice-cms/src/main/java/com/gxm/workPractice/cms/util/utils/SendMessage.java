package com.gxm.workPractice.cms.util.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/* 功能:		HTTP接口 发送短信
 * 说明:		http://47.98.113.130:8011/asmx/smsservice.aspx?name=登录名&pwd=接口密码&mobile=手机号码&content=内容&stime=发送时间&type=pt&extno=自定义扩展码
 */
public class SendMessage {

    private static Logger _log = LoggerFactory.getLogger(SendMessage.class);

    /**
     * @param content 发送内容
     * @param phone   手机号
     * @throws IOException
     */
    public static boolean sendMsg(String content, String phone) throws IOException {
		Boolean sendResult = null;

		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://47.98.113.130:8011/asmx/smsservice.aspx?");
//		StringBuffer sb = new StringBuffer("http://34.55.0.172:18889/asmx/smsservice.aspx?");
		// 向StringBuffer追加用户名
		sb.append("name=xhrcywpjxt");
		// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
		sb.append("&pwd=F2707655E2CBD769013E9A0695A0");
		// 向StringBuffer追加手机号码
		sb.append("&mobile=" + phone);
		// 向StringBuffer追加消息内容转URL标准码
		sb.append("&content="+ URLEncoder.encode(content,"UTF-8"));
		//追加发送时间，可为空，为空为及时发送
		sb.append("&stime=");
		//type为固定值pt  extno为扩展码，必须为数字 可为空
		sb.append("&type=pt&extno=");
		// 创建url对象
		//String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
		System.out.println("sb:"+sb.toString());
		URL url = new URL(sb.toString());
		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");
		// 发送
		InputStream is =url.openStream();
		//转换返回值
		String returnStr = convertStreamToString(is);
		// 返回结果为‘0，20140009090990,1，提交成功’ 发送成功   具体见说明文档
		System.out.println(returnStr);

		if (returnStr.indexOf("0,1,0,提交成功") > 0) {
        _log.info("短信发送成功");
			sendResult = true;
		} else {
        _log.error("短信发送失败");
			sendResult = false;
		}
		return sendResult;
    }

    /**
     * 转换返回值类型为UTF-8格式.
     *
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
        StringBuilder sb1 = new StringBuilder();
        byte[] bytes = new byte[4096];
        int size = 0;

        try {
            while ((size = is.read(bytes)) > 0) {
                String str = new String(bytes, 0, size, "UTF-8");
                sb1.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb1.toString();
    }
}
