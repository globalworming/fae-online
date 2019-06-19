package com.headissue.fate.repository;

import com.headissue.fate.model.Message;
import com.headissue.fate.model.World;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Long> {

  List<Message> findByWorld(World world);
}
