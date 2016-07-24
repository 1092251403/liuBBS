package com.service;

import com.orm.Board;
import com.orm.Post;

import java.util.List;


public interface IBoardService {
     
    public Board loadBoard(int id);
       
       public List<Board> loadChildBoards(int parentId);
    
   
    public List<Board> loadAllBoards();
    
      public List<Board> loadRootBoards();
    
    
    public boolean  saveOrUpdateBoard(Board board);
    
}
