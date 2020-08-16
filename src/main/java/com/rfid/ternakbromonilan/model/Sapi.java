package com.rfid.ternakbromonilan.model;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Entity(name = "sapi")
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class Sapi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nomorsapi")
    private Long nomorSapi;
    @Column(name = "nomorregistrasiternak")
    private String nomorRegistrasiTernak;
    @Column(name = "tanggaltulis")
    private Date tanggalTulis;
    @Column(name = "namapemilik")
    private String namaPemilik;
    @Column(name = "tanggallahir")
    private Date tanggalLahir;
    @Column(name = "jenissapi")
    private String jenisSapi;
    @Column(name = "jeniskelamin")
    private String jenisKelamin;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;

    @Type(type = "list-array")
    @Column(
            name = "tanggalib",
            columnDefinition = "date[]"
    )
    private List<Date> tanggalIB;

    @Type(type = "list-array")
    @Column(
            name = "kodestrawsemen",
            columnDefinition = "varchar(50)[]"
    )
    private List<String> kodeStrawSemen;

    @Type(type = "list-array")
    @Column(
            name = "tanggalbunting",
            columnDefinition = "date[]"
    )
    private List<Date> tanggalBunting;

    @Type(type = "list-array")
    @Column(
            name = "tanggalpemeriksaankebuntingan",
            columnDefinition = "date[]"
    )
    private List<Date> tanggalPemeriksaanKebuntingan;

    @Type(type = "list-array")
    @Column(
            name = "tanggalbirahi",
            columnDefinition = "tanggalbirahi[]"
    )
    private List<Date> tanggalBirahi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomorSapi() {
        return nomorSapi;
    }

    public void setNomorSapi(Long nomorSapi) {
        this.nomorSapi = nomorSapi;
    }

    public String getNomorRegistrasiTernak() {
        return nomorRegistrasiTernak;
    }

    public void setNomorRegistrasiTernak(String nomorRegistrasiTernak) {
        this.nomorRegistrasiTernak = nomorRegistrasiTernak;
    }

    public Date getTanggalTulis() {
        return tanggalTulis;
    }

    public void setTanggalTulis(Date tanggalTulis) {
        this.tanggalTulis = tanggalTulis;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getJenisSapi() {
        return jenisSapi;
    }

    public void setJenisSapi(String jenisSapi) {
        this.jenisSapi = jenisSapi;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKeliamin) {
        this.jenisKelamin = jenisKeliamin;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<Date> getTanggalIB() {
        if (tanggalIB == null){
            return new ArrayList<>();
        }
        return tanggalIB;
    }

    public void setTanggalIB(List<Date> tanggalIB) {
        this.tanggalIB = tanggalIB;
    }

    public List<String> getKodeStrawSemen() {
        if (kodeStrawSemen == null){
            return new ArrayList<>();
        }
        return kodeStrawSemen;
    }

    public void setKodeStrawSemen(List<String> kodeStrawSemen) {
        this.kodeStrawSemen = kodeStrawSemen;
    }

    public List<Date> getTanggalBunting() {
        if (tanggalBunting == null){
            return new ArrayList<>();
        }
        return tanggalBunting;
    }

    public void setTanggalBunting(List<Date> tanggalBunting) {
        this.tanggalBunting = tanggalBunting;
    }

    public List<Date> getTanggalPemeriksaanKebuntingan() {
        if (tanggalPemeriksaanKebuntingan == null){
            return new ArrayList<>();
        }
        return tanggalPemeriksaanKebuntingan;
    }

    public void setTanggalPemeriksaanKebuntingan(List<Date> tanggalPemeriksaanKebuntingan) {
        this.tanggalPemeriksaanKebuntingan = tanggalPemeriksaanKebuntingan;
    }

    public List<Date> getTanggalBirahi() {
        if (tanggalBirahi == null){
            return new ArrayList<>();
        }
        return tanggalBirahi;
    }

    public void setTanggalBirahi(List<Date> tanggalBirahi) {
        this.tanggalBirahi = tanggalBirahi;
    }

    public String getImageStringEncoded() throws UnsupportedEncodingException {
        return new String(Base64.getEncoder().encode(this.getImage()),"UTF-8");
    }

    @Override
    public String toString() {
        return "Sapi{" +
                "id=" + id +
                ", nomorSapi=" + nomorSapi +
                ", nomorRegistrasiTernak='" + nomorRegistrasiTernak + '\'' +
                ", tanggalTulis=" + tanggalTulis +
                ", namaPemilik='" + namaPemilik + '\'' +
                ", tanggalLahir=" + tanggalLahir +
                ", jenisSapi='" + jenisSapi + '\'' +
                ", jenisKelamin='" + jenisKelamin + '\'' +
                ", tanggalIB=" + tanggalIB +
                ", kodeStrawSemen=" + kodeStrawSemen +
                ", tanggalBunting=" + tanggalBunting +
                ", tanggalPemeriksaanKebuntingan=" + tanggalPemeriksaanKebuntingan +
                ", tanggalBirahi=" + tanggalBirahi +
                '}';
    }

    public String dateToString(Date date){
        LocalDate now = date.toLocalDate();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        String bulan = "";
        if (month == 1){
            bulan = "Januari";
        } else if(month == 2){
            bulan = "Februari";
        } else if (month == 3) {
            bulan = "Maret";
        } else if (month == 4){
            bulan = "April";
        } else if (month == 5){
            bulan = "Mei";
        } else if (month == 6){
            bulan = "Juni";
        } else if (month == 7){
            bulan = "Juli";
        } else if (month == 8){
            bulan = "Agustus";
        } else if (month == 9){
            bulan = "September";
        } else if (month == 10){
            bulan = "Oktober";
        } else if (month == 11){
            bulan = "November";
        } else if (month == 12){
            bulan = "Desember";
        }

        return String.valueOf(day) + " " + bulan + " " + String.valueOf(year);
    }

    public String getUmurSapi(){
        LocalDate lahir = this.tanggalLahir.toLocalDate();
        LocalDate saatini = LocalDate.now();
        Period period = Period.between(lahir, saatini);
        int tahun = period.getYears();
        int bulan = period.getMonths() % 12;
        return String.valueOf(bulan) + " bulan " + String.valueOf(tahun) + " tahun";
    }
}
