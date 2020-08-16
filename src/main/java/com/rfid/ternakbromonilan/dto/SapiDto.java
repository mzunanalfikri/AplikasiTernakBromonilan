package com.rfid.ternakbromonilan.dto;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

public class SapiDto {
    private Long nomorSapi;
    private String nomorRegistrasiTernak;
    private Date tanggalTulis;
    private String namaPemilik;
    private Date tanggalLahir;
    private String jenisSapi;
    private String jenisKelamin;
    private byte[] image;
    private MultipartFile imageFile;

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

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    @Override
    public String toString() {
        return "SapiDto{" +
                "nomorSapi=" + nomorSapi +
                ", nomorRegistrasiTernak='" + nomorRegistrasiTernak + '\'' +
                ", tanggalTulis='" + tanggalTulis + '\'' +
                ", namaPemilik='" + namaPemilik + '\'' +
                ", tanggalLahir='" + tanggalLahir + '\'' +
                ", jenisSapi='" + jenisSapi + '\'' +
                ", jenisKelamin='" + jenisKelamin + '\'' +
                '}';
    }
}
