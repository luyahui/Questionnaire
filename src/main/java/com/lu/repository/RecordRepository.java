package com.lu.repository;

import com.lu.model.Question;
import com.lu.model.Record;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecordRepository extends CrudRepository<Record, Long> {
    boolean existsByUuidAndQuestion(String uuid, Question q);

    List<Record> findByUuid(String uuid, Pageable pageable);

    List<Record> findByQuestion(Question question, Pageable pageable);
}
