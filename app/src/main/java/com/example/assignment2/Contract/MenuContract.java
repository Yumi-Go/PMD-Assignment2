package com.example.assignment2.Contract;

import com.example.assignment2.BasePresenter;
import com.example.assignment2.BaseView;

public interface MenuContract {
    interface View extends BaseView<Presenter> {
    }
    interface Presenter extends BasePresenter {
        void loadMain(boolean forceUpdate);
    }
}
