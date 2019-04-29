package com.siri.comiccharacters;

import java.util.UUID;

public class Hero {
    private String mName;
    private String mRealName;
    private String mID;
    private UUID mId;
    private String mUrl;

    public Hero() { this(UUID.randomUUID()); }
    public Hero(UUID id) { mId = id; }
    public UUID getId() { return mId; }

    public String getID() { return mID; }
    public void setID(String mID) { this.mID = mID; }

    public String getName() {
        return mName;
    }
    public void setName(String mName) {
        this.mName = mName;
    }

    public String getRealName() { return mRealName; }
    public void setRealName(String mRealName) { this.mRealName = mRealName; }

    public String getUrl() {
        return mUrl;
    }
    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
