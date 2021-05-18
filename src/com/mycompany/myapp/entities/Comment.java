/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author dell
 */
public class Comment {
    

    private int id;
    private String  authorName;
    private String  content;
    private int workshop;

    public Comment(int id, String authorName, String content, int workshop) {
        this.id = id;
        this.authorName = authorName;
        this.content = content;
        this.workshop = workshop;
    }

    public Comment(String authorName, String content, int workshop) {
        this.authorName = authorName;
        this.content = content;
        this.workshop = workshop;
    }
    

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getContent() {
        return content;
    }

    public int getWorkshop() {
        return workshop;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWorkshop(int workshop) {
        this.workshop = workshop;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", authorName=" + authorName + ", content=" + content + ", workshop=" + workshop + '}';
    }
    
    
}
