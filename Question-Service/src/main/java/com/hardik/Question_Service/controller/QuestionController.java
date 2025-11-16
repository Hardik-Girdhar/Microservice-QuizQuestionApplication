package com.hardik.Question_Service.controller;

import com.hardik.Question_Service.model.Question;
import com.hardik.Question_Service.model.Response;
import com.hardik.Question_Service.model.WrapperQuestion;
import com.hardik.Question_Service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private Environment environment;

    // GET = http://localhost:8080/question/getQuestions
    @GetMapping("getQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    // GET = http://localhost:8080/question/getQuestions?category=Java
    @GetMapping(value = "getQuestions", params = "category")
    public ResponseEntity<List<Question>> getAllQuestionByParamCategory(@RequestParam String category){
        return questionService.getQuestionByCategory(category);
    }

    // GET = http://localhost:8080/question/getQuestions/Java
    @GetMapping("getQuestions/{category}")
    public ResponseEntity<List<Question>> getAllQuestionByPathCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    // POST = http://localhost:8080/question/addQuestion
    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    // PUT = http://localhost:8080/question/updateQuestion
    @PutMapping("updateQuestion")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    // PUT = http://localhost:8080/question/updateQuestion/21
    @PutMapping("updateQuestion/{id}")
    public ResponseEntity<String> updateQuestionById(@PathVariable int id, @RequestBody Question question){
        return questionService.updateQuestionById(id, question);
    }

    // DELETE = http://localhost:8080/question/deleteQuestion/21
    @DeleteMapping("deleteQuestion/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable int id){
        return questionService.deleteQuestion(id);
    }

    // GET = http://localhost:8080/question/generate
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestionIds(@RequestParam String category, @RequestParam int numQ){
        return questionService.generateQuestionIds(category, numQ);
    }

    // POST = http://localhost:8080/question/getQuestions
    @PostMapping("getQuestions")
    public ResponseEntity<List<WrapperQuestion>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }

    // POST = http://localhost:8080/question/getScore
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> response){
        return questionService.getScore(response);
    }
}
