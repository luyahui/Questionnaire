package com.lu.repository;

import com.lu.model.Question;
import com.lu.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<Record, Long> {
    boolean existsByUuidAndQuestion(String uuid, Question q);

    Page<Record> findByUuid(String uuid, Pageable pageable);

    Page<Record> findByQuestion(Question question, Pageable pageable);
}
