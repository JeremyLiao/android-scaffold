package com.jeremyliao.android.scaffold.databinding.observable;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityObservableDemoBinding;
import com.jeremyliao.android.scaffold.utils.ToastUtils;

public class ObservableDemoActivity extends AppCompatActivity {

    ActivityObservableDemoBinding binding;
    ObservableViewModel viewModel;
    Handler handler = new Handler(Looper.getMainLooper());

    public ObservableField<String> stringObservableField = new ObservableField<>();

    Observer observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            ToastUtils.showShort(s);
        }
    };

    Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            if (sender instanceof ObservableField) {
                String s = (String) ((ObservableField) sender).get();
                ToastUtils.showShort(s);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_observable_demo);
        viewModel = ViewModelProviders.of(this).get(ObservableViewModel.class);
        binding.setVm(viewModel);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
        viewModel.name.observe(this, observer);
        stringObservableField.addOnPropertyChangedCallback(callback);
    }

    public void setValue() {
        viewModel.name.setValue("hello LiveData");
        stringObservableField.set("hello Observable");
    }

    public void postValue() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewModel.name.setValue("hello LiveData");
                stringObservableField.set("hello Observable");
            }
        }, 5000);
    }
}
