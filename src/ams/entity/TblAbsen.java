package ams.entity;
// Generated Jul 4, 2013 2:24:47 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TblAbsen generated by hbm2java
 */
@Entity
@Table(name = "tbl_absen", catalog = "ams_db")
public class TblAbsen implements java.io.Serializable {

    private Integer idAbsen;
    private TblJadwal tblJadwal;
    private Date absenMulai;
    private Date absenSelesai;

    public TblAbsen() {
    }

    public TblAbsen(TblJadwal tblJadwal) {
        this.tblJadwal = tblJadwal;
    }

    public TblAbsen(TblJadwal tblJadwal, Date absenMulai, Date absenSelesai) {
        this.tblJadwal = tblJadwal;
        this.absenMulai = absenMulai;
        this.absenSelesai = absenSelesai;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_absen", unique = true, nullable = false)
    public Integer getIdAbsen() {
        return this.idAbsen;
    }

    public void setIdAbsen(Integer idAbsen) {
        this.idAbsen = idAbsen;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jadwal", nullable = false)
    public TblJadwal getTblJadwal() {
        return this.tblJadwal;
    }

    public void setTblJadwal(TblJadwal tblJadwal) {
        this.tblJadwal = tblJadwal;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "absen_mulai", length = 19)
    public Date getAbsenMulai() {
        return this.absenMulai;
    }

    public void setAbsenMulai(Date absenMulai) {
        this.absenMulai = absenMulai;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "absen_selesai", length = 19)
    public Date getAbsenSelesai() {
        return this.absenSelesai;
    }

    public void setAbsenSelesai(Date absenSelesai) {
        this.absenSelesai = absenSelesai;
    }
}