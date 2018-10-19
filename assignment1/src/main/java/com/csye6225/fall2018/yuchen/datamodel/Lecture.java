package com.csye6225.fall2018.yuchen.datamodel;

public class Lecture {
    private long lectureId;
    private String notes;
    private String associatedMaterial;

    public Lecture() {
    }

    public long getLectureId() {
        return lectureId;
    }

    public void setLectureId(long lectureId) {
        this.lectureId = lectureId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAssociatedMaterial() {
        return associatedMaterial;
    }

    public void setAssociatedMaterial(String associatedMaterial) {
        this.associatedMaterial = associatedMaterial;
    }
}
