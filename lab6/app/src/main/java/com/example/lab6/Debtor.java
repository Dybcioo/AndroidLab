package com.example.lab6;

class Debtor {
    private String id;
    private String name;
    private String phone;
    private String photo;
    private boolean isSelected;

    public Debtor(String id, String name, String phone, String photo) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.photo = photo;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
