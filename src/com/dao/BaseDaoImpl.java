/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.mysql.jdbc.Connection;
import com.orm.Post;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author Administrator
 */
public class BaseDaoImpl implements BaseDao {

    @Resource(name = "hibernateTemplate")
    HibernateTemplate hibernateTemplate;

    
    public Object loadById(Class clazz, Serializable id) {
        return hibernateTemplate.get(clazz, id);
    }

    
    @Override
    public Object loadObject(String hql) {
        final String hql1 = hql;
        Object obj = null;
        List list = hibernateTemplate.executeFind(new HibernateCallback() {
            
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql1);
                return query.list();
            }
        });
        if (list.size() > 0) {
            obj = list.get(0);
        }
        return obj;
    }

    
    @Override
    public void delById(Class clazz, Serializable id) {
        hibernateTemplate.delete(hibernateTemplate.load(clazz, id));
    }

    
    public void saveOrUpdate(Object obj) {
        hibernateTemplate.saveOrUpdate(obj);
    }

    
    @Override
    public List listAll(String clazz) {
        return hibernateTemplate.find("from " + clazz + " as c ");
    }

    @Override
    public List listAll(String clazz, int pageNo, int pageSize) {
        final int pNo = pageNo;
        final int pSize = pageSize;
        final String hqlString = "from " + clazz + " as c order by c.id desc";
        List list = hibernateTemplate.executeFind(
                new HibernateCallback<Object>() {

                    @Override
                    public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                        Query query = sn.createQuery(hqlString);
                        query.setMaxResults(pSize);
                        query.setFirstResult((pNo - 1) * pSize);
                        List result = query.list();
                        if (!Hibernate.isInitialized(result)) {
                            Hibernate.initialize(result);
                        }
                        return result;
                    }
                });
        return list;
    }

    public int lodepostcount(String clazz,int id){
    	final String hql = "select count(*) from  " + clazz + " as c " + "where bid='"+ id +"' ";
        Long count = (Long) hibernateTemplate.execute(new HibernateCallback<Object>() {

            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                Query query = sn.createQuery(hql);
                query.setMaxResults(1);
                return query.uniqueResult();
            }
        });
        return count.intValue();
    }
    @Override
    public int countAll(String clazz) {
        
        final String hql = "select count(*) from  " + clazz + " as c ";
        Long count = (Long) hibernateTemplate.execute(new HibernateCallback<Object>() {

            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                Query query = sn.createQuery(hql);
                query.setMaxResults(1);
                return query.uniqueResult();
            }
        });
        return count.intValue();
    }

    @Override
    public List query(String hql) {
        final String hql1 = hql;

        return hibernateTemplate.executeFind(new HibernateCallback<Object>() {

            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                Query query = sn.createQuery(hql1);
                return query.list();
            }
        });
    }

    @Override
    public List query(String hql, int pageNo, int pageSize) {
        final int pNo = pageNo;
        final int pSize = pageSize;
        final String hqlString = hql ;
        List list = hibernateTemplate.executeFind(
                new HibernateCallback<Object>() {

                    @Override
                    public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                        Query query = sn.createQuery(hqlString);
                        query.setMaxResults(pSize);
                        query.setFirstResult((pNo - 1) * pSize);
                        List result = query.list();
                        if (!Hibernate.isInitialized(result)) {
                            Hibernate.initialize(result);
                        }
                        return result;
                    }
                });
        return list;
    }

    
    @Override
    public int countQuery(String hql) {
        final String hql1 = hql;
        Long count = (Long) hibernateTemplate.execute(new HibernateCallback(){

            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                  Query query = sn.createQuery(hql1);
                  query.setMaxResults(1);
                  return query.uniqueResult();
            }
            
        });
        return  count.intValue();
    }

    @Override
    public int update(String hql) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  

   
}
