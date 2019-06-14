package com.headissue.fate.repository;

import com.headissue.fate.model.Player;
import com.headissue.fate.model.World;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {


}
