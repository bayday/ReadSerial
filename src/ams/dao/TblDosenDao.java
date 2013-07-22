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

import ams.entity.TblDosen;
import ams.entity.TblJurusan;
import ams.util.HibernateUtil;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class TblDosenDao {

    public void addDosen(TblDosen dosen) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(dosen);
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

    public void deleteDosen(int id_dosen) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            TblDosen dosen = (TblDosen) session.load(TblDosen.class, new Integer(id_dosen));
            session.delete(dosen);
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

    public void updateDosen(TblDosen dosen) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(dosen);
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

    public List<TblDosen> getAllDosen() {
        List<TblDosen> dosen = new ArrayList<TblDosen>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            dosen = session.createCriteria(TblDosen.class).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return dosen;
    }
    
    public TblDosen getDosenById(int iddosen) {
        TblDosen dosen = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            dosen = (TblDosen)session.createCriteria(TblDosen.class)
                    .add(Restrictions.eq("idDosen", iddosen))
                    .uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return dosen;
    }
    
    public TblDosen getTblDosByName(String nmdosen) {
        TblDosen dosen = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            dosen = (TblDosen)session.createCriteria(TblDosen.class)
                    .add(Restrictions.eq("namaDosen", nmdosen))
                    .uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return dosen;
    }
    
    public List<TblDosen> getCariDosen(String caridosen) {
        List<TblDosen> dosen = new ArrayList<TblDosen>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            dosen = session.createCriteria(TblDosen.class)
                    .add(Restrictions.or(
                        Restrictions.ilike("namaDosen", caridosen, MatchMode.ANYWHERE), 
                        Restrictions.or(
                            Restrictions.ilike("nip", caridosen, MatchMode.ANYWHERE), 
                            Restrictions.or(
                                Restrictions.ilike("tempatLahir", caridosen, MatchMode.ANYWHERE), 
                                Restrictions.or(
                                    Restrictions.ilike("jenisKelamin", caridosen, MatchMode.ANYWHERE), 
                                    Restrictions.or(
                                        Restrictions.ilike("pendidikan", caridosen, MatchMode.ANYWHERE), 
                                        Restrictions.or(
                                            Restrictions.ilike("gelar", caridosen, MatchMode.ANYWHERE),
                                            Restrictions.ilike("noKartu", caridosen, MatchMode.ANYWHERE))))))))
                    .list();
            //query.setString("nama_makul", carimakul)
            //makul = session.createQuery().list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return dosen;
    }
    
    public TblDosen getDosenByNo(String NoKartu) {
        TblDosen dosen = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            dosen = (TblDosen)session.createCriteria(TblDosen.class)
                    .add(Restrictions.eq("noKartu", NoKartu))
                    .uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return dosen;
    }
    
    public List<TblDosen> getDosenByJur(String jur) {
        List<TblDosen> dosen = new ArrayList<TblDosen>();
        TblJurusan jurus = new TblJurusan();
        TblJurusanDao jurdao = new TblJurusanDao();
        jurus = jurdao.getTblJurByName(jur);
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            dosen = session.createCriteria(TblDosen.class)
                    .add(Restrictions.eq("tblJurusan", jurus))
                    .createCriteria("tblJurusan")
                    .list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return dosen;
    }
}