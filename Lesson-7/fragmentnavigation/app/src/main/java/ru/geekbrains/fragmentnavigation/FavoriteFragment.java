package ru.geekbrains.fragmentnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FavoriteFragment extends Fragment {

    public static final String ARG_1 = "ARG1";

    private String arg1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        TextView tv = view.findViewById(R.id.textView2);
        tv.setText(arg1);
        return view;
    }

    public static FavoriteFragment newInstance(String arg1){
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_1, arg1);
        favoriteFragment.setArguments(args);
        return favoriteFragment;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args == null) return;
        arg1 = args.getString(ARG_1);
    }
}
