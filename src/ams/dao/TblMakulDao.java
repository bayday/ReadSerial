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

import ams.entity.TblMakul;
import ams.util.HibernateUtil;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class TblMakulDao {

    public void addMakul(TblMakul makul) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(makul);
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

    public void deleteMakul(String id_makul) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            TblMakul makul = (TblMakul) session.load(TblMakul.class, new String(id_makul));
            session.delete(makul);
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

    public void updateMakul(TblMakul makul) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(makul);
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

    public List<TblMakul> getAllMakul() {
        List<TblMakul> makul = new ArrayList<TblMakul>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            //makul = session.createQuery("from TblMakul").list();
            makul = session.createCriteria(TblMakul.class)
                    .list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return makul;
    }
    
    public TblMakul getMakulById(String idmakul) {
        TblMakul makul = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            makul = (TblMakul) session.createCriteria(TblMakul.class)
                    .add(Restrictions.eq("idMakul", idmakul))
                    .uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return makul;
    }
    
    public TblMakul getTblMklByName(String matakul) {
        TblMakul makul = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            makul = (TblMakul)session.createCriteria(TblMakul.class)
                    .add(Restrictions.eq("mataKuliah", matakul))
                    .uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return makul;
    }
    
    public List<TblMakul> getCariMakul(String carimakul) {
        List<TblMakul> makul = new ArrayList<TblMakul>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            makul = session.createCriteria(TblMakul.class)
                    .add(Restrictions.ilike("mataKuliah", carimakul, MatchMode.ANYWHERE))
                    .list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return makul;
    }
}
