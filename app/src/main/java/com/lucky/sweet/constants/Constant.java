package com.lucky.sweet.constants;

/**
 * Created by lenovo on 2016/2/20.
 */
public class Constant {

    public static final String PKG_NAME = "com.llymm.carmap";

    /**
     * 主界面的标签ID
     */
    public static class TabTag{
        public static final String MAP = "mapTileDownloading";
        public static final String MSG = "message";
        public static final String CONTRACT = "contract";
        public static final String ME = "me";
    }

    /**
     * 广播Action
     */
    public static class ACTION {
        public static final String RECEIVER_ACTION_BASE = "RECEIVER_ACTION_BASE.BaseActivity";
        public static final String RECEIVER_ACT_LOGO = "com.llymm.carmap.activitys.StartActivity";
        public static final String RECEIVER_ACT_MAIN = "com.llymm.carmap.activitys.MainActivity";
        public static final String RECEIVER_ACT_LOGIN = "com.llymm.carmap.activitys.LoginActivity";
        public static final String RECEIVER_ACT_REGISTER = "com.llymm.carmap.activitys.RECEIVER_ACT_REGISTER";
        public static final String RECEIVER_ACT_REGISTER_USER_INFO = "com.llymm.carmap.activitys.RECEIVER_ACT_REGISTER_USER_INFO";
    }

    /**
     * 连接状态
     */
    public static class NET_STATUE {
        public static final int DISCONNECTION    = 0;//连接断开
        public static final int CONNECTING       = 1;//正在创建连接
        public static final int CONNECTED        = 2;//连接正常
        public static final int LOGINING         = 3;//正在登录
        public static final int LOGINED          = 4;//登录成功
        public static final int LOGINFAIL        = 5;//登录失败
    }

    /**
     * 消息通信相关
     */
    public static class MESSAGE {
        //表示申请功能，当一个服务中可能有多个连接时使用，标志哪个连接
        public static final String KEY_FUNCTION = "FUNCTION";
        //消息标识，可以标识从哪里来的消息，可用于测试
        public static final String KEY_FROM = "FROM_WHERE";
        public static final String KEY_RECEIVE_ACTION = "KEY_RECEIVE_ACTION";//已弃用

        //内部广播通信标识，前台和后台使用一套相同广播:当小于0时为无效值
        // public static final String KEY_REQUEST_ID                   = "REQUEST";
        public static final int VALUE_PROC_CLOSE_SOCKET             = 0;//关闭Socket
        public static final int VALUE_PROC_REGISTER_GET_CODE        = 1;//注册:申请验证码
        public static final int VALUE_PROC_LOGIN                    = 2;//登录
        public static final int VALUE_PROC_REGISTER_CHECK_CODE      = 3;//注册:核验验证码
        public static final int VALUE_PROC_REGISTER_USER_INFO       = 4;//注册:上传用户信息
        public static final int VALUE_PROC_REPORT_GPS               = 5;//上报GPS位置
        public static final int VALUE_PROC_TRAFFIC_QUERY            = 6;//查询路况
        public static final int VALUE_PROC_TRANSFER_RADIO_UPLOAD    = 7;//上传语音
        public static final int VALUE_PROC_TRANSFER_RADIO_REQ       = 8;//下载Radio语音
        public static final int VALUE_PROC_CLIENT_SYNC_VERSION      = 9;//同步版本号
        public static final int VALUE_PROC_SYNC_REQUEST             = 10;//同步请求信息
        public static final int VALUE_PROC_ADD_CONTACT_CONFIRM      = 11;//添加好友确认请求
        public static final int VALUE_PROC_ADD_CONTACT              = 12;//添加好友请求
        public static final int VALUE_PROC_DELETE_CONTACT           = 13;//删除好友
        public static final int VALUE_PROC_SYNC_CONTACT             = 14;//同步好友信息
        public static final int VALUE_PROC_UPDATE_CONTACT_INFO      = 15;//更新好友信息
        public static final int VALUE_PROC_REQ_FILE                 = 16;//请求文件信息
        //public static final int VALUE_PROC_UPLOAD_FILE_INFO         = 17;//上传文件信息
        //TODO JAY remove old radio solution public static final int VALUE_PROC_REQ_RADIO_INFO           = 18;//请求Radio信息
        public static final int VALUE_PROC_UPLOAD_RADIO_INFO        = 19;//上传Radio信息
        public static final int VALUE_PROC_GET_TOKEN_INFO           = 20;//请求Token
        public static final int VALUE_PROC_SYNC_SYSTEM_SETTING_INFO     = 21;//同步系统设置
        public static final int VALUE_PROC_FIND_USER                 = 22;//查找用户
        public static final int VALUE_PROC_GET_USER_INFO             = 23;//获取用户信息
        public static final int VALUE_PROC_REQ_AVATAR_FILE_INFO     = 24;//获取头像信息
        public static final int VALUE_PROC_FIND_GROUP                = 25;//查找车友汇
        public static final int VALUE_PROC_GROUP_REQUEST             = 26;//申请加入群
        public static final int VALUE_PROC_GET_GROUP_INFO            = 27;//申请群信息
        public static final int VALUE_PROC_UPDATE_PERSONALINFO       = 28;//更新个人信息
        public static final int VALUE_PROC_GET_CAR_INFO               = 29;//获取车辆信息
        public static final int VALUE_PROC_UPDATE_CAR_INFO            = 30;//更新车辆信息
        public static final int VALUE_PROC_GET_CAR_VOLUME             = 31;//获取车排量信息
        public static final int VALUE_PROC_GET_CAR_CATEGORY           = 32;//获取车型配置信息

