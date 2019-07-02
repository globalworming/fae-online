package com.headissue.fate.repository;

import com.headissue.fate.model.Mook;
import com.headissue.fate.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MookRepository extends JpaRepository<Mook, Long> {

  List<Mook> findByBelongingTo(Actor actor);


}
