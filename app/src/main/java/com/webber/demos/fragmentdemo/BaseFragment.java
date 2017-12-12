package com.webber.demos.fragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by picher on 2017/12/12.
 * Describe：
 */

public class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("picher", getClass().getSimpleName() + "------->>onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("picher", getClass().getSimpleName() + "------->>onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("picher", getClass().getSimpleName() + "------->>onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("picher", getClass().getSimpleName() + "------->>onViewCreated");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("picher", getClass().getSimpleName() + "------->>onActivityCreated");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Log.d("picher", getClass().getSimpleName() + "------->>onCreateOptionsMenu");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("picher", getClass().getSimpleName() + "------->>onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("picher", getClass().getSimpleName() + "------->>onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("picher", getClass().getSimpleName() + "------->>onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("picher", getClass().getSimpleName() + "------->>onStop");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("picher", getClass().getSimpleName() + "------->>onDestroy");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("picher", getClass().getSimpleName() + "------->>onDestroyView");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("picher", getClass().getSimpleName() + "------->>onDetach");

    }
}
