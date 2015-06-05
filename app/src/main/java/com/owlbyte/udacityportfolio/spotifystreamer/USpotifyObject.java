package com.owlbyte.udacityportfolio.spotifystreamer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Object to be used as Artist as well as Track
 * Created by carlos on 6/2/15.
 */
public class USpotifyObject implements Parcelable{
    private String id;
    private String name;
    private String smallImage;
    private String largeImage;
    private String album;

    public USpotifyObject(String id, String name, String smallImage) {
        this(id, name, smallImage, "");
    }

    public USpotifyObject(String id, String name, String smallImage, String album) {
        this(id, name, smallImage, "", album);
    }

    public USpotifyObject(String id, String name, String smallImage, String largeImage, String album) {
        this.id = id;
        this.name = name;
        this.smallImage = smallImage;
        this.largeImage = largeImage;
        this.album = album;
    }

    public USpotifyObject(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.smallImage = in.readString();
        this.largeImage = in.readString();
        this.album = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String image) {
        this.smallImage = smallImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(smallImage);
        parcel.writeString(largeImage);
        parcel.writeString(album);
    }

    public static final Parcelable.Creator<USpotifyObject> CREATOR = new Parcelable.Creator<USpotifyObject>() {
        public USpotifyObject createFromParcel(Parcel in) {
            return new USpotifyObject(in);
        }

        public USpotifyObject[] newArray(int size) {
            return new USpotifyObject[size];
        }
    };
}
