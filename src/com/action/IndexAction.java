
package com.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Board;
import com.orm.Post;
import com.orm.Reply;
import com.orm.Student;
import com.service.IBoardService;
import com.service.IPostService;
import com.service.IStudentService;

import java.util.List;

import javax.annotation.Resource;


public class IndexAction extends ActionSupport {
    @Resource(name="studentService")
    IStudentService studentService;
    
    @Resource(name="boardService")
    IBoardService boardService;
    
    @Resource(name="postService")
    IPostService postService;
    
    private List<Board> rootBoard;
    private int todayNum;
    private int yestNum;
    private int highestNum;

    public int getTodayNum() {
        return todayNum;
    }

    public void setTodayNum(int todayNum) {
        this.todayNum = todayNum;
    }
    
    public int getYestNum() {
        return yestNum;
    }

    public void setYestNum(int yestNum) {
        this.yestNum = yestNum;
    }
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

   
    private Student student;
    private List<Post> hotPosts;
    private List<Post> recentlyPosts;
    private List<Reply> huifuReply;
    public List<Post> getRecentlyPosts() {
		return recentlyPosts;
	}

	public void setRecentlyPosts(List<Post> recentlyPosts) {
		this.recentlyPosts = recentlyPosts;
	}

	public List<Post> getHotPosts() {
        return hotPosts;
    }

    public List<Reply> getHuifuReply() {
		return huifuReply;
	}

	public void setHuifuReply(List<Reply> huifuReply) {
		this.huifuReply = huifuReply;
	}

	public void setHotPosts(List<Post> hotPosts) {
        this.hotPosts = hotPosts;
    }
   
    
    
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    

    public String execute() throws Exception {
        try {
            Student sessionStudent = (Student) ActionContext.getContext().getSession().get("student");
        if (sessionStudent!= null) {
            setStudent(studentService.getStudentByStuNum(sessionStudent.getStuNum()));
        }
            setHuifuReply(postService.huifurankPosts(10));
            setRecentlyPosts(postService.recentlyrankPosts(10));
            setRootBoard(boardService.loadRootBoards());
            setHotPosts(postService.rankPosts(10));
            setTotal(postService.countTotalPost());
            setYestNum(postService.countYesteradyPost());
            setTodayNum(postService.countTodayPost());
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
   
    
    public List<Board> getRootBoard() {
        return rootBoard;
    }
    
    
    public void setRootBoard(List<Board> rootBoard) {
        this.rootBoard = rootBoard;
    }
    
   
    
}
