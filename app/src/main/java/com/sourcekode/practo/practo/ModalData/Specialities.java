package com.sourcekode.practo.practo.ModalData;


public class Specialities {

    private String Title;
    private int Thumbnail;

    public Specialities() {
    }

    public Specialities(String title, int thumbnail) {
        Title = title;
        Thumbnail = thumbnail;
    }

    public String getTitle() {
        return Title;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }

}
