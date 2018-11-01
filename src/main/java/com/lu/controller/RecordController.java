package com.lu.controller;

import com.lu.service.QuestionService;
import com.lu.service.RecordService;
import com.lu.model.Question;
import com.lu.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private QuestionService questionService;

    @PostMapping(path = "/answer/{qid}")
    public ResponseEntity<Record> addNew(@PathVariable(name = "qid") long qid, @RequestBody Record record) {
        Question q = questionService.find(qid);
        if (q == null){
            Record r = null;
            return new ResponseEntity<Record>(r, HttpStatus.BAD_REQUEST);
        }

        record.setQuestion(q);
        recordService.save(record);

        return new ResponseEntity<>(record, HttpStatus.CREATED);
    }
}
