package com.headissue.fate.repository;

import com.headissue.fate.model.Message;
import com.headissue.fate.model.World;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorldRepository extends JpaRepository<World, Long> {

  World findByName(String name);


}
