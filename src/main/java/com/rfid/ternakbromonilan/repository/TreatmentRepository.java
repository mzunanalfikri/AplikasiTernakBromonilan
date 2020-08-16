package com.rfid.ternakbromonilan.repository;

import com.rfid.ternakbromonilan.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    List<Treatment> findByIdsapi(Long idsapi);
}
