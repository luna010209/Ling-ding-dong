package com.example.lingdingdong.repo;

import com.example.lingdingdong.entity.auth.Auth;
import com.example.lingdingdong.entity.sentence.MySentence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MySentenceRepo extends JpaRepository<MySentence, Long> {
    List<MySentence> findByUser(Auth user);
}
