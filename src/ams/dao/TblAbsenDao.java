/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.dao;

/**
 *
 * @author Bayu
 */
import ams.entity.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ams.util.HibernateUtil;
import java.util.*;
import javax.persistence.JoinTable;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Join;

public class TblAbsenDao {

    public void addAbsen(TblAbsen absen) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(absen);
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

    public void deleteAbsen(int id_absen) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            TblAbsen absen = (TblAbsen) session.load(TblAbsen.class, new Integer(id_absen));
            session.delete(absen);
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

    public void updateAbsen(TblAbsen absen) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(absen);
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

    public List<TblAbsen> getAllAbsen() {
        List<TblAbsen> absen = new ArrayList<TblAbsen>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            absen = session.createCriteria(TblAbsen.class)
                    .list();
            for (TblAbsen jad : absen) {
                Hibernate.initialize(jad.getTblJadwal());
                Hibernate.initialize(jad.getTblJadwal().getTblJurusan().getTblFakultas());
                Hibernate.initialize(jad.getTblJadwal().getTblJurusan());
                Hibernate.initialize(jad.getTblJadwal().getTblDosen());
                Hibernate.initialize(jad.getTblJadwal().getTblMakul());
                Hibernate.initialize(jad.getTblJadwal().getTblRuangan());
                Hibernate.initialize(jad.getTblJadwal().getTblKelas());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return absen;
    }
    
    public List<TblAbsen> getAbsen(){
        List<TblAbsen> absen = new ArrayList<TblAbsen>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            absen = session.createCriteria(TblAbsen.class)
                    .setFetchMode("idJadwal", FetchMode.JOIN)
                    .createCriteria("tblJadwal")
                    .add(Restrictions.eq("hari", new Date().getDay()))
                    .list();
            for (TblAbsen jad : absen) {
                Hibernate.initialize(jad.getTblJadwal());
                Hibernate.initialize(jad.getTblJadwal().getTblDosen());
                Hibernate.initialize(jad.getTblJadwal().getTblMakul());
                Hibernate.initialize(jad.getTblJadwal().getTblRuangan());
                Hibernate.initialize(jad.getTblJadwal().getTblKelas());
            }
//            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return absen;
    }

    public TblAbsen Absen(String NoKartu) {
        Transaction trns = null;
        TblAbsen absen = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            absen = (TblAbsen) session.createCriteria(TblAbsen.class).add(Restrictions.eq("tblJadwal.tblDosen.idDosen", NoKartu)).uniqueResult();
            //session.save(absen);
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
        return absen;
    }

    public TblAbsen getCekAbsenAda(TblDosen dosen, int hari) {
        Transaction trns = null;
        int ret = 0;
        TblAbsen absen = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();      
            absen = (TblAbsen) session.createCriteria(TblAbsen.class)
                    .add(Restrictions.isNull("absenSelesai"))
                    .setFetchMode("idJadwal", FetchMode.SELECT)
                    .createCriteria("tblJadwal")
                    .add(Restrictions.eq("tblDosen", dosen))
                    .add(Restrictions.eq("hari",hari))
                    .uniqueResult();
                    
            session.getTransaction().commit();
            if(absen!=null){
                Hibernate.initialize(absen.getTblJadwal());
                Hibernate.initialize(absen.getTblJadwal().getTblDosen());
                Hibernate.initialize(absen.getTblJadwal().getTblMakul());
                Hibernate.initialize(absen.getTblJadwal().getTblRuangan());
                Hibernate.initialize(absen.getTblJadwal().getTblKelas());
                Hibernate.initialize(absen.getTblJadwal().getTblJurusan());
            }
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return absen;
    }

    public TblAbsen getAbsenSelesai(TblJadwal jadwal) {
        Transaction trns = null;
        TblAbsen absen = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            absen = (TblAbsen) session.createCriteria(TblAbsen.class).add(Restrictions.eq("tblJadwal", jadwal)).uniqueResult();
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
        return absen;
    }
    public List<TblAbsen> getCariAbsen(String fak,String jur,String dos,Date mulai,Date selesai){
        Transaction trns = null;
        List<TblAbsen> absen = new ArrayList<TblAbsen>();
        
        TblFakultasDao fakdao = new TblFakultasDao();
        TblFakultas fakul = fakdao.getTblFakByName(fak);
        
        TblJurusanDao jurdao = new TblJurusanDao();
        TblJurusan jurus = jurdao.getTblJurByName(jur);
        
        TblDosenDao dosendao = new TblDosenDao();
        TblDosen dosen = dosendao.getTblDosByName(dos);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            absen = session.createCriteria(TblAbsen.class)
                    .add(Restrictions.between("absenMulai", mulai, selesai))
                    .createCriteria("tblJadwal")
                    .add(Restrictions.eq("tblDosen", dosen))
                    .add(Restrictions.eq("tblJurusan", jurus))
                    .createCriteria("tblJurusan")
                    .add(Restrictions.eq("tblFakultas", fakul))
                    .list();
            session.getTransaction().commit();
            System.out.println(Hibernate.isInitialized(absen));
         for (TblAbsen jad : absen) {
                Hibernate.initialize(jad.getTblJadwal());
                Hibernate.initialize(jad.getTblJadwal().getTblJurusan());
                Hibernate.initialize(jad.getTblJadwal().getTblJurusan().getTblFakultas());
                Hibernate.initialize(jad.getTblJadwal().getTblDosen());
                Hibernate.initialize(jad.getTblJadwal().getTblMakul());
                Hibernate.initialize(jad.getTblJadwal().getTblRuangan());
                Hibernate.initialize(jad.getTblJadwal().getTblKelas());
            }
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return absen;
    }
}
