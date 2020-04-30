package com.cyclingrecord.payload;

public class UploadFileResponse {
    private String imageName;
    private String imageDownloadUri;
    private long size;

    public UploadFileResponse(String imageName, String imageDownloadUri, long size){
        this.imageName = imageName;
        this.imageDownloadUri = imageDownloadUri;
        this.size = size;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageDownloadUri() {
        return imageDownloadUri;
    }

    public void setImageDownloadUri(String imageDownloadUri) {
        this.imageDownloadUri = imageDownloadUri;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
