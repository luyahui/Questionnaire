package com.lu.controller;

import com.lu.service.QuestionService;
import com.lu.service.RecordService;
import com.lu.model.Question;
import com.lu.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping(path = "/record")
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

    @GetMapping(path = "/uuid/{uuid}")
    public ResponseEntity<Iterable> getByUuid(@PathVariable(name = "uuid") String uuid, @RequestParam(name = "pageNo", defaultValue = "0") int pageNo, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        List<Record> records = recordService.findByUuid(uuid, pageNo, pageSize);
        if (records.size() == 0)
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(records, HttpStatus.OK);
    }

    @GetMapping(path = "/qid/{qid}")
    public ResponseEntity<Iterable> getByQid(@PathVariable(name = "qid") long qid, @RequestParam(name = "pageNo", defaultValue = "0") int pageNo, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        List<Record> records = recordService.findByQid(qid, pageNo, pageSize);
        if (records.size() == 0)
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(records, HttpStatus.OK);
    }
}
