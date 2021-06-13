package ru.geekbrains.cityheraldry;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CitiesFragment extends Fragment {

    public static final String CURRENT_CITY = "CurrentCity";
    private int currentPosition = 0;    // Текущая позиция (выбранный город)
    private boolean isLandscape;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    // вызывается после создания макета фрагмента, здесь мы проинициализируем список
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initlist((LinearLayout) view);
    }

    private void initlist(LinearLayout view) {
        String[] cities = getResources().getStringArray(R.array.cities);

        // В этом цикле создаём элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        // Кроме того, создаём обработку касания на элемент
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(30);
            view.addView(tv);
            final int index = i;
            tv.setOnClickListener(v -> {
                currentPosition = index;
                showCoatOfArms(index);
            });
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    // Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_CITY, currentPosition);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(CURRENT_CITY, 0);
        }

        if (isLandscape){
            showLandCoatOfArms(currentPosition);
        }
    }

    private void showCoatOfArms(int index) {
        if (isLandscape) {
            showLandCoatOfArms(index);
        }else{
            showPortCoatOfArms(index);
        }
    }

    private void showLandCoatOfArms(int index) {
        CoatOfArmsFragment detail = CoatOfArmsFragment.newInstance(index);

        FragmentManager fragmentManager = requireActivity()
                .getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.coat_of_arms, detail);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortCoatOfArms(int index) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), CoatOfArmsActivity.class);
        intent.putExtra(CoatOfArmsFragment.ARG_INDEX, index);
        startActivity(intent);
    }
}