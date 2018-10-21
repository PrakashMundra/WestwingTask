package com.westwingtask.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Campaign implements Parcelable {
    public static final Creator<Campaign> CREATOR = new Creator<Campaign>() {
        @Override
        public Campaign createFromParcel(Parcel in) {
            return new Campaign(in);
        }

        @Override
        public Campaign[] newArray(int size) {
            return new Campaign[size];
        }
    };
    public String name;
    public String description;
    public Image image;

    public Campaign(String name, String description, Image image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    private Campaign(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        Object imageObj = in.readValue(Image.class.getClassLoader());
        if (imageObj != null)
            this.image = (Image) imageObj;
    }

    public String getImageUrl() {
        return image.url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeValue(this.image);
    }
}
