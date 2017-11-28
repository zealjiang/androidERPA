package com.jzg.erp.appraiser.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.activity.ChooseSellerActivity;
import com.jzg.erp.appraiser.model.Seller;
import com.jzg.erp.event.PriceInfoActivityEvent;
import com.jzg.erp.global.IntentKey;
import com.jzg.erp.model.SubmitParamWrapper;
import com.jzg.erp.utils.MyToast;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class CurrEvaluateFragment extends Fragment {

    @Bind(R.id.etStartPrice)
    EditText etStartPrice;
    @Bind(R.id.etEndPrice)
    EditText etEndPrice;
    @Bind(R.id.etExtraInfo)
    EditText etExtraInfo;
    @Bind(R.id.tvChooseSeller)
    TextView tvChooseSeller;
    private Seller.DataBean seller;
    private String price;

    public CurrEvaluateFragment() {
    }

    @SuppressLint("ValidFragment")
    public CurrEvaluateFragment(String price) {
        this.price = price;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_curr_evaluate, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RxTextView.textChangeEvents(etStartPrice)
                .debounce(300, TimeUnit.MILLISECONDS)
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
                                    etStartPrice.setText(result);
                                    etStartPrice.setSelection(result.length());
                                }
                            }else{
                                if(keyword.length()>5){
                                    String result = keyword.substring(0, 5);
                                    etStartPrice.setText(result);
                                    etStartPrice.setSelection(result.length());
                                }
                            }
                        }
                    }
                });

        RxTextView.textChangeEvents(etEndPrice)
                .debounce(300, TimeUnit.MILLISECONDS)
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
                                    etEndPrice.setText(result);
                                    etEndPrice.setSelection(result.length());
                                }
                            }else{
                                if(keyword.length()>5){
                                    String result = keyword.substring(0, 5);
                                    etStartPrice.setText(result);
                                    etStartPrice.setSelection(result.length());
                                }
                            }
                        }
                    }
                });
        SubmitParamWrapper.PriceEvaluation priceInfo = JzgApp.getSubmitParam().getPriceEvaluation();
        if(priceInfo!=null){
            if(!TextUtils.isEmpty(priceInfo.getSaleName()) && priceInfo.getSaleID()>0){
                seller = new Seller.DataBean(priceInfo.getSaleName(),priceInfo.getSaleID());
                tvChooseSeller.setText(priceInfo.getSaleName());
            }
            etStartPrice.setText(String.valueOf(priceInfo.getPingguPriceMin()));
            etEndPrice.setText(String.valueOf(priceInfo.getPingguPriceMax()));
            etExtraInfo.setText(priceInfo.getRemark());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK &&requestCode==IntentKey.REQ_CODE_SELECT_SELLER&& data!=null){
            seller = (Seller.DataBean) data.getSerializableExtra("seller");
            if(seller!=null){
                tvChooseSeller.setText(seller.getName());
            }
        }
    }

    @OnClick({R.id.tvChooseSeller, R.id.btnSave})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvChooseSeller:
                Intent intent = new Intent(getActivity(), ChooseSellerActivity.class);
                startActivityForResult(intent, IntentKey.REQ_CODE_SELECT_SELLER);
                break;
            case R.id.btnSave:
                checkInput();
                break;
        }
    }

    private void checkInput(){
        String startPrice = etStartPrice.getText().toString().trim();
        String endPrice = etEndPrice.getText().toString().trim();
        String extraInfo = etExtraInfo.getText().toString().trim();
        if(TextUtils.isEmpty(endPrice) || TextUtils.equals("0",endPrice)){
            MyToast.showShort("请填写评估价格");
            return;
        }
        if(TextUtils.isEmpty(extraInfo) ){
            MyToast.showShort("请填写评估说明");
            return;
        }
        Float min = Float.valueOf(startPrice);
        Float max = Float.valueOf(endPrice);
        if(min>max){
            MyToast.showShort("最低估价不能高于最高估价");
            return;
        }
        String sellerName = "";
        int sellerId = 0;
        SubmitParamWrapper.PriceEvaluation data = null;
        if(seller!=null){
            sellerName = seller.getName();
            sellerId = seller.getId();
        }
        data = new SubmitParamWrapper.PriceEvaluation(min,max,extraInfo,sellerId,sellerName);
        data.setReferPrice(price);
        JzgApp.getSubmitParam().setPriceEvaluation(data);

        PriceInfoActivityEvent priceInfoActivityEvent = new PriceInfoActivityEvent();
        priceInfoActivityEvent.setFinish(true);
        EventBus.getDefault().post(priceInfoActivityEvent);


    }

}
