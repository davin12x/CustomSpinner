package com.customspinner;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Lalit Bagga on 2017-03-05.
 */

public class CustomSpinner extends AppCompatSpinner implements View.OnTouchListener, AdapterView.OnItemSelectedListener {
    private Boolean isSpinnerTouched = false;
    private CustomSpinnerItemSelectedHandler customSpinnerItemSelectedHandler;

    public CustomSpinner(Context context) {
        super(context);
        registerListeners();
    }

    public CustomSpinner(Context context, int mode) {
        super(context, mode);
        registerListeners();
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        registerListeners();
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        registerListeners();
    }

    private void registerListeners() {
        setOnItemSelectedListener(this);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        isSpinnerTouched = true;
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (isSpinnerTouched && customSpinnerItemSelectedHandler != null) {
            customSpinnerItemSelectedHandler.onSpinnerItemSelected(parent, view, position, id);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (isSpinnerTouched && customSpinnerItemSelectedHandler != null) {
            customSpinnerItemSelectedHandler.onNothingSelected(parent);
        }
    }

    public void setCustomSpinnerItemSelectedHandler(CustomSpinnerItemSelectedHandler customSpinnerItemSelectedHandler) {
        this.customSpinnerItemSelectedHandler = customSpinnerItemSelectedHandler;
    }

    public interface CustomSpinnerItemSelectedHandler {
        void onSpinnerItemSelected(AdapterView<?> parent, View view, int position, long id);
        void onNothingSelected(AdapterView<?> parent);
    }
}
