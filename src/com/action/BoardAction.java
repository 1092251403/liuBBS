
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import com.orm.Admin;
import com.orm.Board;
import com.service.IBoardService;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class BoardAction extends ActionSupport{
    @Resource(name="boardService")
    IBoardService boardService;
    
    private List<Board> boardList;
     private List<Board> childBoards;
     private List<Board> rootBoards;

    private Board board;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    public List<Board> getChildBoards() {
        return childBoards;
    }

    public void setChildBoards(List<Board> childBoards) {
        this.childBoards = childBoards;
    }
    
     public List<Board> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<Board> boardList) {
        this.boardList = boardList;
    }
    public List<Board> getRootBoards() {
        return rootBoards;
    }

    public void setRootBoards(List<Board> rootBoards) {
        this.rootBoards = rootBoards;
    }

  
    
    public String execute() throws Exception {
        try {
           loadRootBoards();
           return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
       
    }
    
    public String listBoard() throws Exception
    {
        try {
               setBoardList(boardService.loadAllBoards());
               return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    public String listChildBoards() throws Exception
    {
        
        int parentId = Integer.valueOf(ServletActionContext.getRequest().getParameter("parentId"));
        try {
         loadRootBoards();
         setChildBoards(boardService.loadChildBoards(parentId));
         return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
     public String loadRootBoards() throws Exception
    {
        try {
            setRootBoards(boardService.loadRootBoards());
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
   
    public String prepareAddBoard() throws Exception
    {
        try {
             setBoardList(boardService.loadAllBoards());
             return "addBoard";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    public String addBoard() throws Exception
    {
        Admin admin = (Admin)ServletActionContext.getRequest().getSession().getAttribute("admin");
        
        int bid = getBoard().getId();
        int parentId = 0;
        Board tempBoard =new Board() ;
       try 
        {      
           if (bid==-1) 
           {
               tempBoard.setName(getBoard().getName());
               tempBoard.setAdmin(admin);
               tempBoard.setDescription(getBoard().getDescription());
               
               if (boardService.saveOrUpdateBoard(tempBoard)) {
                  return "addSuccess";
               }
              return ERROR;
   
            }
             
           else 
            {
               parentId = boardService.loadBoard(bid).getBoard().getId();
                
               Board board = boardService.loadBoard(bid);
               tempBoard.setName(getBoard().getName());
               tempBoard.setAdmin(admin);
               tempBoard.setBoard(board);
               tempBoard.setDescription(getBoard().getDescription());
               
                if ( boardService.saveOrUpdateBoard(tempBoard)) {
                      return "addSuccess";
                }
                  return ERROR;
            }
          
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return ERROR;
        }
  
        
        
       
    }
    
    public String prepareModifyBoard() throws Exception
    {
         
        HttpServletRequest request = ServletActionContext.getRequest(); 
        int bid = 0;
        if (request.getParameter("bid")!=null) {
            bid = Integer.valueOf(request.getParameter("bid").toString());
        }
        setBoard(boardService.loadBoard(bid));
        loadRootBoards();
        return "prepareModifyBoard"; 
    }         
}
