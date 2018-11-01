package com.lu.service;

import com.lu.model.Question;
import com.lu.repository.QuestionRepository;
import com.lu.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private RecordRepository recordRepository;

    public Question getRandom(String uuid) {
        long qty = questionRepository.count();

        Question q = null;
        // we will try ten times to get a random question, which has not been answered by this user
        for(int i = 0; i < 10; i++) {
            int idx = (int) (Math.random() * qty);
            Page<Question> qPage = questionRepository.findAll(PageRequest.of(idx, 1));
            if (qPage.hasContent())
                q = qPage.getContent().get(0);
            if(q != null && !recordRepository.existsByUuidAndQuestion(uuid, q))
                break;
            else
                q = null;
        }
        return q;
    }

    public Question find(long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public boolean exists(long id){
        return questionRepository.existsById(id);
    }

    public Question save(Question q) {
        return questionRepository.save(q);
    }

    public void delete(long id){
        questionRepository.deleteById(id);
    }

}
