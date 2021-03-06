
package com.example.covid19.model.create;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddData implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<AddData> CREATOR = new Creator<AddData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public AddData createFromParcel(Parcel in) {
            return new AddData(in);
        }

        public AddData[] newArray(int size) {
            return (new AddData[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1274546081891254476L;

    protected AddData(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public AddData() {
    }

    /**
     * 
     * @param message
     * @param status
     */
    public AddData(Boolean status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
