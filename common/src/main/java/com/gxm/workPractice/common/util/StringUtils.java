package com.gxm.workPractice.common.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 工具类
 */
public class StringUtils {

	public static boolean isBlank(String str) {
		if (str != null && !str.equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isNotBlank(String str) {
		if (str != null && !str.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 过滤空NULL
	 * 
	 * @param o
	 * @return
	 */
	public static String FilterNull(Object o) {
		return o != null && !"null".equals(o.toString()) ? o.toString().trim()
				: "";
	}

	/**
	 * 是否为空
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if ("".equals(FilterNull(o.toString()))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否不为空
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isNotEmpty(Object o) {
		if (o == null) {
			return false;
		}
		if ("".equals(FilterNull(o.toString()))) {
			return false;
		} else {
			return true;
		}
	}
	    
	    /**
	     * 是否可转化为数字
	     * @param o
	     * @return
	     */
	    public static boolean isNum(Object o) {
	        try {
	            new BigDecimal(o.toString());
	            return true;
	        } catch (Exception e) {
	        }
	        return false;
	    }
	    
	    /**
	     * 是否可转化为Long型数字
	     * @param o
	     * @return
	     */
	    public static boolean isLong(Object o) {
	        try {
	            new Long(o.toString());
	            return true;
	        } catch (Exception e) {
	        }
	        return false;
	    }
	    
	    /**
	     * 转化为Long型数字, 不可转化时返回0
	     * @param o
	     * @return
	     */
	    public static Long toLong(Object o) {
	        if (isLong(o)) {
	            return new Long(o.toString());
	        } else {
	            return 0L;
	        }
	    }
	    
	    /**
	     * 转化为int型数字, 不可转化时返回0
	     * @param o
	     * @return
	     */
	    public static int toInt(Object o) {
	        if (isNum(o)) {
	            return new Integer(o.toString());
	        } else {
	            return 0;
	        }
	    }
	    

	    /**
	     * 逗号表达式_添加
	     * @param commaexpress 原逗号表达式 如 A,B
	     * @param newelement   新增元素 C
	     * @return A,B,C
	     */
	    public static String comma_add(String commaexpress, String newelement) {
	        return comma_rect(FilterNull(commaexpress) + "," + FilterNull(newelement));
	    }

	    /**
	     * 逗号表达式_删除
	     * @param commaexpress  原逗号表达式 如 A,B,C
	     * @param delelement 删除元素 C,A
	     * @return B
	     */
	    public static String comma_del(String commaexpress, String delelement) {
	        if ((commaexpress == null) || (delelement == null) || (commaexpress.trim().equals(delelement.trim()))) {
	            return "";
	        }
	        String[] deletelist = delelement.split(",");
	        String result = commaexpress;
	        for (String delstr : deletelist) {
	            result = comma_delone(result, delstr);
	        }
	        return result;
	    }
	    
	    /**
	     * 逗号表达式_单一删除
	     * @param commaexpress  原逗号表达式 如 A,B,C
	     * @param delelement 删除元素 C
	     * @return A,B
	     */
	    public static String comma_delone(String commaexpress, String delelement) {
	        if ((commaexpress == null) || (delelement == null) || (commaexpress.trim().equals(delelement.trim()))) {
	          return "";
	        }
	        String[] strlist = commaexpress.split(",");
	        StringBuffer result = new StringBuffer();
	        for (String str : strlist) {
	          if ((!str.trim().equals(delelement.trim())) && (!"".equals(str.trim()))) {
	            result.append(str.trim() + ",");
	          }
	        }
	        return result.toString().substring(0, result.length() - 1 > 0 ? result.length() - 1 : 0);
	      }

	    /**
	     * 逗号表达式_判断是否包含元素
	     * @param commaexpress 逗号表达式 A,B,C
	     * @param element C
	     * @return true
	     */
	    public static boolean comma_contains(String commaexpress, String element) {
	        boolean flag = false;
	        commaexpress = FilterNull(commaexpress);
	        element = FilterNull(element);
	        if (!"".equals(commaexpress) && !"".equals(element)) {
	            String[] strlist = commaexpress.split(",");
	            for (String str : strlist) {
	                if (str.trim().equals(element.trim())) {
	                    flag = true;
	                    break;
	                }
	            }
	        }
	        return flag;
	    }

	    /**
	     * 逗号表达式_取交集
	     * @param commaexpressA 逗号表达式1  A,B,C
	     * @param commaexpressB 逗号表达式2  B,C,D
	     * @return B,C
	     */
	    public static String comma_intersect(String commaexpressA, String commaexpressB) {
	        commaexpressA = FilterNull(commaexpressA);
	        commaexpressB = FilterNull(commaexpressB);
	        StringBuffer result = new StringBuffer();
	        String[] strlistA = commaexpressA.split(",");
	        String[] strlistB = commaexpressB.split(",");
	        for (String boA : strlistA) {
	            for (String boB : strlistB) {
	                if (boA.trim().equals(boB.trim())) {
	                    result.append(boA.trim() + ",");
	                }
	            }
	        }
	        return comma_rect(result.toString());
	    }

	    /**
	     * 逗号表达式_规范
	     * @param commaexpress  逗号表达式  ,A,B,B,,C
	     * @return A,B,C
	     */
	    public static String comma_rect(String commaexpress) {
	        commaexpress = FilterNull(commaexpress);
	        String[] strlist = commaexpress.split(",");
	        StringBuffer result = new StringBuffer();
	        for (String str : strlist) {
	            if (!("".equals(str.trim())) && !("," + result.toString() + ",").contains("," + str + ",") && !"null".equals(str)) {
	                result.append(str.trim() + ",");
	            }
	        }
	        return result.toString().substring(0, (result.length() - 1 > 0) ? result.length() - 1 : 0);
	    }
	    
	    /**
	     * 逗号表达式_反转
	     * @param commaexpress A,B,C
	     * @return C,B,A
	     */
	    public static String comma_reverse(String commaexpress) {
	        commaexpress = FilterNull(commaexpress);
	        String[] ids = commaexpress.split(",");
	        StringBuffer str = new StringBuffer();
	        for (int i = ids.length - 1; i >= 0; i--) {
	            str.append(ids[i] + ",");
	        }
	        return comma_rect(str.toString());
	    }

	    /**
	     * 逗号表达式_获取首对象
	     * @param commaexpress A,B,C
	     * @return A
	     */
	    public static String comma_first(String commaexpress) {
	        commaexpress = FilterNull(commaexpress);
	        String[] ids = commaexpress.split(",");
	        System.out.println("length:" + ids.length);
	        if ((ids != null) && (ids.length > 0)) {
	            return ids[0];
	        }
	        return null;
	    }

	    /**
	     * 逗号表达式_获取尾对象
	     * @param commaexpress A,B,C
	     * @return C
	     */
	    public static String comma_last(String commaexpress) {
	        commaexpress = FilterNull(commaexpress);
	        String[] ids = commaexpress.split(",");
	        if ((ids != null) && (ids.length > 0)) {
	            return ids[(ids.length - 1)];
	        }
	        return null;
	    }

	    /**
	     * 替换字符串,支持字符串为空的情形
	     * @param strData
	     * @param regex
	     * @param replacement
	     * @return
	     */
	    public static String replace(String strData, String regex, String replacement) {
	        return strData == null ? "" : strData.replaceAll(regex, replacement);
	    }
	        
	    /**
	     * 字符串转为HTML显示字符
	     * @param strData
	     * @return
	     */
	    public static String String2HTML(String strData){
	        if( strData == null || "".equals(strData) ){
	            return "" ;
	        }
	        strData = replace(strData, "&", "&amp;");
	        strData = replace(strData, "<", "&lt;"); 
	        strData = replace(strData, ">", "&gt;");
	        strData = replace(strData, "\"", "&quot;");
	        return strData;
	    }
	    

	    
	    /** 过滤特殊符号 */ 
	    public static String regex(String str){
	        Pattern pattern = Pattern.compile("[0-9-:/ ]");// 中文汉字编码区间
	        Matcher matcher;
	        char[] array = str.toCharArray();
	        for (int i = 0; i < array.length; i++) {
	            matcher = pattern.matcher(String.valueOf(array[i]));
	            if (!matcher.matches()) {// 空格暂不替换
	                str = str.replace(String.valueOf(array[i]), "");// 特殊字符用空字符串替换
	            }
	        }
	         
	        return str;    
	    }
	    
	    public static String comma_insert(String commaexpress, String newelement,int index){
	        int length = commaexpress.length();
	        if ( index > length ) {
	            index = length;
	        }else if ( index < 0){
	            index = 0;
	        }
	        String result = commaexpress.substring(0, index) + newelement + commaexpress.substring(index, commaexpress.length());
	        return result;
	    }
	    
	    /**
	     * 去除字符串中 头和尾的空格，中间的空格保留
	     * 
	     * @Title: trim
	     * @Description: TODO
	     * @return String
	     * @throws
	     */
	    public static String trim(String s) {
	        int i = s.length();// 字符串最后一个字符的位置
	        int j = 0;// 字符串第一个字符
	        int k = 0;// 中间变量
	        char[] arrayOfChar = s.toCharArray();// 将字符串转换成字符数组
	        while ((j < i) && (arrayOfChar[(k + j)] <= ' '))
	        ++j;// 确定字符串前面的空格数
	        while ((j < i) && (arrayOfChar[(k + i - 1)] <= ' '))
	        --i;// 确定字符串后面的空格数
	        return (((j > 0) || (i < s.length())) ? s.substring(j, i) : s);// 返回去除空格后的字符串
	    }
	    /**
	     * 得到大括号中的内容
	     * @param str
	     * @return
	     */
	    public static String getBrackets(String str) {
	        int a = str.indexOf("{");
	        int c = str.indexOf("}");
	        if (a >= 0 && c >= 0 & c > a) {
	            return (str.substring(a + 1, c));
	        } else {
	            return str;
	        }
	    }

	    /**
	     * 将字符串中所有的，替换成|
	     * 
	     * @param str
	     * @return
	     */
	    public static String commaToVerti(String str) {
	        if (str != null && !"".equals(str) && str.contains(",")) {
	            return str.replaceAll(",", "|");
	        } else {
	            return str;
	        }
	    }

	    /**
	     * 去掉字符串中、前、后的空格
	     * @throws IOException
	     */
	    public static String extractBlank(String name) {
	        if (name != null && !"".equals(name)) {
	            return name.replaceAll(" +", "");
	        } else {
	            return name;
	        }
	    }

	    /**
	     * 将null换成""
	     * @param str
	     * @return
	     */
	    public static String ConvertStr(String str) {
	        return str != null && !"null".equals(str) ? str.trim() : "";
	    }
}
