package com.jzg.erp.appraiser.activity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.adapter.GridPhotoAdapter;
import com.jzg.erp.appraiser.model.UploadResult;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.global.Constant;
import com.jzg.erp.model.SubmitParamWrapper;
import com.jzg.erp.utils.FrescoImageLoader;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.widget.FullyGridLayoutManager;
import com.jzg.erp.widget.GridSpacingItemDecoration;
import com.jzg.erp.widget.MActionSheet;
import com.jzg.erp.widget.crop.FileUtilTool;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UploadPhotoActivity extends BaseActivity implements OnItemClickListener {

    @Bind(R.id.rvPhoto)
    RecyclerView rvPhoto;
    private static final String[] MENUS = {"从相册选取", "拍照"};
    private final int REQUEST_CODE_CAMERA = 1000;
    private static final int PHOTO_PERMISSION = 1;
    private FunctionConfig functionConfig;

    private List<SubmitParamWrapper.PhotoItem> photoItems;
    private GridPhotoAdapter adapter;
    private int currPhotoPos = -1;
    private static final int PICK_GALLERY = 5;
    private static final int CROP_PHOTO = 7;
    private static final int INPUT_DESC = 6;
    private static final String IMAGE_UNSPECIFIED = "image/*";
    private String imgPath;

    private static final String[] IMGS = {"res:///" + R.mipmap.img_001, "res:///" + R.mipmap.img_002, "res:///" + R.mipmap.img_003, "res:///" + R.mipmap.img_004, "res:///" + R.mipmap.img_005, "res:///" + R.mipmap.img_06};
    private static final String[] IMG_DECS = {"左前45°带号牌", "仪表盘公里数", "车身车架号", "出厂铭牌", "行驶证", "添加更多"};
    private static final int[] IDS = {349, 350, 351, 352, 354, 0};


    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_upload_photo);
        ButterKnife.bind(this);
        rvPhoto.setLayoutManager(new FullyGridLayoutManager(this, 3));
        rvPhoto.addItemDecoration(new GridSpacingItemDecoration(3, 16, false));
        initGalleryFinal();
    }

    private void init() {
        photoItems = new ArrayList<>();
        List<SubmitParamWrapper.PhotoItem> cacheList = JzgApp.getSubmitParam().getPhotoItems();//缓存中的照片
        for (int i = 0; i < IMGS.length; i++) {
            SubmitParamWrapper.PhotoItem item = new SubmitParamWrapper.PhotoItem(IDS[i], "", IMGS[i], IMG_DECS[i]);
            photoItems.add(item);
        }
        if (cacheList != null && cacheList.size() > 0) {
            photoItems.remove(photoItems.size() - 1);
            for (int i = 0; i < cacheList.size(); i++) {
                SubmitParamWrapper.PhotoItem item = cacheList.get(i);
                int dictID = item.getDictID();
                if (dictID != 353) {
                    switch (dictID) {
                        case 349:
                            item.setImgText(IMG_DECS[0]);
                            photoItems.set(0, item);
                            break;
                        case 350:
                            item.setImgText(IMG_DECS[1]);
                            photoItems.set(1, item);
                            break;
                        case 351:
                            item.setImgText(IMG_DECS[2]);
                            photoItems.set(2, item);
                            break;
                        case 352:
                            item.setImgText(IMG_DECS[3]);
                            photoItems.set(3, item);
                            break;
                        case 354:
                            item.setImgText(IMG_DECS[4]);
                            photoItems.set(4, item);
                            break;
                    }
                } else {
                    photoItems.add(item);
                }
            }
            if (photoItems.size() < 30) {
                SubmitParamWrapper.PhotoItem item = new SubmitParamWrapper.PhotoItem(IDS[5], "", IMGS[5], IMG_DECS[5]);
                photoItems.add(item);
            }
        }
        adapter = new GridPhotoAdapter(this, R.layout.item_rv_photo, photoItems);
        adapter.setShowDelete(true);
        rvPhoto.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void setData() {
        setTitle(getString(R.string.upload_car_photo));
        init();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            LogUtil.e(TAG,"点击实体返回键");
            save();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop() {
        super.onStop();
        save();//退出activity的时候保存到缓存中
    }
    @OnClick({R.id.ivLeft, R.id.tvLeft})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                LogUtil.e(TAG,"点击tigle上的返回按钮");
                save();
                break;
            case R.id.tvLeft:
                break;
        }
        super.onClick(view);
    }

    private void save() {
        List<SubmitParamWrapper.PhotoItem> resultList = new ArrayList<>();
        for (SubmitParamWrapper.PhotoItem item : photoItems) {
            if (!TextUtils.isEmpty(item.getViewUrl()) && item.getViewUrl().startsWith("http")) {//只保留已上传的图片
                resultList.add(item);
            }
        }
        JzgApp.getAppContext().getSubmitParam().setPhotoItems(resultList);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @PermissionGrant(PHOTO_PERMISSION)
    public void permissionSucceed() {
        GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, mOnHanlderResultCallback);
    }

    @PermissionDenied(PHOTO_PERMISSION)
    public void permissionFailed() {
        MyToast.showShort("授权被拒!");
    }

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null && resultList.size() > 0) {
                imgPath = resultList.get(0).getPhotoPath();
                LogUtil.e(TAG, "imgPath=" + imgPath);
                try {
                    Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), imgPath, null, null));
                    int angle = getExifOrientation(imgPath);
                    LogUtil.e(TAG, "拍照角度------" + angle);
                    Intent intent = new Intent(UploadPhotoActivity.this, CropImageActivity.class);
                    intent.setData(uri);
                    intent.putExtra("Angle", angle);
                    intent.putExtra("imgPath", imgPath);
                    startActivityForResult(intent, CROP_PHOTO);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            MyToast.showShort(errorMsg);
        }
    };

    private void initGalleryFinal() {
        FunctionConfig.Builder builder = new FunctionConfig.Builder().setCropHeight(600).setCropWidth(800).setEnableCamera(true).setForceCrop(true).setEnableCrop(true);
        ImageLoader imageLoader = new FrescoImageLoader(this);
        builder.setEnableCamera(true);
        builder.setEnablePreview(false);
        functionConfig = builder.build();
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageLoader, ThemeConfig.DEFAULT)
                .setTakePhotoFolder(new File(FileUtilTool.SDCARD_PAHT+ FileUtilTool.APPROOT+"/photo_temp/"))
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);

    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        currPhotoPos = position;
        MActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles(MENUS)
                .setCancelableOnTouchOutside(true)
                .setListener(new MActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(MActionSheet actionSheet, boolean isCancel) {
                    }

                    @Override
                    public void onOtherButtonClick(MActionSheet actionSheet, int index) {
                        if (index == 0) {
                            Intent in = new Intent(Intent.ACTION_PICK, null);
                            in.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
                            startActivityForResult(in, PICK_GALLERY);
                        } else if (index == 1) {
                            MPermissions.requestPermissions(UploadPhotoActivity.this, PHOTO_PERMISSION, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        }
                    }
                }).show();
    }


    /**
     * 获取图片拍照角度
     * getExifOrientation
     *
     * @param @param  path
     * @param @return
     * @return int
     * @throws
     * @Title: getExifOrientation
     * @Description:
     */
    public int getExifOrientation(String path) {
        String img_path = path;
        LogUtil.e(TAG, "图片位置:" + img_path);
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(img_path);
        } catch (IOException ex) {
            Log.e("CropImageActivity", "cannot read exif", ex);
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }

            }
        }
        return degree;
    }

    /**
     * 根据Uri获取图片绝对路径 uri2Path
     *
     * @param @param  uri
     * @param @return
     * @return String
     * @throws
     * @Title: uri2Path
     * @Description:
     */
    public String uri2Path(Uri uri) {
        int index;
        String imhgPath;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        imhgPath = cursor.getString(index);
        return imhgPath;
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;
        switch (requestCode) {
            case INPUT_DESC:
                String result = data.getStringExtra(Constant.ACTIVITY_INPUT);
                if (TextUtils.isEmpty(result)) {
                    MyToast.showShort("请填写照片描述");
                    return;
                }
                SubmitParamWrapper.PhotoItem oldData = photoItems.get(currPhotoPos);
                oldData.setImgText(result);
                photoItems.set(currPhotoPos, oldData);
                adapter.notifyDataSetChanged();
                break;
            case PICK_GALLERY:
                // 从相册传递
                Uri uri = data.getData();
                if (uri != null) {
                    String imgpath = uri2Path(uri);
                    LogUtil.e(TAG, "从相册选择的图片地址---" + imgpath);
                    int ro = getExifOrientation(imgpath);
                    LogUtil.e(TAG, "从相册选择的图片角度------" + ro);
                    Intent intent = new Intent(this, CropImageActivity.class);
                    intent.setData(uri);
                    intent.putExtra("Angle", ro);
                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:// 从截取传递
                imgPath = data.getStringExtra("cropImagePath");
                uploadImage(imgPath);
                break;
            default:
                break;
        }

    }


    /**
     * 上传图片
     */
    public void uploadImage(String path) {
        showDialog();
        File file = new File(path);
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), file);
        RequestBody op = RequestBody.create(MediaType.parse("text/plain"), "uploadimage");
        Map<String, RequestBody> params = new HashMap<String, RequestBody>();
        params.put("image\"; filename=\"" + file.getName() + "", fileBody);
        params.put("op", op);
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        RequestBody sign = RequestBody.create(MediaType.parse("text/plain"), MD5Utils.getMD5Sign(signMap));
        params.put("sign", sign);
        JzgApp.getApiServer().uploadPhoto(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<UploadResult>() {
            @Override
            public void onCompleted() {
                dismissDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissDialog();
            }

            @Override
            public void onNext(UploadResult data) {
                if (data.getStatus() == 1) {
                    String viewUrl = data.getData().getViewUrl();
                    String submitVal = data.getData().getSubmitVal();
                    SubmitParamWrapper.PhotoItem newItem = photoItems.get(currPhotoPos);
                    newItem.setPicPath(submitVal);
                    newItem.setViewUrl(viewUrl);
                    if (currPhotoPos < 5) {//默认前五张
                        newItem.setDictID(IDS[currPhotoPos]);
                        newItem.setImgText(IMG_DECS[currPhotoPos]);
                        photoItems.set(currPhotoPos, newItem);
                        adapter.notifyDataSetChanged();
                    } else {
                        newItem.setDictID(353);
                        if (currPhotoPos >= 5 && currPhotoPos < 29) {//第6张到第29张
                            if (currPhotoPos == photoItems.size() - 1) {//点击添加
                                photoItems.remove(currPhotoPos);
                                photoItems.add(newItem);
                                photoItems.add(new SubmitParamWrapper.PhotoItem(0, "", IMGS[5], IMG_DECS[5]));
                            } else {//点击已经存在的照片，替换
                                photoItems.set(currPhotoPos, newItem);
                            }
                        } else {//第30张照片
                            photoItems.set(currPhotoPos, newItem);
                        }
                        if (!TextUtils.isEmpty(imgPath)) {
                           FileUtilTool.deleteFile(imgPath);
                        }
                        startInputTextActivity(INPUT_DESC, "请填写照片描述", "最多输入7个汉字", "", 7, -1);
                    }
                }
            }
        });
    }


}
