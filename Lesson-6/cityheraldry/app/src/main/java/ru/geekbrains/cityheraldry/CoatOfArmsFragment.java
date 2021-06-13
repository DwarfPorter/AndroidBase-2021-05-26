package ru.geekbrains.cityheraldry;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CoatOfArmsFragment extends Fragment {

    public static final String ARG_INDEX = "index";

    private City city;

    public static CoatOfArmsFragment newInstance(City city) {
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();

        // Передача параметров
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city = getArguments().getParcelable(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coat_of_arms, container, false);

        AppCompatImageView imageView = view.findViewById(R.id.coat_of_arms);

        TypedArray images = getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
        int image = images.getResourceId(city.getImageIndex(), -1);
        imageView.setImageResource(image);

        TextView cityName = view.findViewById(R.id.textView);
        cityName.setText(city.getCityName());

        return view;
    }
}