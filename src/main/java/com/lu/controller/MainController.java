package com.lu.controller;

import com.lu.model.Question;
import com.lu.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping(path = "/question/get")
    private Question getAQuestion(){
        Question q = new Question();
        q.setDescription("How old are you?");
        return q;
    }
}
