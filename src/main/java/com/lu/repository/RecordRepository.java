package com.lu.repository;

import com.lu.model.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<Record, Long> {
}
