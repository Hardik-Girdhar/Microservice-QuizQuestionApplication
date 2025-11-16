package com.hardik.Question_Service.repo;

import com.hardik.Question_Service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
    public List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id from Question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    public List<Integer> findByCategoryNumQ(String category, int numQ);
}
