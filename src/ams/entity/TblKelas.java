package ams.entity;
// Generated Jul 4, 2013 2:24:47 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TblKelas generated by hbm2java
 */
@Entity
@Table(name="tbl_kelas"
    ,catalog="ams_db"
)
public class TblKelas  implements java.io.Serializable {


     private Integer idKelas;
     private String kelas;
     private int angkatan;
     private Set tblJadwals = new HashSet(0);

    public TblKelas() {
    }

	
    public TblKelas(String kelas, int angkatan) {
        this.kelas = kelas;
        this.angkatan = angkatan;
    }
    public TblKelas(String kelas, int angkatan, Set tblJadwals) {
       this.kelas = kelas;
       this.angkatan = angkatan;
       this.tblJadwals = tblJadwals;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id_kelas", unique=true, nullable=false)
    public Integer getIdKelas() {
        return this.idKelas;
    }
    
    public void setIdKelas(Integer idKelas) {
        this.idKelas = idKelas;
    }
    
    @Column(name="kelas", nullable=false, length=15)
    public String getKelas() {
        return this.kelas;
    }
    
    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
    
    @Column(name="angkatan", nullable=false)
    public int getAngkatan() {
        return this.angkatan;
    }
    
    public void setAngkatan(int angkatan) {
        this.angkatan = angkatan;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tblKelas")
    public Set getTblJadwals() {
        return this.tblJadwals;
    }
    
    public void setTblJadwals(Set tblJadwals) {
        this.tblJadwals = tblJadwals;
    }




}


