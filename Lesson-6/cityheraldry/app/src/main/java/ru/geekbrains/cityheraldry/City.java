package ru.geekbrains.cityheraldry;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {
    private int imageIndex;
    private String cityName;

    public City(int imageIndex, String cityName){
        this.imageIndex = imageIndex;
        this.cityName = cityName;
    }

    protected City(Parcel in) {
        imageIndex = in.readInt();
        cityName = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public String getCityName() {
        return cityName;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageIndex);
        dest.writeString(cityName);
    }
}
