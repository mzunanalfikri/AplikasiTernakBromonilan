package com.rfid.ternakbromonilan.repository;

import com.rfid.ternakbromonilan.model.Penyakit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PenyakitRepository extends JpaRepository<Penyakit, Long> {
    List<Penyakit> findByIdsapi(Long idsapi);
}
