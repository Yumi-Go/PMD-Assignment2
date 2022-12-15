package com.example.assignment2.Presenter;

import static androidx.core.util.Preconditions.checkNotNull;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.assignment2.Contract.MenuContract;

public class MenuPresenter implements MenuContract.Presenter {
    private final MenuContract.View mMainView;

    @SuppressLint("RestrictedApi")
    public MenuPresenter(@NonNull MenuContract.View mainView){
        mMainView = checkNotNull(mainView, "mainView cannot be null");
        mMainView.setPresenter(this);
    }


    @Override
    public void start() {
        loadMain(false);
    }

    @Override
    public void loadMain(boolean forceUpdate) {

    }
}
