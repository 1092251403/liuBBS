package com.orm;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Post generated by hbm2java
 */
public class Post  implements java.io.Serializable {


     private Integer id;
     private Admin admin;
     private Student student;
     private Board board;
     private String name;
     private String content;
     private Date publishTime;
     private Integer count;
     private Set replies = new HashSet(0);

    public Post() {
    }

	
    public Post(Board board, String name) {
        this.board = board;
        this.name = name;
    }
    public Post(Admin admin, Student student, Board board, String name, String content, Date publishTime, Integer count, Set replies) {
       this.admin = admin;
       this.student = student;
       this.board = board;
       this.name = name;
       this.content = content;
       this.publishTime = publishTime;
       this.count = count;
       this.replies = replies;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Admin getAdmin() {
        return this.admin;
    }
    
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public Student getStudent() {
        return this.student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    public Board getBoard() {
        return this.board;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    public Date getPublishTime() {
        return this.publishTime;
    }
    
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
    public Integer getCount() {
        return this.count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }
    public Set getReplies() {
        return this.replies;
    }
    
    public void setReplies(Set replies) {
        this.replies = replies;
    }




}


