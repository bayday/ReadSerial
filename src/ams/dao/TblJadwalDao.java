/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.dao;

/**
 *
 * @author Bayu
 */
import ams.entity.TblDosen;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ams.entity.TblJadwal;
import ams.util.HibernateUtil;
import java.sql.Time;
import java.util.Date;
import org.hibernate.*;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

public class TblJadwalDao {

    public void addJadwal(TblJadwal jadwal) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(jadwal);
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

    public void deleteJadwal(int id_jadwal) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            TblJadwal jadwal = (TblJadwal) session.load(TblJadwal.class, new Integer(id_jadwal));
            session.delete(jadwal);
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

    public void updateJadwal(TblJadwal jadwal) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(jadwal);
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

    public List<TblJadwal> getAllJadwal() {
        List<TblJadwal> jadwal = new ArrayList<TblJadwal>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            jadwal = session.createCriteria(TblJadwal.class).list();

            for (TblJadwal jad : jadwal) {
                Hibernate.initialize(jad.getTblDosen());
                Hibernate.initialize(jad.getTblMakul());
                Hibernate.initialize(jad.getTblRuangan());
                Hibernate.initialize(jad.getTblKelas());
                Hibernate.initialize(jad.getTblJurusan());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return jadwal;
    }

    public TblJadwal getJadwalById(int idjadwal) {
        TblJadwal jadwal = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            jadwal = (TblJadwal) session.createCriteria(TblJadwal.class).add(Restrictions.eq("idJadwal", idjadwal)).uniqueResult();
            //for (TblJadwal jad : jadwal) {
            Hibernate.initialize(jadwal.getTblDosen());
            Hibernate.initialize(jadwal.getTblMakul());
            Hibernate.initialize(jadwal.getTblRuangan());
            Hibernate.initialize(jadwal.getTblKelas());
            Hibernate.initialize(jadwal.getTblJurusan());
            //}
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return jadwal;
    }

    public TblJadwal TblJadwalByDosenHari(TblDosen dosen, int hari) {
        Transaction trns = null;
        TblJadwal jadwal = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            jadwal = (TblJadwal) session.createCriteria(TblJadwal.class).add(Restrictions.eq("tblDosen", dosen)).add(Restrictions.eq("hari", hari)).uniqueResult();
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
        return jadwal;
    }

    public List<TblJadwal> getCariJadwal(String carijadwal) {
        List<TblJadwal> jadwal = new ArrayList<TblJadwal>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            jadwal = session.createCriteria(TblJadwal.class).add(Restrictions.ilike("makul", carijadwal)).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return jadwal;
    }

//    public TblJadwal getJadwalByDosen(TblDosen dosen) {
//        //System.out.println("Dorrr : "+NoKartu);
//        Transaction trns = null;
//        TblJadwal jadwal = null;
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            trns = session.beginTransaction();
//            jadwal = (TblJadwal) session.createCriteria(TblJadwal.class)
//                    .add(Restrictions.eq("tblDosen", dosen))
//                    .add(Restrictions.eq("hari", 1)).uniqueResult();
//            session.getTransaction().commit();
//        } catch (RuntimeException e) {
//            if (trns != null) {
//                trns.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.flush();
//            session.close();
//        }
//        return jadwal;
//    }
    public List<TblJadwal> getCekJadwal(TblDosen dosen, int hari) {
        List<TblJadwal> jadwal = new ArrayList<TblJadwal>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            jadwal = session.createCriteria(TblJadwal.class).add(Restrictions.eq("tblDosen", dosen)).add(Restrictions.eq("hari", hari)).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return jadwal;
    }

    public TblJadwal getJadwalAbsen(TblDosen dosen, int hari) {
        //System.out.println("Dorrr : "+NoKartu);
        Transaction trns = null;
        TblJadwal jadwal = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String Query = "from TblJadwal where "
                    + "id_dosen=" + dosen.getIdDosen() + " "
                    + "and hari=" + hari + " "
                    + "and timediff(jam_mulai,'00:15:00') <= time(now()) "
                    + "and timediff(time(now()),'00:15:00') <= jam_mulai";
            jadwal = (TblJadwal) session.createQuery(Query).uniqueResult();

            session.getTransaction().commit();
            if (jadwal != null) {
                Hibernate.initialize(jadwal.getTblDosen());
                Hibernate.initialize(jadwal.getTblMakul());
                Hibernate.initialize(jadwal.getTblRuangan());
                Hibernate.initialize(jadwal.getTblKelas());
                Hibernate.initialize(jadwal.getTblJurusan());
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
        return jadwal;
    }
}
