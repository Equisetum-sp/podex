package com.example.mobile_final;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;

public class Pokemon implements Parcelable {
    int id;
    double height, weight;
    String name, about;
    String[] location = new String[3];
    Stat stat;
    Type[] types = new Type[2];

    public Pokemon(int id, String name, double height, double weight, Stat stat, Type type1, Type type2, String about, String[] location){
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.stat = stat;
        this.types[0] = type1;
        this.types[1] = type2;
        this.about = about;
        this.location = location;
    }

    //region Parcelable methods
    protected Pokemon(Parcel in) {
        id = in.readInt();
        name = in.readString();
        height = in.readDouble();
        weight = in.readDouble();
        stat = (Stat) in.readParcelable(Stat.class.getClassLoader());
        types = in.createTypedArray(Type.CREATOR);
        about = in.readString();
        in.readStringArray(location);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(height);
        dest.writeDouble(weight);
        dest.writeParcelable(stat, flags);
        dest.writeTypedArray(types, flags);
        dest.writeString(about);
        dest.writeStringArray(location);
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };
    //endregion

    public int getId() {
        return id;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public Stat getStat() {
        return stat;
    }

    public Type[] getTypes() {
        return types;
    }

    public String getAbout() {
        return about;
    }

    public String getLocationRed(){
        return location[0];
    }
    public String getLocationBlue(){
        return location[1];
    }
    public String getLocationYellow(){
        return location[2];
    }

    public Drawable getSprite(Context context){
        String readyFormattedName = "Sprites/" + String.format("%03d", this.id) + ".png";

        try {
            InputStream inputStream = context.getAssets().open(readyFormattedName);
            Drawable spriteDrawable = Drawable.createFromStream(inputStream, null);
            return spriteDrawable;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MediaPlayer getSoundPlayer(Context context){
        String readyFormattedName = "Cries/" +  Integer.toString(this.id) + ".mp3";

        MediaPlayer mediaPlayer;
        try {
            // Open the audio file from the assets folder
            AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd(readyFormattedName);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(
                    assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(),
                    assetFileDescriptor.getLength()
            );
            mediaPlayer.prepare();
            return mediaPlayer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
