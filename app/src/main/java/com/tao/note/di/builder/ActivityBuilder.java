package com.tao.note.di.builder;

import com.tao.note.ui.accountdetail.AccountDetailActivity;
import com.tao.note.ui.accountdetail.changepassword.ChangeUserPasswordDialogProvider;
import com.tao.note.ui.accountdetail.changeusername.ChangeUserNameDialogProvider;
import com.tao.note.ui.accountdetail.changeuserphonenumber.ChangeUserPhoneNumberDialogProvider;
import com.tao.note.ui.login.LoginActivity;
import com.tao.note.ui.login.bottomdialog.ResetDialogProvider;
import com.tao.note.ui.login.resetpassword.ResetPasswordFragmentProvider;
import com.tao.note.ui.login.signin.SignInFragmentProvider;
import com.tao.note.ui.login.signinwithcode.SignInWithCodeFragmentProvider;
import com.tao.note.ui.login.signup.SignUpFragmentProvider;
import com.tao.note.ui.main.MainActivity;
import com.tao.note.ui.main.today.RecordTodayFragmentProvider;
import com.tao.note.ui.profile.ProfileActivity;
import com.tao.note.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Tao Zhou on 2019/4/18
 * Package name: com.tao.note.di.builder
 */
@Module
public abstract class ActivityBuilder {

    //    @ContributesAndroidInjector(modules = {
//            FeedActivityModule.class,
//            BlogFragmentProvider.class,
//            OpenSourceFragmentProvider.class})
//    abstract FeedActivity bindFeedActivity();
//
    @ContributesAndroidInjector(modules = {
            SignInFragmentProvider.class,
            SignUpFragmentProvider.class,
            ResetDialogProvider.class,
            SignInWithCodeFragmentProvider.class,
            ResetPasswordFragmentProvider.class
    })
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {
            RecordTodayFragmentProvider.class
    })
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract ProfileActivity bindProfileActivity();

    @ContributesAndroidInjector(modules = {
            ChangeUserNameDialogProvider.class,
            ChangeUserPhoneNumberDialogProvider.class,
            ChangeUserPasswordDialogProvider.class
    })
    abstract AccountDetailActivity bindAccountDetailActivity();
}
