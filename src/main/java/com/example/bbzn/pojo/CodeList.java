package com.example.bbzn.pojo;

import java.util.List;

public class CodeList {

    private List<?> availableList;
    private List<?> unavailableList;

    public List<?> getAvailableList() {
        return availableList;
    }

    public void setAvailableList(List<?> availableList) {
        this.availableList = availableList;
    }

    public List<?> getUnavailableList() {
        return unavailableList;
    }

    public void setUnavailableList(List<?> unavailableList) {
        this.unavailableList = unavailableList;
    }

    @Override
    public String toString() {
        return "CodeList{" +
                "availableList=" + availableList +
                ", unavailableList=" + unavailableList +
                '}';
    }
}
