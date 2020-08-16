package com.rfid.ternakbromonilan.service;

import com.rfid.ternakbromonilan.dto.SapiDto;
import com.rfid.ternakbromonilan.model.Sapi;
import com.rfid.ternakbromonilan.repository.SapiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class SapiService {

    @Autowired
    private SapiRepository sapiRepository;

    public List<Sapi> getAllSapi(){
        return sapiRepository.findAll();
    }

    public Sapi getSapiById(Long id){
        return sapiRepository.findById(id).get();
    }
    public Page<Sapi> getSapiPagination(int page){
        return sapiRepository.findAll(PageRequest.of(page, 10));
    }

    public Sapi save(SapiDto sapiDto) throws Exception{
        Sapi sapi = new Sapi();
        sapi.setNomorSapi(sapiDto.getNomorSapi());
        sapi.setImage(sapiDto.getImageFile().getBytes());
        sapi.setJenisKelamin(sapiDto.getJenisKelamin());
        sapi.setJenisSapi(sapiDto.getJenisSapi());
        sapi.setNamaPemilik(sapiDto.getNamaPemilik());
        sapi.setNomorRegistrasiTernak(sapiDto.getNomorRegistrasiTernak());
        sapi.setTanggalLahir(sapiDto.getTanggalLahir());
        sapi.setTanggalTulis(sapiDto.getTanggalTulis());
        sapiRepository.save(sapi);
        return sapi;
    }
}
