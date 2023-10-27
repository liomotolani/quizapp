package com.arissa.quizapp.controller;

import com.arissa.quizapp.dto.QuestionDTO;
import com.arissa.quizapp.model.Quiz;
import com.arissa.quizapp.model.Response;
import com.arissa.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;


    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int numberOfQuestion,
                                                 @RequestParam String quizTitle)
    {
        return quizService.createQuiz(category, numberOfQuestion, quizTitle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuiz(@PathVariable Integer id){
        return quizService.getQuiz(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Long> calculateResult(@PathVariable int id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
}
