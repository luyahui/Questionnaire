package com.lu.controller;

import com.lu.service.QuestionService;
import com.lu.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(path = "/random")
    public ResponseEntity<Question> getAQuestion(@RequestParam(name = "uuid", defaultValue = "") String uuid) {
        Question q = questionService.getRandom(uuid);
        if (q != null)
            return new ResponseEntity<Question>(q, HttpStatus.OK);
        else
            return new ResponseEntity<Question>(q, HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Question> getById(@PathVariable(name = "id") long id) {
        Question q = questionService.find(id);
        if (q != null)
            return new ResponseEntity<Question>(q, HttpStatus.OK);
        else
            return new ResponseEntity<Question>(q, HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Iterable> getAll(@RequestParam(name = "pageNo", defaultValue = "0") int pageNo, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        Page<Question> questions = questionService.findAll(pageNo, pageSize);
        if(!questions.hasContent())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(questions, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public HttpStatus addNewQuestion(@RequestBody Question nQuestion) {
        if (questionService.save(nQuestion) != null)
            return HttpStatus.CREATED;
        else
            return HttpStatus.BAD_REQUEST;
    }

    @PutMapping(path = "/edit/{id}")
    public HttpStatus updateQuestion(@PathVariable(name = "id") long id, @RequestBody Question question) {
        if (!questionService.exists(id))
            return HttpStatus.BAD_REQUEST;
        question.setId(id);
        if (questionService.save(question) != null)
            return HttpStatus.OK;
        else
            return HttpStatus.BAD_REQUEST;
    }

    @DeleteMapping(path = "/delete/{id}")
    public HttpStatus deleteQuestion(@PathVariable(name = "id") long id) {
        if (!questionService.exists(id))
            return HttpStatus.BAD_REQUEST;
        questionService.delete(id);
        return HttpStatus.OK;
    }
}
