package com.headissue.fate.repository;

import com.headissue.fate.model.Aspect;
import com.headissue.fate.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AspectRepository extends JpaRepository<Aspect, Long> {


}
