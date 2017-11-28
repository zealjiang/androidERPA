package com.jzg.erp.appraiser.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.CarCondOptionsModel;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.model.SubmitParamWrapper;
import com.jzg.erp.utils.ACache;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 工况(车况)
 *
 * @author zealjiang
 * @time 2016/8/15 10:02
 */
public class CarConditionActivity extends BaseActivity {


    @Bind(R.id.rb_intact)
    RadioButton rbIntact;
    @Bind(R.id.rb_poor)
    RadioButton rbPoor;
    @Bind(R.id.et_poor)
    EditText etPoor;
    @Bind(R.id.rb_cannot_start)
    RadioButton rbCannotStart;
    @Bind(R.id.et_cannot_start)
    EditText etCannotStart;
    @Bind(R.id.radioGroup_gongkuang)
    RadioGroup radioGroupGongkuang;
    private Context mContext;

    private final String INTACT = "1";
    private final String POOR = "2";
    private final String CONNOT_START = "3";

    private String selectId = "";

    //选项
    private CarCondOptionsModel carCondOptionsModel;
    private List<CarCondOptionsModel.DataBean.ItemBean> listOptions;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_vehicle_condition);
        ButterKnife.bind(this);
        mContext = this.getApplicationContext();
        carCondOptionsModel = (CarCondOptionsModel) ACache.get(JzgApp.getAppContext()).getAsObject("carCondOptionsModel");
        init();
    }

    @Override
    protected void setData() {
        setTitle("工况");
        setTextRight("保存");

        mTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    public void init() {
        //工况
        listOptions = carCondOptionsModel.getData().getKey186().get(0).getOption();
        etPoor.setEnabled(false);
        etCannotStart.setEnabled(false);

        //设置上次保存的值
        SubmitParamWrapper.CarCondition carCondition = JzgApp.getAppContext().getSubmitParam().getCarCondition();
        if(null!=carCondition&&null!=carCondition.getData()){
            List<String> listString =  carCondition.getData();
            for (int i = 0; i < listString.size(); i++) {
                String[] idValue = listString.get(i).split(",");

                for (int j = 0; j < listOptions.size(); j++) {
                    if(String.valueOf(listOptions.get(j).getId()).equals(idValue[0])){

                        switch (j) {
                            case 0:
                                rbIntact.setChecked(true);
                                enabled01();
                                selectId = INTACT;
                                break;
                            case 1:
                                rbPoor.setChecked(true);
                                if(idValue.length==1){
                                    etPoor.setText("");
                                }else{
                                    etPoor.setText(idValue[1]);
                                }
                                enabled02();
                                selectId = POOR;
                                break;
                            case 2:
                                rbCannotStart.setChecked(true);
                                if(idValue.length==1){
                                    etCannotStart.setText("");
                                }else{
                                    etCannotStart.setText(idValue[1]);
                                }
                                enabled03();
                                selectId = CONNOT_START;
                                break;
                            default:
                                break;
                        }

                    }
                }

            }
        }

        radioGroupGongkuang
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_intact:
                                selectId = INTACT;
                                enabled01();
                                break;
                            case R.id.rb_poor:
                                selectId = POOR;
                                enabled02();
                                break;
                            case R.id.rb_cannot_start:
                                selectId = CONNOT_START;
                                enabled03();
                                break;
                            default:
                                break;
                        }
                    }
                });

    }


    protected void enabled01() {
        etPoor.setText("");
        etCannotStart.setText("");
        etPoor.setEnabled(false);
        etCannotStart.setEnabled(false);
    }

    protected void enabled02() {
        etPoor.setEnabled(true);
        etCannotStart.setText("");
        etCannotStart.setEnabled(false);
        showSoftKeyboard(etPoor);
    }

    protected void enabled03() {
        etPoor.setText("");
        etPoor.setEnabled(false);
        etCannotStart.setEnabled(true);
        showSoftKeyboard(etCannotStart);
    }

    /**
     * 弹出软键盘
     * @author zealjiang
     * @time 2016/8/16 10:28
     */
    private void showSoftKeyboard(EditText  editText){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }

    public void save() {

        SubmitParamWrapper.CarCondition carCondition = JzgApp.getAppContext().getSubmitParam().getCarCondition();
        if(null==carCondition){
            carCondition = new SubmitParamWrapper.CarCondition();
            JzgApp.getAppContext().getSubmitParam().setCarCondition(carCondition);
        }
        List<String> list = carCondition.getData();
        if(null==list){
            list = new ArrayList<String>();
            carCondition.setData(list);
        }

        if(list.size()==0) {
            //判断哪个被选中
            if(selectId == INTACT){
                list.add(listOptions.get(0).getId() + ","+"");
            }else if(selectId == POOR){
                list.add(listOptions.get(1).getId() + ","+etPoor.getText().toString());
            }else if(selectId == CONNOT_START){
                list.add(listOptions.get(2).getId() + ","+etCannotStart.getText().toString());
            }
        }else{
            //将工况中的值加入到list中
            for (int j = 0; j < listOptions.size(); j++) {
                String mListId = listOptions.get(j).getId()+"";
                for (int i = 0; i < list.size(); i++) {
                    String[] idValue = list.get(i).split(",");
                    String listId = idValue[0];

                    if(mListId.equals(listId)){
                        list.remove(i);
                    }
                }
            }
            //判断哪个被选中
            if(selectId == INTACT){
                list.add(listOptions.get(0).getId() + ","+"");
            }else if(selectId == POOR){
                list.add(listOptions.get(1).getId() + ","+etPoor.getText().toString());
            }else if(selectId == CONNOT_START){
                list.add(listOptions.get(2).getId() + ","+etCannotStart.getText().toString());
            }

        }
        carCondition.setData(list);

        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
