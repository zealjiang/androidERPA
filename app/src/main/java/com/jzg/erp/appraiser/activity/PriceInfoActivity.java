package com.jzg.erp.appraiser.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.fragment.BigDataFragment;
import com.jzg.erp.appraiser.fragment.CurrEvaluateFragment;
import com.jzg.erp.appraiser.fragment.HistoryFragment;
import com.jzg.erp.appraiser.model.CarEvaluateInfo;
import com.jzg.erp.appraiser.presenter.EvaluateCarPricePresenter;
import com.jzg.erp.appraiser.view.IEvaluatePrice;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.event.PriceInfoActivityEvent;
import com.jzg.erp.utils.DateTimeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PriceInfoActivity extends BaseActivity implements IEvaluatePrice {
    private static final int CURR_PRICE = 0;//本次评估价格
    private static final int HISTORY = 1;//历史记录
    private static final int BIG_DATA = 2;//精真估大数据
    @Bind(R.id.ivCar)
    SimpleDraweeView ivCar;
    @Bind(R.id.tvCarFullName)
    TextView tvCarFullName;
    @Bind(R.id.tvGuidePrice)
    TextView tvGuidePrice;
    @Bind(R.id.tvEmissionStandard)
    TextView tvEmissionStandard;
    @Bind(R.id.tvNewCarPrice)
    TextView tvNewCarPrice;
    @Bind(R.id.tvLowerPrice)
    TextView tvLowerPrice;
    @Bind(R.id.tvEvaluatePrice)
    TextView tvEvaluatePrice;
    @Bind(R.id.tvUpperPrice)
    TextView tvUpperPrice;
    @Bind(android.R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private EvaluateCarPricePresenter presenter;
    private String cityId = "201";
    private String carId;
    private String modelId;
    private CarEvaluateInfo info;


    @Override
    public void showData(CarEvaluateInfo info) {
        this.info = info;
        CarEvaluateInfo.Data carInfo = info.getData();
        ivCar.setImageURI(Uri.parse(TextUtils.isEmpty(carInfo.getImageUrl())?"http://image.xcar.com.cn/attachments/a/day_160810/2016081013_74c203d276fcebcc0c0bjaUz5fz0PtVk.jpg":carInfo.getImageUrl()));
        tvCarFullName.setText(carInfo.getCarName());
        if(TextUtils.isEmpty(carInfo.getB2CPrice()+"")){
            tvGuidePrice.setText("厂商指导价 暂无数据");
        }else {
            tvGuidePrice.setText("厂商指导价 "+carInfo.getMsrp()+"万");
        }
        String price_c = carInfo.getPrice_C();
        String price_b = carInfo.getPrice_B();
        String price_a = carInfo.getPrice_A();

        tvLowerPrice.setText(TextUtils.equals("暂无数据",price_c)?"暂无数据": price_c+"万");
        tvEvaluatePrice.setText(TextUtils.equals("暂无数据",price_b)?"暂无数据":price_b +"万");
        tvUpperPrice.setText(TextUtils.equals("暂无数据",price_a)?"暂无数据":price_a +"万");

        if(TextUtils.isEmpty(carInfo.getEnvironmentStandard())){
            tvEmissionStandard.setText("暂无数据");
        }else {
            tvEmissionStandard.setText(carInfo.getEnvironmentStandard());
        }

        if(TextUtils.isEmpty(carInfo.getNewcarpreferentialprice())){
            tvNewCarPrice.setText("暂无数据");
        }else {
            tvNewCarPrice.setText(carInfo.getNewcarpreferentialprice()+"万");
        }

        //初始化Fragment
        List<CarEvaluateInfo.Data.KeyValueItem> carstock = carInfo.getCarstock();
        List<CarEvaluateInfo.Data.KeyValueItem> tradepricetendencyresp = carInfo.getTradepricetendencyresp();
        fillData(carstock,tradepricetendencyresp);

    }

    @Override
    public void showError(String error) {
        super.showError(error);
        List<CarEvaluateInfo.Data.KeyValueItem> carstock = new ArrayList<>();
        List<CarEvaluateInfo.Data.KeyValueItem> tradepricetendencyresp = new ArrayList<>();
        fillData(carstock,tradepricetendencyresp);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_price_info);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        presenter = new EvaluateCarPricePresenter(this);

        if(null==JzgApp.getSubmitParam().getCarInfo()){
            this.finish();
            return;
        }
        if(null==JzgApp.getSubmitParam().getCarInfo().getCarBaseInfo()){
            this.finish();
            return;
        }
        carId = JzgApp.getSubmitParam().getCarInfo().getCarBaseInfo().getCarId()+"";
        String styleId = JzgApp.getSubmitParam().getCarInfo().getCarBaseInfo().getStyleId();
        modelId = JzgApp.getSubmitParam().getCarInfo().getCarBaseInfo().getSeriesId();
        String mileage = JzgApp.getSubmitParam().getCarInfo().getCarBaseInfo().getMileage();
        String regDate = JzgApp.getSubmitParam().getCarInfo().getCarBaseInfo().getFirstRegDate();
        presenter.evaluateCarPrice(styleId,cityId,mileage, DateTimeUtils.formatMillsStr(regDate,DateTimeUtils.YYYYMMDD));
    }

    @Override
    protected void setData() {
        setTitle("价格信息");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void fillData(final List<CarEvaluateInfo.Data.KeyValueItem> carstock, final List<CarEvaluateInfo.Data.KeyValueItem> tradepricetendencyresp){
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case CURR_PRICE:
                        String price = info.getData().getPrice_B();
                        return new CurrEvaluateFragment(TextUtils.isEmpty(price)?"暂无数据":price);
                    case HISTORY:
                        return new HistoryFragment(modelId,carId);
                    case BIG_DATA:
                        return new BigDataFragment(carstock,tradepricetendencyresp);
                    default:
                        return new CurrEvaluateFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case CURR_PRICE:
                        return "本次评估价格";
                    case HISTORY:
                        return "历史评估记录";
                    case BIG_DATA:
                        return "精真估大数据";
                    default:
                        return "本次评估价格";
                }
            }
        });
        tabs.setupWithViewPager(viewPager);
    }

    @Subscribe
    public void onFinishPriceInfoActy(PriceInfoActivityEvent priceInfoActivityEvent) {
        if(priceInfoActivityEvent.isFinish()){
            finish();
        }
    }

    @OnClick(R.id.ivLeft)
    public void onClick() {
        finish();
    }

}
