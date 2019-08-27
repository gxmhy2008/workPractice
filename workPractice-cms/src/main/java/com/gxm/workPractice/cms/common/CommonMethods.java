package com.gxm.workPractice.cms.common;

import com.gxm.workPractice.common.util.StringUtils;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * cms模块的一些公共方法
 */
public class CommonMethods {
    /**
     * 使用区域编码和路口编码生成区域路口编码唯一标识一台监测仪
     * @param areaCode
     * @param roadCode
     * @return
     */
    public synchronized  static  String buildAreaAndRoadNum(int areaCode,int roadCode) {
        return areaCode + "-" + roadCode;
    }

    /**
     * 解析base64，返回图片所在路径
     * @param base64Info
     * @return
     */
    public static byte[] decodeBase64(String base64Info) {
        if (StringUtils.isEmpty(base64Info)) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        if (!base64Info.contains("base64,"))
            return null;
        String[] arr = base64Info.split("base64,");
        try {
            return decoder.decodeBuffer(arr[1]);
        } catch (IOException e) {
            return null;
        }
    }
}
