package com.itsqmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.itsqmet.entity.Rol;
@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}
