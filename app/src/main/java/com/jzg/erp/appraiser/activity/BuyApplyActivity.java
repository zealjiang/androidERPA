package com.jzg.erp.appraiser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.base.BaseObject;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.utils.NetworkExceptionUtils;
import com.jzg.erp.utils.UIUtils;
import com.jzg.erp.widget.ClearableEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BuyApplyActivity extends BaseActivity {

    @Bind(R.id.etBuyPrice)
    ClearableEditText etBuyPrice;
    @Bind(R.id.etZbPrice)
    ClearableEditText etZbPrice;
    private String pingguNo;
    private String carId;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_buy_apply);
        ButterKnife.bind(this);
        pingguNo = getIntent().getStringExtra("pingguNo");
        carId = getIntent().getStringExtra("carId");

        RxTextView.textChangeEvents(etBuyPrice)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TextViewTextChangeEvent>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(TextViewTextChangeEvent onTextChangeEvent) {
                        String keyword = onTextChangeEvent.text().toString();
                        if (!TextUtils.isEmpty(keyword)) {
                            if(keyword.contains(".")){
                                String s1 = keyword.substring(keyword.indexOf(".")+1,keyword.length());
                                if(s1.length()>2){
                                    String result = keyword.substring(0, keyword.indexOf(".")+3);
                                    etBuyPrice.setText(result);
                                    etBuyPrice.setSelection(result.length());
                                }
                            }else{
                                if(keyword.length()>5){
                                    String result = keyword.substring(0, 5);
                                    etBuyPrice.setText(result);
                                    etBuyPrice.setSelection(result.length());
                                }
                            }
                        }
                    }
                });
    }

    @Override
    protected void setData() {
        setTitle("收购信息录入");
        setTextRight("保存");
    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                buyCar();
                break;
        }
    }

    private void buyCar(){
        String buyPrice = etBuyPrice.getText().toString().trim();
        String zbPrice = etZbPrice.getText().toString().trim();
        if(TextUtils.isEmpty(buyPrice)){
            MyToast.showShort("请填写收购价格");
            return;
        }
        if(TextUtils.isEmpty(zbPrice)){
            MyToast.showShort("请填写预计整备费");
            return;
        }
        showDialog();
        Map<String,String> params = new HashMap<>();
        params.put("op","Putprice");
        params.put("pingguNo",pingguNo);
        params.put("id",carId);
        params.put("purchasePrice",buyPrice);
        params.put("equippedPrice",zbPrice);
        params = MD5Utils.encryptParams(params);
        LogUtil.i(TAG, UIUtils.getUrl(params));
        JzgApp.getApiServer().buCar(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<BaseObject>() {
            @Override
            public void onCompleted() {
                dismissDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissDialog();
                String error = NetworkExceptionUtils.getErrorByException(e);
                MyToast.showShort(error);
            }

            @Override
            public void onNext(BaseObject baseObject) {
                if(baseObject.getStatus()==1){
                    MyToast.showShort("收购已提交");
                    Intent intent = getIntent();
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    onError(new RuntimeException(baseObject.getMessage()));
                }

            }
        });
    }
}
