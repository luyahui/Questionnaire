package com.lu.service;

import com.lu.model.Record;
import com.lu.repository.QuestionRepository;
import com.lu.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Record save(Record record) {
        return recordRepository.save(record);
    }

    public boolean exists(String uuid, long qid) {
        if (!questionRepository.existsById(qid))
            return false;
        return recordRepository.existsByUuidAndQuestion(uuid, questionRepository.findById(qid).get());
    }

    public Page<Record> findByUuid(String uuid, int pageNo, int pageSize){
        return recordRepository.findByUuid(uuid, PageRequest.of(pageNo,pageSize));
    }

    public Page<Record> findByQid(long qid, int pageNo, int pageSize){
        if(!questionRepository.existsById(qid))
            return null;
        return recordRepository.findByQuestion(questionRepository.findById(qid).get(), PageRequest.of(pageNo, pageSize));
    }
}

