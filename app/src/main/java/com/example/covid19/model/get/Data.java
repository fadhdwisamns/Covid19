
package com.example.covid19.model.get;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("covid19apps")
    @Expose
    private List<Covid19app> covid19apps = null;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;
    private final static long serialVersionUID = 3225419319009850143L;

    protected Data(Parcel in) {
        in.readList(this.covid19apps, (com.example.covid19.model.get.Covid19app.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param covid19apps
     */
    public Data(List<Covid19app> covid19apps) {
        super();
        this.covid19apps = covid19apps;
    }

    public List<Covid19app> getCovid19apps() {
        return covid19apps;
    }

    public void setCovid19apps(List<Covid19app> covid19apps) {
        this.covid19apps = covid19apps;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(covid19apps);
    }

    public int describeContents() {
        return  0;
    }

}
