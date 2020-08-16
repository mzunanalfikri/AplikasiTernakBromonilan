package com.rfid.ternakbromonilan.repository;

import com.rfid.ternakbromonilan.model.Sapi;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface SapiRepository extends JpaRepository<Sapi, Long> {
    List<Sapi> findAllByNomorSapi(Long nomorSapi);
}