        public static final int VALUE_PROC_SEND_MESSAGE_APPLY         = 90;//获取车型配置信息

        public static final int VALUE_PROC_CMD                        = 9999;//获取车型配置信息

        public static final int VALUE_SOCKET_CREATE_FAIL            = 10001;//创建连接失败
        public static final int VALUE_SOCKET_BREAK                  = 10002;//连接断开
        public static final int VALUE_FUNCTION_PLAY_RADIO           = 10003;//Function，播放Radio
        public static final int VALUE_FUNCTION_AVATAR_COMPLETE     = 10004;//Function，下载头像完成

        //应答返回的标识
        public static final String KEY_RESPONSE_ID      = "KEY_RESPONSE_ID";
        public static final String KEY_RESPONSE_VALUE   = "RESPONSE_VALUE_KEY";//返回值
        public static final String KEY_RESPONSE_FRAMEHEADERTYPE_VALUE = "KEY_RESPONSE_FRAMEHEADERTYPE_VALUE";//应答帧类型

        //应答返回的值类型
        public static final String KEY_RESPONSE_TYPE    = "KEY_REPONSE_TYPE";
        public static final int     VALUE_MSG_PAR        = 1;//值类型:Parcelable
        public static final int     VALUE_MSG_SER        = 2;//值类型:Serializable

