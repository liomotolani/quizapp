package com.arissa.quizapp.service;


import com.arissa.quizapp.mapper.QuestionMapper;
import com.arissa.quizapp.model.Question;
import com.arissa.quizapp.dto.QuestionDTO;
import com.arissa.quizapp.model.Quiz;
import com.arissa.quizapp.model.Response;
import com.arissa.quizapp.repository.QuestionRepository;
import com.arissa.quizapp.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    private final QuestionRepository questionRepository;


    public ResponseEntity<Quiz> createQuiz(String category, int numberOfQuestion, String quizTitle){
        try {
            List<Question> questions = questionRepository.findRandomQuestionsByCategory(category,numberOfQuestion);
            Quiz quiz = new Quiz();
            quiz.setTitle(quizTitle);
            quiz.setQuestions(questions);
            quizRepository.save(quiz);
            return new ResponseEntity<>(quiz, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionDTO>> getQuiz(Integer id) {
        try {
            Optional<Quiz> quiz = quizRepository.findById(id);
            List<Question> questions = quiz.get().getQuestions();
            List<QuestionDTO> questionDTOS = QuestionMapper.INSTANCE.mapList(questions);
            return new ResponseEntity<>(questionDTOS, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Long> calculateResult(Integer id, List<Response> responses) {
        try {
            Quiz quiz = quizRepository.findById(id).get();
            List<Question> questions = quiz.getQuestions();
            long right = IntStream.range(0, responses.size())
                    .filter(i -> responses.get(i).getResponse().equals(questions.get(i).getRightAnswer()))
                    .count();
            return new ResponseEntity<>(right, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
