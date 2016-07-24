package com.orm;



import java.util.HashSet;
import java.util.Set;

/**
 * Admin generated by hbm2java
 */
public class Admin  implements java.io.Serializable {


     private Integer id;
     private String account;
     private String password;
     private int qx;
     private String nickName;
     private String name;
     private String photoPath;
     private Set posts = new HashSet(0);
     private Set replies = new HashSet(0);
     private Set boards = new HashSet(0);

    public Admin() {
    }

	
    public Admin(String account, String password, int qx) {
        this.account = account;
        this.password = password;
        this.qx = qx;
    }
    public Admin(String account, String password, int qx, String nickName, String name, String photoPath, Set posts, Set replies, Set boards) {
       this.account = account;
       this.password = password;
       this.qx = qx;
       this.nickName = nickName;
       this.name = name;
       this.photoPath = photoPath;
       this.posts = posts;
       this.replies = replies;
       this.boards = boards;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAccount() {
        return this.account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public int getQx() {
        return this.qx;
    }
    
    public void setQx(int qx) {
        this.qx = qx;
    }
    public String getNickName() {
        return this.nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getPhotoPath() {
        return this.photoPath;
    }
    
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
    public Set getPosts() {
        return this.posts;
    }
    
    public void setPosts(Set posts) {
        this.posts = posts;
    }
    public Set getReplies() {
        return this.replies;
    }
    
    public void setReplies(Set replies) {
        this.replies = replies;
    }
    public Set getBoards() {
        return this.boards;
    }
    
    public void setBoards(Set boards) {
        this.boards = boards;
    }




}