        //本地通信使用的数据Key
        public static final String KEY_ACCOUNT_TYPE     = "KEY_ACCOUNT_TYPE";//账号类型
        public static final String KEY_ACCOUNT          = "KEY_ACCOUNT";//账号:手机/邮箱
        public static final String KEY_PASSWORD         = "KEY_PASSWORD";//密码
        public static final String KEY_NICK             = "KEY_NICK";//用户名:昵称
        public static final String KEY_GENDER           = "KEY_GENDER";//用户性别
        public static final String KEY_UUID             = "KEY_UUID";//设备标识
        public static final String KEY_VERIFY_PURPOSE   = "KEY_VERIFY_PURPOSE";//验证目的
        public static final String KEY_VERIFY_CODE      = "KEY_VERIFY_CODE";//验证码
        public static final String KEY_IS_RECONNECT     = "KEY_IS_RECONNECT";//是否重新建立连接
        public static final String KEY_LATITUDE         = "KEY_LATITUDE";  //纬度
        public static final String KEY_LONGITUDE        = "KEY_LONGITUDE"; //经度
        public static final String KEY_DIRECTION        = "KEY_DIRECTION"; //方向
        public static final String KEY_VELOCITY         = "KEY_VELOCITY";  //速度
        public static final String KEY_PAR_BOTTOM_LEFT  = "KEY_PAR_BOTTOM_LEFT";  //屏幕左下坐标
        public static final String KEY_PAR_TOP_RIGHT    = "KEY_PAR_TOP_RIGHT";  //屏幕右下坐标
        public static final String KEY_SCALE            = "KEY_SCALE";  //缩放级别
        public static final String KEY_DURATION         = "KEY_DURATION";  //音频长度
        public static final String KEY_PAR_RADIO_CONTENT= "KEY_PAR_RADIO_CONTENT";  //音频内容
        public static final String KEY_PAR_COORD        = "KEY_PAR_COORD";  //坐标点
        public static final String KEY_USER_ID          = "KEY_USER_ID";// 用户ID
        public static final String KEY_TO_USER_ID       = "KEY_TO_USER_ID";//目标用户ID
        public static final String KEY_FROME_USER_ID    = "KEY_FROME_USER_ID";//来源用户ID
        public static final String KEY_REQUEST_MESSAGE  = "KEY_REQUEST_MESSAGE";
        public static final String KEY_RespondToRequest = "KEY_RespondToRequest";
        public static final String KEY_REMARK_NAME      = "KEY_REMARK_NAME";
        public static final String KEY_RANK             = "KEY_RANK";
        public static final String KEY_GROUP_NAME       = "KEY_GROUP_NAME";
        public static final String KEY_GROUP_ID       = "KEY_GROUP_ID";
        public static final String KEY_PAR_RADIO_INFO   = "KEY_PAR_RADIO_INFO";  //Radio信息
        public static final String KEY_FILE_TYPE  = "KEY_FILE_TYPE";  //文件类型
        public static final String KEY_FILE_VERSION    = "KEY_FILE_VERSION";  //文件版本号
        public static final String KEY_REQ_TARGET_ID   = "KEY_REQ_TARGET_ID";  //请求目标ID
        public static final String KEY_OSS_FILE_NAME   = "KEY_OSS_FILE_NAME";  //OSS文件名
        public static final String KEY_FIND_USER_ID_TYPE = "KEY_USER_ID_TYPE";//查询用户的用户ID类型
        public static final String KEY_FIND_USER_ID = "KEY_FIND_USER_ID";//查询用户ID
        public static final String KEY_AVATAR_TYPE  = "KEY_AVATAR_TYPE";  //头像文件类型
        public static final String KEY_FIND_GROUP_KEYWORD = "KEY_FIND_GROUP_KEYWORD";//搜索车友汇关键字
        public static final String KEY_FIND_GROUP_CONDITION_BEAN = "KEY_FIND_GROUP_CONDITION_BEAN";//搜索车友汇条件
        public static final String KEY_FIND_GROUP_PAGENO = "KEY_FIND_GROUP_PAGENO";//搜索车友汇PAGE_NO
        public static final String KEY_FIND_GROUP_ITEM_INFO = "KEY_FIND_GROUP_ITEM_INFO";//搜索车友汇搜索结果中一条群信息
        public static final String KEY_PAR_GROUP_REQUEST_INFO_BEAN = "KEY_PAR_GROUP_REQUEST_INFO_BEAN";//
        public static final String KEY_PAR_USER_INFO_BEAN  = "KEY_PAR_USER_INFO_BEAN";  //用户信息
        public static final String KEY_CAR_PLATE_NO = "KEY_CAR_PLATE_NO";//车牌号
        public static final String KEY_PAR_CAR_INFO = "KEY_PAR_CAR_INFO";//车辆信息
        public static final String KEY_IS_MODIFY_CAR_VOLUME = "KEY_IS_MODIFY_CAR_VOLUME";//是否修改车排量
        public static final String KEY_CATEGORY_ID = "KEY_CATEGORY_ID";//车型ID
        //public static final String KEY_CAR_CATEGORY = "KEY_CAR_CATEGORY";//车型信息版本号

        public static final String KEY_USER_DATA = "KEY_USER_DATA";

        public static final int SEND_MESSAGE_RESPONSE = -1;
    }

    /**
     * Activity之间通信使用的Key,及Value值
     */
    public static class ACT_KEY {
        public static final String KEY_FROME_WHERE      = "KEY_FROME_WHERE";
        //public static final String KEY_RESULT_CODE      = "KEY_RESULT_CODE";

        public static final int CODE_LOCATION = 1001;
        public static final int CODE_BARDIAN  = 1002;
        public static final int CODE_LIMIT_CAR_MODEL  = 1003;
        public static final int CODE_CAR_CATEGORY = 1004;
        public static final int CODE_REGISTER_TIME = 1005;
    }

    public static class RequestCode{

        public static final int REGISTRATION_DATE = 1;
        public static final int CAR_CATEGORY = 2;
    }




}
