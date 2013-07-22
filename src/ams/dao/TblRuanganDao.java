/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.dao;

import ams.entity.TblRuangan;
import ams.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Bayu
 */
public class TblRuanganDao {
    public void addRuangan(TblRuangan ruang) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(ruang);
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

    public void deleteRuangan(int id_ruang) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            TblRuangan user = (TblRuangan) session.load(TblRuangan.class, new Integer(id_ruang));
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

    public void updateRuangan(TblRuangan ruang) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(ruang);
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

    public List<TblRuangan> getAllRuangan() {
        List<TblRuangan> user = new ArrayList<TblRuangan>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            user = session.createCriteria(TblRuangan.class).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return user;
    }
    public TblRuangan getRuanganById(int idruang) {
        TblRuangan ruang = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            ruang = (TblRuangan)session.createCriteria(TblRuangan.class)
                    .add(Restrictions.eq("idRuangan", idruang))
                    .uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return ruang;
    }
    
    public TblRuangan getTblRuangByName(String rng) {
        TblRuangan ruang = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            ruang = (TblRuangan)session.createCriteria(TblRuangan.class)
                    .add(Restrictions.eq("namaRuangan", rng))
                    .uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return ruang;
    }
    
    public List<TblRuangan> getCariRuangan(String cariruang) {
        List<TblRuangan> ruang = new ArrayList<TblRuangan>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            ruang = session.createCriteria(TblRuangan.class)
                    .add(Restrictions.ilike("namaRuangan", cariruang, MatchMode.ANYWHERE))
                    .list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return ruang;
    }
}
