/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.dao;

import ams.entity.TblKelas;
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
public class TblKelasDao {
    public void addKelas(TblKelas kelas) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(kelas);
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

    public void deleteKelas(int id_kelas) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            TblKelas kelas = (TblKelas) session.load(TblKelas.class, new Integer(id_kelas));
            session.delete(kelas);
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

    public void updateKelas(TblKelas kelas) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(kelas);
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

    public List<TblKelas> getAllKelas() {
        List<TblKelas> fakultas = new ArrayList<TblKelas>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            fakultas = session.createCriteria(TblKelas.class).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return fakultas;
    }

    public TblKelas getKelasById(int idkelas) {
        TblKelas kelas = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            kelas = (TblKelas)session.createCriteria(TblKelas.class)
                    .add(Restrictions.eq("idKelas", idkelas))
                    .uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return kelas;
    }

    public TblKelas getTblKlsByName(String kls) {
        TblKelas kelas = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            kelas = (TblKelas)session.createCriteria(TblKelas.class)
                    .add(Restrictions.eq("kelas", kls))
                    .uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return kelas;
    }

    public List<TblKelas> getCariKelas(String carikelas) {
        List<TblKelas> kelas = new ArrayList<TblKelas>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            kelas = session.createCriteria(TblKelas.class)
                    .add(Restrictions.or(
                        Restrictions.ilike("kelas", carikelas,MatchMode.ANYWHERE), 
                        Restrictions.ilike("angkatan", carikelas, MatchMode.ANYWHERE)))
                    .list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return kelas;
    }

    public List<TblKelas> getNamaKelas() {
        List<TblKelas> kelas = new ArrayList<TblKelas>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            kelas = session.createCriteria(TblKelas.class).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return kelas;
    }
}
