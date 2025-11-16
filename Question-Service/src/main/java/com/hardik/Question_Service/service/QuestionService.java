package com.hardik.Question_Service.service;

import com.hardik.Question_Service.model.Question;
import com.hardik.Question_Service.model.Response;
import com.hardik.Question_Service.model.WrapperQuestion;
import com.hardik.Question_Service.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionRepo.save(question);
        return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        if(questionRepo.existsById(question.getId())){
            questionRepo.save(question);
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("No Question found to update",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestionById(int id, Question question) {
        if(questionRepo.existsById(id)){
            question.setId(id);
            questionRepo.save(question);
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("No Question found to update",HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> deleteQuestion(int id) {
        if(questionRepo.existsById(id)){
            questionRepo.deleteById(id);
            return new ResponseEntity<>("Question Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("No Question found to delete", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> generateQuestionIds(String category, int numQ) {
        List<Integer> questionIds = questionRepo.findByCategoryNumQ(category, numQ);
        return new ResponseEntity<>(questionIds, HttpStatus.OK);
    }

    public ResponseEntity<List<WrapperQuestion>> getQuestionsFromId(List<Integer> questionIds) {
        List<WrapperQuestion> questionsForClient = new ArrayList<>();
        List<Question> questionsFromIds = new ArrayList<>();
        for(Integer id : questionIds){
            questionsFromIds.add(questionRepo.findById(id).get());
        }

        for(Question que : questionsFromIds){
            WrapperQuestion wq = new WrapperQuestion();
            wq.setId(que.getId());
            wq.setQuestiontitle(que.getQuestiontitle());
            wq.setOption1(que.getOption1());
            wq.setOption2(que.getOption2());
            wq.setOption3(que.getOption3());
            wq.setOption4(que.getOption4());
            questionsForClient.add(wq);
        }

        return new ResponseEntity<>(questionsForClient, HttpStatus.CREATED);
    }

    public ResponseEntity<Integer> getScore(List<Response> response) {
        int right = 0;
        for(Response resp : response){
            Question question = questionRepo.findById(resp.getId()).get();
            if(resp.getResponse().equals(question.getRightanswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
