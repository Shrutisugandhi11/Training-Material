package com.epam.service;


import com.epam.dao.QuestionDao;
import com.epam.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public String create(Question question) {
        return questionDao.create(question);
    }

    public Question update(int id, String updateString) {
        return questionDao.update(id, updateString);
    }

    public Question updateQuestion(int id, String updateString, int choice) {
        return questionDao.updateQuestion(id, updateString, choice);
    }

    public Question updateOption(int id, String updateString, int choice) {
        return questionDao.updateOptions(id, updateString, choice);
    }

    public String delete(int removeId) {
        return questionDao.delete(removeId);
    }

    public List<Question> read() {
        return questionDao.read();
    }

}
