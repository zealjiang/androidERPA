package com.jzg.erp.http;


import com.jzg.erp.appraiser.model.BaseInfoModel;
import com.jzg.erp.appraiser.model.CarAllDataModel;
import com.jzg.erp.appraiser.model.CarCondInfoShowModel;
import com.jzg.erp.appraiser.model.CarCondOptionsModel;
import com.jzg.erp.appraiser.model.CarEvaluateInfo;
import com.jzg.erp.appraiser.model.CarInfoModel;
import com.jzg.erp.appraiser.model.CarPhoto;
import com.jzg.erp.appraiser.model.ConfigInfoModel;
import com.jzg.erp.appraiser.model.EVCarList;
import com.jzg.erp.appraiser.model.EvalStatusListModel;
import com.jzg.erp.appraiser.model.EvaluateHistory;
import com.jzg.erp.appraiser.model.EvaluatePrice;
import com.jzg.erp.appraiser.model.HistoryEvaListModel;
import com.jzg.erp.appraiser.model.OnlineSale;
import com.jzg.erp.appraiser.model.PGOrderDetailModel;
import com.jzg.erp.appraiser.model.PGOrderInfoModel;
import com.jzg.erp.appraiser.model.PGOrderModel;
import com.jzg.erp.appraiser.model.PGPersonModel;
import com.jzg.erp.appraiser.model.ParamOption;
import com.jzg.erp.appraiser.model.PingguCustomer;
import com.jzg.erp.appraiser.model.ProcedureInfoModel;
import com.jzg.erp.appraiser.model.ProvinceCity;
import com.jzg.erp.appraiser.model.Seller;
import com.jzg.erp.appraiser.model.TradeHistory;
import com.jzg.erp.appraiser.model.TradeRecord;
import com.jzg.erp.appraiser.model.UploadResult;
import com.jzg.erp.base.BaseObject;
import com.jzg.erp.model.AccountInf;
import com.jzg.erp.model.BuyCarIntent;
import com.jzg.erp.model.CarSourceData;
import com.jzg.erp.model.CarSourceTagData;
import com.jzg.erp.model.CustomerData;
import com.jzg.erp.model.CustomerDetail;
import com.jzg.erp.model.CustomerInfo;
import com.jzg.erp.model.MatchCustomerData;
import com.jzg.erp.model.TaskItemGroupWrapper;
import com.jzg.erp.model.UserWrapper;
import com.jzg.erp.model.WaitingItemWrapper;
import com.jzg.erp.update.UpdateApp;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;


/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/6/7 10:26
 * @desc:ERP本App相关接口
 */
public interface ApiServer {

//    public  static final String BASE_URL="http://192.168.0.140:8888";
//    public  static final String BASE_URL="http://jtdapp.sandbox.guchewang.com";

    public static final String BASE_URL = "http://192.168.0.140:9001";

