package com.csye6225.fall2018.yuchen.datamodel;

public class Lecture {
    private String notes;
    private String associatedMaterial;

    public Lecture() {
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
