/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.dao;

import ams.entity.TblFakultas;
import ams.dao.TblFakultasDao;
import ams.entity.TblJurusan;
import ams.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Bayu
 */
public class TblJurusanDao {
    public void addJurusan(TblJurusan jurusan) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(jurusan);
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

    public void deleteJurusan(int id_jurusan) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            TblJurusan jurusan = (TblJurusan) session.load(TblJurusan.class, new Integer(id_jurusan));
            session.delete(jurusan);
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

    public void updateJurusan(TblJurusan jurusan) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(jurusan);
//            session.update(jurusan.getTblFakultas());
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

    public List<TblJurusan> getAllJurusan() {
        List<TblJurusan> jurusan = new ArrayList<TblJurusan>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            jurusan = session.createCriteria(TblJurusan.class)
                    .createCriteria("tblFakultas")
                    .list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return jurusan;
    }
    public TblJurusan getJurusanById(int idjurusan) {
        TblJurusan jurusan = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            jurusan = (TblJurusan) session.createCriteria(TblJurusan.class)
                    .add(Restrictions.eq("idJurusan", idjurusan))
                    .createCriteria("tblFakultas")
                    .uniqueResult();
//            dats.
//            String queryString = "from TblJurusan where id_jurusan = :id_jurusan";
//            Query query = session.createQuery(queryString);
//            query.setInteger("id_jurusan", idjurusan);
//            jurusan = (TblJurusan) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return jurusan;
    }
    
     public TblJurusan getTblJurByName(String jur) {
        TblJurusan jurusan = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            jurusan = (TblJurusan)session.createCriteria(TblJurusan.class)
                    .add(Restrictions.eq("jurusan", jur))
                    .uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return jurusan;
    }
    
    public List<TblJurusan> getCariJurusan(String carijurusan) {
        List<TblJurusan> jurusan = new ArrayList<TblJurusan>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            jurusan = session.createCriteria(TblJurusan.class)
                    .add(Restrictions.ilike("jurusan", carijurusan, MatchMode.ANYWHERE))
                    .createCriteria("tblFakultas")
                    .list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return jurusan;
    }
    public List<TblJurusan> getJurusanByFak(String fak) {
        List<TblJurusan> jurusan = new ArrayList<TblJurusan>();
        TblFakultas fakul = new TblFakultas();
        TblFakultasDao fakdao = new TblFakultasDao();
        fakul = fakdao.getTblFakByName(fak);
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            //TblFakultas fakultas = session.createCriteria(TblFakultas.class).add(null);
            //TblFakultas fakultas = (TblFakultas)session.createCriteria(TblFakultas.class).uniqueResult();
            jurusan = session.createCriteria(TblJurusan.class)
                    .add(Restrictions.eq("tblFakultas", fakul))
                    .createCriteria("tblFakultas")
                    .list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return jurusan;
    }
}
