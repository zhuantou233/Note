package com.tao.note.ui.accountdetail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.tao.note.BR;
import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.ActivityAccountDetailBinding;
import com.tao.note.ui.accountdetail.changepassword.ChangeUserPasswordDialog;
import com.tao.note.ui.accountdetail.changeusername.ChangeUserNameDialog;
import com.tao.note.ui.accountdetail.changeuserphonenumber.ChangeUserPhoneNumberDialog;
import com.tao.note.ui.base.BaseActivity;
import com.tao.note.utils.L;
import com.tao.note.utils.PhotoUtil;
import com.tao.note.utils.ToastUtil;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;


import java.io.File;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import cn.bmob.v3.datatype.BmobFile;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * Created by Tao Zhou on 2019/4/28
 * Package name: com.tao.note.ui.accountdetail
 */
public class AccountDetailActivity extends BaseActivity<ActivityAccountDetailBinding, AccountDetailViewModel>
        implements AccountDetailNavigator, HasSupportFragmentInjector, View.OnClickListener, EasyPermissions.PermissionCallbacks {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;

    private AccountDetailViewModel mAccountDetailViewModel;
    private ActivityAccountDetailBinding mActivityAccountDetailBinding;
    private Toolbar mToolbar;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AccountDetailActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_detail;
    }

    @Override
    public AccountDetailViewModel getViewModel() {
        mAccountDetailViewModel = ViewModelProviders.of(this, factory).get(AccountDetailViewModel.class);
        return mAccountDetailViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAccountDetailBinding = getViewDataBinding();
        mAccountDetailViewModel.setNavigator(this);
        setUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable.getCause() instanceof UnknownHostException) {
            ToastUtil.getInstance(this).shortToast(getString(R.string.no_network));
        } else {
            ToastUtil.getInstance(this).shortToast(throwable.getMessage());
        }
        L.i(throwable.getMessage());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PhotoUtil.GET_IMAGE_FROM_PHONE:
                if (resultCode == RESULT_OK) {
                    initUCrop(data.getData());
                }
                break;
            case PhotoUtil.GET_IMAGE_BY_CAMERA:
                if (resultCode == RESULT_OK) {
                    initUCrop(PhotoUtil.imageUriFromCamera);
                }
                break;
            case UCrop.REQUEST_CROP://UCrop裁剪之后的处理
                if (resultCode == RESULT_OK) {
                    Uri resultUri = UCrop.getOutput(data);
                    File file = new File(PhotoUtil.getImageAbsolutePath(this, resultUri));
                    mAccountDetailViewModel.onUploadAvatar(file);
                } else if (resultCode == UCrop.RESULT_ERROR) {
                    final Throwable cropError = UCrop.getError(data);
                    handleError(cropError);
                }
                break;
            case UCrop.RESULT_ERROR://UCrop裁剪错误之后的处理
                final Throwable cropError = UCrop.getError(data);
                handleError(cropError);
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initUCrop(Uri uri) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        long time = System.currentTimeMillis();
        String imageName = timeFormatter.format(new Date(time));

        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), imageName + ".jpeg"));

        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置隐藏底部容器，默认显示
//        options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark));
        //设置toolbar标题及控件颜色
        options.setToolbarWidgetColor(ActivityCompat.getColor(this, R.color.white));

        //开始设置
        //设置最大缩放比例
        options.setMaxScaleMultiplier(5);
        //设置图片在切换比例时的动画
        options.setImageToCropBoundsAnimDuration(666);
        //设置裁剪窗口是否为圆
        options.setCircleDimmedLayer(true);
        //设置是否展示矩形裁剪框
        options.setShowCropFrame(false);
        //设置裁剪框横竖线的宽度
        //options.setCropGridStrokeWidth(20);
        //设置裁剪框横竖线的颜色
        //options.setCropGridColor(Color.GREEN);
        //设置竖线的数量
        options.setCropGridColumnCount(0);
        //设置横线的数量
        options.setCropGridRowCount(0);

        UCrop.of(uri, destinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(1000, 1000)
                .withOptions(options)
                .start(this);
    }

    @Override
    public void dismissDialog() {
        ChangeUserNameDialog.newInstance().dismissDialog();
        ChangeUserPhoneNumberDialog.newInstance().dismissDialog();
    }

    private void setUp() {
        mAccountDetailViewModel.onDataLoad();
        mToolbar = mActivityAccountDetailBinding.toolbar;
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        LinearLayout avatarForm = mActivityAccountDetailBinding.avatarForm;
        LinearLayout nameForm = mActivityAccountDetailBinding.nameForm;
        LinearLayout phoneForm = mActivityAccountDetailBinding.phoneForm;
        LinearLayout passwordForm = mActivityAccountDetailBinding.passwordForm;
        LinearLayout deleteAccountForm = mActivityAccountDetailBinding.deleteAccountForm;
        avatarForm.setOnClickListener(this);
        nameForm.setOnClickListener(this);
        phoneForm.setOnClickListener(this);
        passwordForm.setOnClickListener(this);
        deleteAccountForm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.avatar_form:
                showAvatarDialog();
                break;
            case R.id.name_form:
                ChangeUserNameDialog.newInstance().show(getSupportFragmentManager());
                break;
            case R.id.phone_form:
                ChangeUserPhoneNumberDialog.newInstance().show(getSupportFragmentManager());
                break;
            case R.id.password_form:
                ChangeUserPasswordDialog.newInstance().show(getSupportFragmentManager());
                break;
            case R.id.delete_account_form:
                break;
        }
    }

    private void showAvatarDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(R.array.avatar, (dialog, which) -> {
            if (which == 0) {
                chooseFromPhoto();
            } else {
                chooseFromGallery();
            }
        });
        builder.create().show();
    }

    private void chooseFromGallery() {
        PhotoUtil.openLocalImage(this);
    }

    @AfterPermissionGranted(PhotoUtil.PERMISSIONS_PHOTO)
    private void chooseFromPhoto() {
        PhotoUtil.openCameraImage(this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        L.i("onPermissionsGranted");
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        L.i("onPermissionsDenied");
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle("权限已经被您拒绝")
                    .setRationale("如果不打开权限则无法使用该功能,点击确定去打开权限")
                    .setRequestCode(10001) //用于onActivityResult回调做其它对应相关的操作
                    .build()
                    .show();
        }
    }
}

