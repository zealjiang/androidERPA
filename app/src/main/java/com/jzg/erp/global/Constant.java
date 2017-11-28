package com.jzg.erp.global;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/6/7 14:47
 * @desc:存放各种SharedPreference，intent跳转的key和其他常量
 */
public class Constant {
    /**
     * 确定列表标题和内容是否有重复标记
     */
    public static final String IS_TITLE = "istitle";
    public static final String ERROR_FORNET = "网络请求失败，请检查您的网络";

    /**
     * 填写完成
     */
    public static final int WRITE_FILL = 666;
    /**
     * 拍照
     */
    public static final int REQUEST_CODE_CAMERA = 100;
    public static final int REQUEST_CODE_LOCAL = 101;
    public static final int REQUEST_CODE_GETIMAGE_BYCROP = 102;

    /**
     * 照片的临时存放地址
     */
    public static final String TEMP_PATH = "/JZGErpChe/";

    /**
     * 跳转单选页面
     */
    public static final String KEY_ARR="arr";
    public static final String KEY_TITLE="title";


    public static final String[] BIANSUXIANG={"AT","MT","AMT"};
    public static final String[] YUSUAN={"3~5万","5~6万","6~8万","8~10万","10万以上"};
    public static final String INPUT_TITLE = "input_title";
    public static final String INPUT_HINT = "input_hint";
    public static final String SHOWTEXT = "showText";
    public static final String INPUT_TYPE = "input_type";
    public static final String INPUT_LENGTH = "input_length";
    public static final String ACTIVITY_INPUT = "activity_input";
    public static final String INPUT_REQUESTCODE = "input_requestcode";
    public static final String INPUT_TIPS = "input_tips";
    public static final String OLD_DATA = "oldData";


    public static final String KEY_ACache_UserWrapper = "userwrapper";
    public static final String CARDETAIL = "车辆详情";
    public static final String MAKE_NAME = "makename";
    public static final String MODE_NAME = "modename";
    public static final String CARSOURCEID = "carsourceId ";
    public static final String CARSTATUS = "carstatus ";
    public static final String CARID = "carId";
        public static final String CARDETAILPATH = "cardetailpath";
    public static final String ISSHOWBUTTON = "isshowbutton";
    public static final String STATUSTEXTCOLOR1 = "#ffffff";
    public static final String STATUSZAISHOUCOLOR = "#4790ef";//在售背景
    public static final String STATUSYISHOUCHCOLOR = "#7a7a7a";//已售出
    public static final String STATUSYIYUDINGCOLOR = "#ff612a";//已预订
    public static final String STATUSTEXTCOLOR2 = "#5495e6";//已整备
    public static final String STATUSTEXTCOLORNO2 = "#9fa5ab";//未整备
    public static final String MONEYTEXTCOLOR = "#ff4c4d";//有价格
    public static final String NOMONEYTEXTCOLOR = "#999999";//无价格
    /**
     * 联网访问成功
     */
    public static final int SUCCESS = 1;
    public static final int FAIL = 0;

    public static final int PAGECOUNT = 20;

    public static final String activity_input = "activity_input";
    public static final String INPUT_VIN = "INPUT_VIN";
    public static final String input_title = "input_title";
    public static final String input_hint = "input_hint";
    public static final String showText = "showText";
    public static final String input_type = "input_type";
    public static final String input_length = "input_length";
    public static final String car_Time_MSG = "car_Time_MSG";
    public static final String activity_radio = "activity_radio";
    public static final String CHECKED_ITEM = "checkedItem";
    public static final String activity_radioGroup = "activity_radioGroup";
    public static final String activity_radio_title = "activity_radio_title";
    //基本信息填写
    public static final int INPUT_VIN_ID = 0x00000010;
    public static final int CAR_NO = 0x00000030;
    public static final int guohu_num = 0x00000040;//过户次数
    public static final int XINGSHILICHENG = 0x00000060;
    public static final int XINCHESHICHANGJIA = 0x00000070;//新车市场价
    public static final int key_num = 0x00000240;//钥匙把数
    public static final int shangyexiane = 0x00000250;//商险金额

    public static final int CHOOSE_CAR_BRAND = 0x00000780;
    public static final int CHOOSE_CAR_MODEL = 0x00000790;
    public static final int CHOOSE_STYLE = 0x00000800;
    public static final int Time_MSG = 0x00000830;
    public static final int CAR_COLOR = 0x00000150;
    public static final int INNER_COLOR = 0x00000151;

    //车况描述
    /**
     * 返回数据Code
     */
    public static final int SHEET_TWO_COLUMN_MSG = 0x00000840;
    /**
     * 第一列显示数据
     */
    public static final String FIRST_COLUMN_SHOW = "first_column_show";
    /**
     * 第二列显示数据
     */
    public static final String SECOND_COLUMN_SHOW = "second_column_show";
    /**
     * 第一列最大显示数量
     */
    public static final String FIRST_COLUMN_SHOW_MAX = "first_column_show_max";
    /**
     * 第一列最小显示数量
     */
    public static final String FIRST_COLUMN_SHOW_MIM = "first_column_show_min";
    /**
     * 第二列最大显示数量
     */
    public static final String SECOND_COLUMN_SHOW_MAX = "second_column_show_max";
    /**
     * 第二列最小显示数量
     */
    public static final String SECOND_COLUMN_SHOW_MIN = "second_column_show_min";
    /**
     * 第一列默认显示数据
     */
    public static final String FIRST_COLUMN_SHOW_DEFAULT = "first_column_show_default";
    /**
     * //第二列默认显示数据
     */
    public static final String SECOND_COLUMN_SHOW_DEFAULT = "second_column_show_default";
    /**
     * 返回第一列选择数据
     */
    public static final String FIRST_COLUMN_CHOOSE_RESULT = "first_column_choose_result";
    /**
     * 返回第一列选择数据
     */
    public static final String SECOND_COLUMN_CHOOSE_RESULT = "second_column_choose_result";

    public static final String[] GENDER = {"男","女"};

}