    //---------评估师端相关的接口--------------
    @FormUrlEncoded
    @POST("/ProcessOptions/GetOptions.ashx") //1、获取所有 Options 数据
    public Observable<ParamOption> getParamOptions(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/Assessment/HistoryQuery.ashx")//2、获取历史评估单
    public Observable<HistoryEvaListModel> getHistoryEvalList(@FieldMap Map<String, String> params);

    //3、获取门店选项数据。这个接口在（1、获取所有 Options 数据）里已经有

    @FormUrlEncoded
    @POST("/Assessment/CreateNew.ashx") //4、获取单个车辆信息和车辆评估信息，根据 VIN 码
    public Observable<CarInfoModel> queryCarInfoByVin(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/Assessment/CreateNew.ashx") //5、创建评估单 op=add
    public Observable<ParamOption> addEvalOrder(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/Assessment/GetPrices.ashx") //6、获取价格信息 styleId=111670
    public Observable<ParamOption> getPrices(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/Assessment/HistoryQuery.ashx") //7、获取车辆成交历史记录 op=orderListByModel&modelId=2591
    public Observable<TradeHistory> getTradeHistory(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/Assessment/HistoryQuery.ashx") //8、获取车辆评估历史记录  op=listByCar&pageindex=1&pagesize=10&carId=3482
    public Observable<EvaluateHistory> getEvaluateHistory(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/Assessment/DelPutPrice.ashx") //9、删除评估单 op=delete&pingguid=100&status=1&sign=xxx
    public Observable<ParamOption> delEvaluateOrder(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/Assessment/DelPutPrice.ashx") //10、收购信息 op= putprice&pingguid=111&equippedprice=100&purchaseprice=100&sign=xxx
    public Observable<ParamOption> buyInfo(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/ProcessOptions/GetOptions.ashx") //查询省市区 op=areacity&provinceId=22
    public Observable<ProvinceCity> getProvinceCity(@FieldMap Map<String,String> params);


    @FormUrlEncoded
    @POST("/ProcessOptions/GetOptions.ashx")//13、获取历史评估单状态 选项数据
    public Observable<EvalStatusListModel> getHistoryEvalStatus(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/Account/UserAccount.ashx") //14、登录
    public Observable<UserWrapper> login(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //15、车辆  op=CarSource&userId=155&pageIndex=1&pageSize=100
    public Observable<EVCarList> getCarList(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //16、车型  op=StyleName&userId=155&pageIndex=1&pageSize=100&styleName= 2.0L 自动 ST
    public Observable<EVCarList> getCarListByStyleName(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //17、车牌号  op=LicensePlate&license=B12313&userId=155
    public Observable<EVCarList> getCarListByLicense(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //18、手机号  op=Phone&userId=155&telphone=13465435668
    public Observable<EVCarList> getCarListByPhone(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //19、VIN   op=VIN&vinCode=HJK20160813000004&userid=155
    public Observable<EVCarList> getCarListByVin(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //20、基本信息op=CarbaseInfo&id=3501
    public Observable<BaseInfoModel> getBaseInfo(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //21、配置信息op=CarConfigInfo&id=3501
    public Observable<ConfigInfoModel> getConfigInfo(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //22、手续信息  op=ProcedureInfo&id=3501
    public Observable<ProcedureInfoModel> getProcedureInfo(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //23、评估单  op=PGOrder&id=3501
    public Observable<PGOrderModel> getPGOrder(@FieldMap Map<String,String> params);


    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //24、根据评估单单号找出对应的评估人、评估单状态、车况等级   op=PGOrderNo&pingguNo=A201608130005
    public Observable<PGOrderInfoModel> getPGOrderInfo(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //25、点击姓名获取评估人的信息
    public Observable<PGPersonModel> getPGPersonInfo(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //26、点击估单的状态获取评估单的详情 op=PGOrderNoDetail&pingguNo=A201608140021&requestStatus=3
    public Observable<PGOrderDetailModel> getPGOrderDetail(@FieldMap Map<String,String> params);


    @FormUrlEncoded
    @POST("/carinfo/carvaluation.ashx") //29、获取近 45 天同车系历史成交记录
    public Observable<TradeRecord> getTradeRecord(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/carinfo/carvaluation.ashx") //30、获取近 45 天同车系网络在售参考
    public Observable<OnlineSale> getOnlineSale(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/carinfo/CarValuation.ashx") //31[15]、获取车辆价格
    public Observable<CarEvaluateInfo> carValuatePrice(@FieldMap Map<String,String> params);
    @Multipart
    @POST("/Assessment/CreateNew.ashx") //32[14]上传评估车辆图片
    public Observable<UploadResult> uploadPhoto(@PartMap Map<String,RequestBody> params);


    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //33、 根据评估单中的 CarInfoId 来获车的图片： ( 图片 )
    public Observable<CarPhoto> getCarPhoto(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //33、 根据评估单中的 CarInfoId 来获车的图片： ( 图片 )
    public Observable<EvaluatePrice> getEvaluatePrice(@FieldMap Map<String,String> params);


    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //33、
    public Observable<PingguCustomer> getCustomerInfo(@FieldMap Map<String,String> params);



    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //33[32]根据车辆列表中的 CarInfoId 来获取车况： ( 车况 )
    public Observable<CarCondInfoShowModel> getCarConditionInfo(@FieldMap Map<String,String> params);


    @FormUrlEncoded
    @POST("/Assessment/CreateNew.ashx") //获取所有车况检测的选项数据
    public Observable<CarCondOptionsModel> getCarCondOptions(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/ProcessOptions/GetOptions.ashx") //43、获取销售 Opc tion 数据，根据店铺
    public Observable<Seller> getStoreSellers(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx") //  36删除
    public Observable<BaseObject> deleteOrder(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/Assessment/CreateNew.ashx") //通过车型查询车辆配置
    public Observable<CarInfoModel> getConfigInfoBySeries(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/Assessment/CreateNew.ashx") //5,创建评估单
    public Observable<BaseObject> submitEvaluate(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/Assessment/CreateNew.ashx") //44,获取车辆的所有信息
    public Observable<CarAllDataModel> getCarAllData(@FieldMap Map<String,String> params);
    //---------评估师端相关的接口-----end---------

    /*** 以下是销售端相关的接口*/
    @FormUrlEncoded
    @POST("/Account/UserAccount.ashx") //帐户信息
    public Observable<AccountInf> getAccountInf(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/customer/todolist.ashx")//车源列表
    public Observable<CarSourceData> loadCarSource(@FieldMap Map<String, String> params);

    @Multipart
    @POST("/Account/UserAccount.ashx?") //上传头像
    public Observable<AccountInf> updateImage(@PartMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST("/Account/UserAccount.ashx") //修改密码
    public Observable<BaseObject> changePwd(@FieldMap Map<String,String> params);


    @FormUrlEncoded
    @POST("/Customer/ToDoList.ashx") //客户信息详情
    public Observable<CustomerDetail> getCustomerDetail(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/Customer/ToDoList.ashx") //修改购车意向
    public Observable<BuyCarIntent> modifyBuyCarIntent(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/Customer/ToDoList.ashx") //获取关注车辆列表
    public Observable<CarSourceData> getFocusCarList(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST("/Customer/ToDoList.ashx") //获取未关注车辆的用户
    public Observable<CarSourceData> getIntendCustomerBySellerIDAndCarID(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST("/Customer/ToDoList.ashx") //获取带关注车辆列表
    public Observable<CarSourceTagData> getFocusTagCarList(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST("/Customer/ToDoList.ashx") //客户列表
    public Observable<CustomerInfo> getCustomerList(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST("/Customer/ToDoList.ashx") //获取关注此车辆的用户列表
    public Observable<CustomerData> getChooseCustomer(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST("/Customer/ToDoList.ashx") //批量添加关注此车辆的客户
    public Observable<CustomerData> addChooseCustomer(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST("/customer/todolist.ashx")//车源模糊搜索
    public Observable<CarSourceData> loadSearchDatas(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST("/followup/AddFollowUpLog.ashx")//添加跟进记录
    public Observable<BaseObject> addFollowUpLog(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST("/followup/AddFollowUpItem.ashx")//添加跟进记录
    public Observable<BaseObject> addFollowUpItem(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST("/followup/GetCarConcernUserList.ashx")//匹配客户
    public Observable<MatchCustomerData> loadMatchCustomer(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST("/followup/GetFollowUpList.ashx")//获取跟进记录
    public Observable<TaskItemGroupWrapper> getFollowUpList(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/followup/GetFollowUpItemList.ashx")//获取跟进事项
    public Observable<TaskItemGroupWrapper> getFollowUpItemList(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/customer/todolist.ashx")//插入通话记录
    public Observable<BaseObject> insertDialRecord(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/followup/GetWaitDoneItems.ashx")//获取待办事项
    public Observable<WaitingItemWrapper> getWaitDoneItems(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/followup/GetWaitDoneItems.ashx")//改变待办事项的查看状态
    public Observable<BaseObject> changeTaskItemStatus(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST("/APPUpdate/APPUpdateVersion.ashx")//改变待办事项的查看状态
    public Observable<UpdateApp> isUpdate(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/PGCar/GetCarList.ashx")//35 、 收购
    public Observable<BaseObject> buCar(@FieldMap Map<String, String> params);


}
