package com.kaffle.kaffle.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<DictionaryEntity, Long>{

    List<DictionaryEntity> findAllByJa1AndJa2AndJa3(String ja1, String ja2, String ja3);

    List<DictionaryEntity> findAllByBaseword(String word);
    List<DictionaryEntity> findAllByJamo(String word);
    
}

