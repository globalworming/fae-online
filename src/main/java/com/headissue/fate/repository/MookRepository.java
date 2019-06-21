package com.headissue.fate.repository;

import com.headissue.fate.model.Mook;
import com.headissue.fate.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MookRepository extends JpaRepository<Mook, Long> {


}
