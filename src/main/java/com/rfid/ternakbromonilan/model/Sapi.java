package com.rfid.ternakbromonilan.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity(name = "sapi")
public class Sapi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomorRegistrasiTernak;
    private String tanggalTulis;
    private String namaPemilik;
    private String tanggalLahir;
    private String jenisSapi;
    private String jenisKeliamin;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;

//    d serial primary key,
//    nomorRegistrasiTernak varchar(50),
//    tanggalTulis date,
//    namaPemilik varchar(255),
//    tanggalLahir date,
//    jenisSapi varchar(50),
//    jenisKelamin varchar(50),
//    image bytea

}
