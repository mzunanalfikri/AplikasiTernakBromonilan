package com.rfid.ternakbromonilan.controller;

import com.rfid.ternakbromonilan.model.Sapi;
import com.rfid.ternakbromonilan.repository.SapiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private SapiRepository sapiRepository;

    @GetMapping("/test/tanggalbirahi")
    public String gettanggalbirahi(){
        Sapi sapi = sapiRepository.findById(1L).get();
        List<Date> tanggalIB = sapi.getTanggalBirahi();
        tanggalIB.add(Date.valueOf("2020-01-01"));
        tanggalIB.add(Date.valueOf("2020-01-02"));
        sapi.setTanggalBirahi(tanggalIB);
        sapiRepository.save(sapi);
        return "redirect:/sapi";
    }
}
