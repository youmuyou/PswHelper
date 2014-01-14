package com.example.pswhelper;

import android.os.Parcel;
import android.os.Parcelable;

public class Psw  implements Parcelable{
        private String website;
        private String name;
        private String psw;
        private int id;
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getWebsite() {
            return website;
        }
        public void setWebsite(String website) {
            this.website = website;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getPsw() {
            return psw;
        }
        public void setPsw(String psw) {
            this.psw = psw;
        }
        @Override
        public int describeContents() {
            // TODO Auto-generated method stub
            return 0;
        }
        @Override
        public void writeToParcel(Parcel arg0, int arg1) {
            // TODO Auto-generated method stub
            arg0.writeString(name);
            arg0.writeString(website);
            arg0.writeString(psw);
            arg0.writeInt(id);
        }
        public static final Parcelable.Creator<Psw> CREATOR = new Creator<Psw>() {  
            public Psw createFromParcel(Parcel source) {  
                Psw p = new Psw();  
                p.name = source.readString();  
                p.website = source.readString();  
                p.psw = source.readString();  
                p.id = source.readInt();
                return p;  
            }  
            public Psw[] newArray(int size) {  
                return new Psw[size];  
            }  
        };  
}
