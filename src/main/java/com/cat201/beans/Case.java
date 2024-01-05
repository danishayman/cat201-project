package com.cat201.beans;

import java.util.List;

public class Case {
    private int id;
    private String title;
    private String description;
    private String status;
    private String clientId;
    private String staffId;
    private List<Integer> documentIds;

    public Case() {
    }

    public Case(int id, String title, String description, String status, String clientId, String staffId, List<Integer> documentIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.clientId = clientId;
        this.staffId = staffId;
        this.documentIds = documentIds;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus(){
        return status;
    }

    public String getclientId() {
        return clientId;
    }

    public String getStaffId() {
        return staffId;
    }

    public List<Integer> getDocumentIds() {
        return documentIds;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setclientId(String clientId){
        this.clientId = clientId;
    }

    public void setStaffId(String staffId){
        this.staffId = staffId;
    }

    public void setDocumentIds(List<Integer> documentIds) {
        this.documentIds = documentIds;
    }

    @Override
    public String toString() {
        String str = "Case [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status + ", clientId=" + clientId + ", staffId=" + staffId + ", documentIds=" + documentIds + "]";
        return str;
    }
}
