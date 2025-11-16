package com.hardik.Quiz_Service.controller;

import com.hardik.Quiz_Service.model.QuizDto;
import com.hardik.Quiz_Service.model.Response;
import com.hardik.Quiz_Service.model.WrapperQuestion;
import com.hardik.Quiz_Service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // POST = http://localhost:8090/quiz/createQuiz
    @PostMapping("createQuiz")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategory() , quizDto.getNumQ(), quizDto.getTitle());
    }

    // GET = http://localhost:8090/quiz/getQuizById/0
    @GetMapping("getQuizById/{id}")
    public ResponseEntity<List<WrapperQuestion>> getQuizQuestion(@PathVariable int id){
        return quizService.getQuizQuestion(id);
    }

    // POST = http://localhost:8090/quiz/submit/0
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> calculateResult(@PathVariable int id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
}
