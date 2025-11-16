package com.hardik.Quiz_Service.feign;

import com.hardik.Quiz_Service.model.Response;
import com.hardik.Quiz_Service.model.WrapperQuestion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> generateQuestionIds(@RequestParam String category, @RequestParam int numQ);

    // POST = http://localhost:8080/question/getQuestions
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<WrapperQuestion>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    // POST = http://localhost:8080/question/getScore
    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> response);
}
