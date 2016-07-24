package com.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Admin;
import com.orm.Post;
import com.orm.Reply;
import com.orm.Student;
import com.service.IPostService;
import com.service.IReplyService;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Administrator
 */
public class ReplyAction extends ActionSupport {

    private int pid;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
    private Reply reply;
    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }
    
    private List<Reply> replys;
    
    @Resource(name="replyService")
    IReplyService replyService;
    
    
    @Resource(name="postService")
    IPostService postService;

    @Override
    public String execute() throws Exception {
        return super.execute();
    }


    public String stuReply() throws Exception
    { 
       HttpServletRequest request = ServletActionContext.getRequest();
       int id =Integer.parseInt(request.getParameter("pid"));
       Student student =(Student)ActionContext.getContext().getSession().get("student");
        try 
        {
            Post post = postService.loadPost(id);
            replyService.stuReplyPost(student, post, reply);
            
            setReplys(replyService.getReplysByPid(id));
            request.setAttribute("pid", id);
            return SUCCESS;
        } 
        catch (Exception e) {
            return ERROR;
        }
        
    }
    
    public String adminReply() throws Exception
    { 
       HttpServletRequest request = ServletActionContext.getRequest();
       int id =Integer.parseInt(request.getParameter("pid"));
       Admin admin =(Admin)ActionContext.getContext().getSession().get("admin");
        try 
        {
            Post post = postService.loadPost(id);
            replyService.adminReplyPost(admin, post, reply);
            
            setReplys(replyService.getReplysByPid(id));
            request.setAttribute("pid", id);
            return SUCCESS;
        } 
        catch (Exception e) {
            return ERROR;
        }
        
    }
     public String prepareModifyReply() throws Exception
    {
         int rid = -1;
         if (ServletActionContext.getRequest().getParameter("rid")!=null) {
            rid = Integer.valueOf(ServletActionContext.getRequest().getParameter("rid"));
            setReply(replyService.loadReply(rid));
            return SUCCESS;
        }
         return ERROR;
    }
    
    
    public String modifyReply() throws Exception
    {
        getReply().setContent(getReply().getContent());
        if (replyService.modifyReply(getReply())) {
            return "modifySuccess";
        }
        return ERROR;
        
    }
    
     public String delReply() throws Exception
    {
         int rid = -1;
         if (ServletActionContext.getRequest().getParameter("rid")!=null) {
           rid = Integer.valueOf(ServletActionContext.getRequest().getParameter("rid"));
           replyService.delReply(rid);
           return "deleteSuccess";
         }
         return ERROR;
    }
    
    public List<Reply> getReplys() {
        return replys;
    }

    public void setReplys(List<Reply> replys) {
        this.replys = replys;
    }
    
}
