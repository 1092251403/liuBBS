
package com.service;

import com.orm.Admin;
import com.orm.Post;
import com.orm.Reply;
import com.orm.Student;

import java.util.List;


public interface IReplyService {

    public Reply loadReply(int rid);
 
    public List<Reply> getReplysByPid(int pId);
    
    public boolean stuReplyPost(Student student,Post post,Reply reply);
    
    public boolean modifyReply(Reply reply);
    
    public boolean delReply(int rid);

	public boolean adminReplyPost(Admin admin, Post post, Reply reply);
    
    //public int CountReplyByUser(Object user);
    
}
