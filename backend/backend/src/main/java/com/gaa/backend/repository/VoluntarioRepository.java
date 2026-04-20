package com.gaa.backend.repository;

import com.gaa.backend.model.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
    boolean existsByCpf(String cpf);
}