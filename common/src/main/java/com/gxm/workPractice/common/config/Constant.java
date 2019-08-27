package com.gxm.workPractice.common.config;

import java.util.Arrays;
import java.util.List;

/**
 * 常量
 */
public class Constant {
	/** 超级管理员ID */
	public static final String SUPER_ADMIN = "1";

	/**
	 * 菜单类型
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     * 实时上报类型
     */
    public static final String HEART_BEAT = "HeartBeat";                            //心跳类型

    public static final String REALTIME_STATE = "STATE";                            //客户端查询灯色

    public static final String SIGNAL_REMOVE = "SIGNAL_REMOVE";                     //信号机删除

    public static final String REALTIME_STATE_ALL = "STATE_ALL";                    //后台应答客户端灯色

    public static final String REALTIME_PROGRAMME = "PROGRAMME";                    //方案

    public static final String REALTIME_LINE = "LINE";                              //链接(离在线状态)

    public static final String REALTIME_FAULT = "FAULT";                            //故障

    public static final String DEVICE_NUM = "DEVICE_NUM";                           //查询设备总数/在线数/故障数

    public static final String REALTIME_FAULT_RECOVERY = "FAULT_RECOVERY";          //故障恢复

    public static final String ALL_COMBINATIONID = "000000";                        //websocket查询所有id

    public static final String REDIS_ONLINE_SET = "xhycywpjxtOnLine";               //缓存中在线集合key

    public static final String REDIS_LKEXTIS_SET = "xhycywpjxtLkExtis";             //redis中存储存在路口的集合key

    public static final String ONLINE_STATE = "ON_LINE";                            //在线标识

    public static final String OFFLINE_STATE = "OFF_LINE";                          //离线标识

    public static final String SIGNAL_STATISTIC_COUNT = "SIGNAL_STATISTIC_COUNT";   //信号机统计计数

    public static final List<Integer> FAULT_RECOVERY_STATE = Arrays.asList(161,163,116,118,120);//故障恢复类型数组

    public static final List<Integer> FAULT_STATE = Arrays.asList(177,179,132,134,136);         //故障类型数组

    /**
     * 监测仪上报的灯色类型
     */
    // 监测仪回复实时灯色包
    public static final String MONITOR_REPLY_LIGHT_COLOR = "monitorReplyLightColor";
    // 监测仪主动上报灯色状态变化包
    public static final String MONITOR_REPORT_LIGHT_STATUS = "monitorReportLightStatus";
    // 监测仪应答灯色状态变化包
    public static final String MONITOR_RESPONSE_LIGHT_STATUS = "monitorResponseLightStatus";
    // 监测仪主动上报故障信息包
    public static final String MONITOR_REPORT_FAULT = "monitorReportFault";
    // 监测仪回复故障信息包
    public static final String MONITOR_REPLY_FAULT = "monitorReplyFault";
    // 监测仪上报方案信息包
    public static final String MONITOR_REPORT_SCHEME = "monitorReportScheme";
    // 监测仪回复方案信息包
    public static final String MONITOR_REPLY_SCHEME = "monitorReplyScheme";

}
