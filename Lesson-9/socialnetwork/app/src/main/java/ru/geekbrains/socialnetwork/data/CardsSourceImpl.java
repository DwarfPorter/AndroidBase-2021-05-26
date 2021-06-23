package ru.geekbrains.socialnetwork.data;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.socialnetwork.R;

public class CardsSourceImpl implements CardsSource {
    private List<CardData> dataSource;
    private Resources resources;

    public CardsSourceImpl(Resources resources) {
        this.resources = resources;
        dataSource = new ArrayList<>(7);
    }

    public CardsSourceImpl init() {
        String[] titles = resources.getStringArray(R.array.titles);
        String[] descriptions = resources.getStringArray(R.array.descriptions);
        int[] pictures = getImageArray();

        for(int i=0; i<descriptions.length; i++){
            dataSource.add(new CardData(titles[i], descriptions[i], pictures[i], false));
        }
        
        return this;
    }

    private int[] getImageArray() {
        TypedArray pictures = resources.obtainTypedArray(R.array.pictures);
        int len = pictures.length();
        int[] answer = new int[len];
        for(int i=0; i<len; i++){
            answer[i] = pictures.getResourceId(i, 0);
        }
        return answer;
    }

    @Override
    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public int getSize() {
        return dataSource.size();
    }
}
