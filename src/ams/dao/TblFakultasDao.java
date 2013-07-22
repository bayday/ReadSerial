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

import ams.entity.TblFakultas;
import ams.util.HibernateUtil;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class TblFakultasDao {

    public void addFakultas(TblFakultas fakultas) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(fakultas);
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

    public void deleteFakultas(int id_fakultas) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            TblFakultas fakultas = (TblFakultas) session.load(TblFakultas.class, new Integer(id_fakultas));
            session.delete(fakultas);
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

    public void updateFakultas(TblFakultas fakultas) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(fakultas);
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

    public List<TblFakultas> getAllFakultas() {
        List<TblFakultas> fakultas = new ArrayList<TblFakultas>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            fakultas = session.createCriteria(TblFakultas.class).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return fakultas;
    }

    public TblFakultas getFakultasById(int idfakultas) {
        TblFakultas fakultas = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            fakultas = (TblFakultas)session.createCriteria(TblFakultas.class)
                    .add(Restrictions.eq("idFakultas", idfakultas))
                    .uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return fakultas;
    }

    public TblFakultas getTblFakByName(String fak) {
        TblFakultas fakultas = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            fakultas = (TblFakultas)session.createCriteria(TblFakultas.class)
                    .add(Restrictions.eq("fakultas", fak))
                    .uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return fakultas;
    }

    public List<TblFakultas> getCariFakultas(String carifakultas) {
        List<TblFakultas> fakultas = new ArrayList<TblFakultas>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            fakultas = session.createCriteria(TblFakultas.class)
                    .add(Restrictions.ilike("fakultas", carifakultas, MatchMode.ANYWHERE))
                    .list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return fakultas;
    }

    public List<TblFakultas> getNamaFakultas() {
        List<TblFakultas> fakultas = new ArrayList<TblFakultas>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            fakultas = session.createCriteria(TblFakultas.class)
                    .setProjection(Projections.property("fakultas"))
                    .list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return fakultas;
    }
}
