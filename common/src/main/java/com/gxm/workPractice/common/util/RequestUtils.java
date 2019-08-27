package com.gxm.workPractice.common.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class RequestUtils {
    /**
     * 将请求中的参数转化为Map
     * @param request
     * @return
     */
   public static Map<String,String> parameterToMap(HttpServletRequest request){
       Map<String , String> params = new HashMap<String, String>();
       Enumeration<String> paramNames = request.getParameterNames();
       while (paramNames.hasMoreElements()) {
           String paramName = paramNames.nextElement();
           params.put(paramName, request.getParameter(paramName));
       }
       return params;
   }
   
   /**
	 * 获取请求参数中所有的信息
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				//在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				//System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
				if (null == res.get(en) || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		return res;
	}
}
