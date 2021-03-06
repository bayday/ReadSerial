package ams.entity;
// Generated Jul 4, 2013 2:24:47 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TblJurusan generated by hbm2java
 */
@Entity
@Table(name="tbl_jurusan"
    ,catalog="ams_db"
)
public class TblJurusan  implements java.io.Serializable {


     private int idJurusan;
     private TblFakultas tblFakultas;
     private String jurusan;
     private Set tblJadwals = new HashSet(0);
     private Set tblDosens = new HashSet(0);
     private Set tblMakuls = new HashSet(0);

    public TblJurusan() {
    }

	
    public TblJurusan(int idJurusan, TblFakultas tblFakultas, String jurusan) {
        this.idJurusan = idJurusan;
        this.tblFakultas = tblFakultas;
        this.jurusan = jurusan;
    }
    public TblJurusan(int idJurusan, TblFakultas tblFakultas, String jurusan, Set tblJadwals, Set tblDosens, Set tblMakuls) {
       this.idJurusan = idJurusan;
       this.tblFakultas = tblFakultas;
       this.jurusan = jurusan;
       this.tblJadwals = tblJadwals;
       this.tblDosens = tblDosens;
       this.tblMakuls = tblMakuls;
    }
   
     @Id 
    
    @Column(name="id_jurusan", unique=true, nullable=false)
    public int getIdJurusan() {
        return this.idJurusan;
    }
    
    public void setIdJurusan(int idJurusan) {
        this.idJurusan = idJurusan;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_fakultas", nullable=false)
    public TblFakultas getTblFakultas() {
        return this.tblFakultas;
    }
    
    public void setTblFakultas(TblFakultas tblFakultas) {
        this.tblFakultas = tblFakultas;
    }
    
    @Column(name="jurusan", nullable=false, length=50)
    public String getJurusan() {
        return this.jurusan;
    }
    
    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tblJurusan")
    public Set getTblJadwals() {
        return this.tblJadwals;
    }
    
    public void setTblJadwals(Set tblJadwals) {
        this.tblJadwals = tblJadwals;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tblJurusan")
    public Set getTblDosens() {
        return this.tblDosens;
    }
    
    public void setTblDosens(Set tblDosens) {
        this.tblDosens = tblDosens;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tblJurusan")
    public Set getTblMakuls() {
        return this.tblMakuls;
    }
    
    public void setTblMakuls(Set tblMakuls) {
        this.tblMakuls = tblMakuls;
    }




}


