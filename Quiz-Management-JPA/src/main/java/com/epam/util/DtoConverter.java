package com.epam.util;

import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.dto.UserDto;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DtoConverter {
    @Autowired
    ModelMapper modelMapper;

    public QuestionDto convertToQuestionDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    public  Question convertToQuestion(QuestionDto questionDto) {
        return modelMapper.map(questionDto, Question.class);
    }

    public  QuizDto convertToQuizDto(Quiz quiz) {
        return modelMapper.map(quiz, QuizDto.class);
    }

    public Quiz convertToQuiz(QuizDto quizDto) {
        return modelMapper.map(quizDto, Quiz.class);
    }

    public UserDto convertToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public  User convertToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }


}
