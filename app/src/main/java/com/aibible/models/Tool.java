package com.aibible.models;

import java.io.Serializable;

public class Tool implements Serializable {
    public String id;
    public String toolName;
    public String toolDes;
    public String shortDes;
    public boolean verified;
    public String verifiedReason;
    public String[] pricing;
    public String publishedAt;
    public int favCount;
    public String[] tagsIndex;
    public String[] categories;
    public String[] newFeatures;
    public String url;
    public String imgUrl;
    public String imgFormat;
    public String tnUrl;
}
