package com.lu.repository;

import com.lu.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    Page<Question> findAll(Pageable pageable);
}
