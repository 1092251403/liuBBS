package com.serviceImpl;

import com.dao.BaseDao;
import com.orm.Admin;
import com.orm.Student;
import com.service.ILoginService;
import javax.annotation.Resource;


public class LoginServiceImpl implements ILoginService {
     
     @Resource(name="dao")
     BaseDao dao ;
    
    @Override
    public Student stuLogin(Student student) {
        Student stu = (Student) dao.loadObject("from Student as s where s.stuNum = '"+student.getStuNum()+"' and s.password= '"+student.getPassword()+"' ");
        if (stu!=null) {
            return  stu;
        }
        return  null;
    }

    @Override
    public Admin adminLogin(Admin admin) {
        Admin a = (Admin) dao.loadObject("from Admin as a where a.account = '"+admin.getAccount()+"' and a.password = '"+admin.getPassword()+"' ");
        if (a!=null) {
            return  a;
        }
        return  null;
    }
    
}
