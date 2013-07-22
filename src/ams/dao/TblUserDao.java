/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.dao;

/**
 *
 * @author Bayu
 */
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ams.entity.TblUser;
import ams.util.HibernateUtil;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class TblUserDao {

    public void addUser(TblUser user) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void deleteUser(int id_user) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            TblUser user = (TblUser) session.load(TblUser.class, new Integer(id_user));
            session.delete(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void updateUser(TblUser user) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public List<TblUser> getAllUser() {
        List<TblUser> user = new ArrayList<TblUser>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            user = session.createCriteria(TblUser.class).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return user;
    }

    public TblUser getUserById(int iduser) {
        TblUser user = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            user = (TblUser) session.createCriteria(TblUser.class).add(Restrictions.eq("idUser", iduser)).uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return user;
    }

    public List<TblUser> getCariUser(String cariuser) {
        List<TblUser> user = new ArrayList<TblUser>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            user = session.createCriteria(TblUser.class).add(Restrictions.ilike("username", cariuser, MatchMode.ANYWHERE)).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return user;
    }

    public TblUser Login(String username) {
        TblUser user = null;
        Transaction trns = null;
        String kembali;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            user = (TblUser) session.createCriteria(TblUser.class).add(Restrictions.eq("username", username)).uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return user;
    }
}
