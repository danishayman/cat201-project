package com.cat201.beans;

public class Document {
    
    private int id;
    private String name;
    private String format;
    private String path;
    private int caseId; 
    private int uploaderId;

    public Document() {
    }

    public Document(int id, String name, String format, String path, int caseId, int uploaderId) {
        this.id = id;
        this.name = name;
        this.format = format;
        this.path = path;
        this.caseId = caseId;
        this.uploaderId = uploaderId;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public String getPath() {
        return path;
    }

    public int getCaseId() {
        return caseId;
    }

    public int getUploaderId() {
        return uploaderId;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public void setUploaderId(int uploaderId) {
        this.uploaderId = uploaderId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setFormat(String format){
        this.format = format;
    }

    public void setPath(String path){
        this.path = path;
    }

    @Override
    public String toString() {
        return "Document [caseId=" + caseId + ", format=" + format + ", id=" + id + ", name=" + name + ", path=" + path
                + ", uploaderId=" + uploaderId + "]";
    }
}
