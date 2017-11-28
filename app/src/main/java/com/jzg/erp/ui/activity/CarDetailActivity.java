package com.jzg.erp.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.jzg.erp.R;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.global.Constant;
import com.jzg.erp.utils.MyToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: guochen
 * date: 2016/6/28 14:30
 * email:
 */
public class CarDetailActivity extends BaseActivity {
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.psb)
    ProgressBar psb;
    @Bind(R.id.ll_pipei)
    LinearLayout llPipei;
    @Bind(R.id.ll_guanzhu)
    LinearLayout llGuanzhu;
    @Bind(R.id.ll_button)
    LinearLayout llButton;
    private String makeName;
    private String carSourceId;
    private String carId;
    private Boolean isShowBtn;  //true显示button false 显示
    private String modeName;
    private String carDetailPath;
    private String carStatus;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_cardetail);
        ButterKnife.bind(this);
        makeName = getIntent().getStringExtra(Constant.MAKE_NAME);
        modeName = getIntent().getStringExtra(Constant.MODE_NAME);
        isShowBtn = getIntent().getBooleanExtra(Constant.ISSHOWBUTTON, true);
        carStatus = getIntent().getStringExtra(Constant.CARSTATUS);
        carSourceId = getIntent().getStringExtra(Constant.CARSOURCEID);
        carId = getIntent().getStringExtra(Constant.CARID);
        carDetailPath = getIntent().getStringExtra(Constant.CARDETAILPATH);
        if (isShowBtn) {
            llButton.setVisibility(View.VISIBLE);
        } else {
            llButton.setVisibility(View.GONE);
        }

    }

    @Override
    protected void setData() {
        setTitle(Constant.CARDETAIL);
        //设置javascript支持
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(carDetailPath);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                if (psb == null)
                    return;
                psb.setVisibility(View.VISIBLE);
            }
        });

        webview.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (psb == null)
                    return;

                psb.setProgress(newProgress);

                if (newProgress == 100) {
                    psb.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack(); //goBack()表示返回webView的上一页面
        } else {
            super.onBackPressed();
        }
    }


    @OnClick({R.id.ll_pipei, R.id.ll_guanzhu})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.ll_pipei:
                if(!TextUtils.isEmpty(carStatus) && TextUtils.equals("3",carStatus)){
                    MyToast.showShort("已售出车辆无法匹配客户!");
                    return;
                }
                Intent intent = new Intent(this, MatchCustomerActivity.class);
                if (!TextUtils.isEmpty(makeName)) {
                    intent.putExtra(Constant.MAKE_NAME, makeName);
                    intent.putExtra(Constant.MODE_NAME, modeName);
                }
                startActivity(intent);
                break;
            case R.id.ll_guanzhu:
                if(!TextUtils.isEmpty(carStatus) && TextUtils.equals("3",carStatus)){
                    MyToast.showShort("已售出车辆无法关注!");
                    return;
                }
                Intent intent1 = new Intent(this, ChooseCustomerActivity.class);//关注
                intent1.putExtra(Constant.CARSOURCEID, carSourceId);
                intent1.putExtra(Constant.CARID, carId);
                intent1.putExtra(Constant.MAKE_NAME, makeName);
                startActivity(intent1);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
