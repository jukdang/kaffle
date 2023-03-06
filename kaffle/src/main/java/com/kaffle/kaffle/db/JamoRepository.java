package com.kaffle.kaffle.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JamoRepository extends JpaRepository<JamoEntity, Long>{
}

