package com.lu.service;

import com.lu.model.Record;
import com.lu.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public Record save(Record record){
        return recordRepository.save(record);
    }
}
