package com.tao.note;

import com.tao.note.data.DataManager;
import com.tao.note.ui.accountdetail.AccountDetailViewModel;
import com.tao.note.ui.accountdetail.changepassword.ChangeUserPasswordViewModel;
import com.tao.note.ui.login.LoginViewModel;
import com.tao.note.ui.login.bottomdialog.ResetViewModel;
import com.tao.note.ui.login.resetpassword.ResetPasswordViewModel;
import com.tao.note.ui.login.signin.SignInViewModel;
import com.tao.note.ui.login.signinwithcode.SignInWithCodeViewModel;
import com.tao.note.ui.login.signup.SignUpViewModel;
import com.tao.note.ui.main.MainViewModel;
import com.tao.note.ui.main.all.RecordAllViewModel;
import com.tao.note.ui.createrecord.CreateRecordViewModel;
import com.tao.note.ui.main.today.RecordTodayViewModel;
import com.tao.note.ui.main.week.RecordWeekViewModel;
import com.tao.note.ui.profile.ProfileViewModel;
import com.tao.note.ui.splash.SplashViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by Tao Zhou on 2019/4/18
 * Package name: com.tao.note
 */
@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {
    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //noinspection unchecked
            return (T) new LoginViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SignUpViewModel.class)) {
            //noinspection unchecked
            return (T) new SignUpViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SignInViewModel.class)) {
            //noinspection unchecked
            return (T) new SignInViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ResetViewModel.class)) {
            //noinspection unchecked
            return (T) new ResetViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SignInWithCodeViewModel.class)) {
            //noinspection unchecked
            return (T) new SignInWithCodeViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ResetPasswordViewModel.class)) {
            //noinspection unchecked
            return (T) new ResetPasswordViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
            //noinspection unchecked
            return (T) new ProfileViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(AccountDetailViewModel.class)) {
            //noinspection unchecked
            return (T) new AccountDetailViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ChangeUserPasswordViewModel.class)) {
            //noinspection unchecked
            return (T) new ChangeUserPasswordViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(RecordTodayViewModel.class)) {
            //noinspection unchecked
            return (T) new RecordTodayViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(RecordWeekViewModel.class)) {
            //noinspection unchecked
            return (T) new RecordWeekViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(RecordAllViewModel.class)) {
            //noinspection unchecked
            return (T) new RecordAllViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CreateRecordViewModel.class)) {
            //noinspection unchecked
            return (T) new CreateRecordViewModel(dataManager, schedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}

