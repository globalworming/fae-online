package com.headissue.fate.repository;

import com.headissue.fate.model.Mook;
import com.headissue.fate.model.Player;
import com.headissue.fate.model.World;
import com.headissue.fate.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MookRepository extends JpaRepository<Mook, Long> {

  List<Mook> findByBelongingTo(Character character);


}
