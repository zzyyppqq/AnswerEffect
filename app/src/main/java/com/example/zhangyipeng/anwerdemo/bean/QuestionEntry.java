package com.example.zhangyipeng.anwerdemo.bean;

import java.util.List;

/**
 * Created by zhangyipeng on 16/7/27.
 */
public class QuestionEntry {

    /**
     * explain : “违反道路交通安全法”，违反法律法规即为违法行为。官方已无违章/违规的说法。
     * question : 驾驶机动车在道路上违反道路交通安全法的行为，属于什么行为？
     * questionid : 1
     * mediatype : 0
     * mediacontent :
     * optiontype : 1
     * answer : 2
     * chapterid : 1
     * answer_arr : ["2"]
     * answers : ["违章行为","违法行为","过失行为","违规行为"]
     */

    private String explain;
    private String question;
    private String questionid;
    private String mediatype;
    private String mediacontent;
    private String optiontype;
    private String answer;
    private String chapterid;
    private List<String> answer_arr;
    private List<String> answers;

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getMediatype() {
        return mediatype;
    }

    public void setMediatype(String mediatype) {
        this.mediatype = mediatype;
    }

    public String getMediacontent() {
        return mediacontent;
    }

    public void setMediacontent(String mediacontent) {
        this.mediacontent = mediacontent;
    }

    public String getOptiontype() {
        return optiontype;
    }

    public void setOptiontype(String optiontype) {
        this.optiontype = optiontype;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getChapterid() {
        return chapterid;
    }

    public void setChapterid(String chapterid) {
        this.chapterid = chapterid;
    }

    public List<String> getAnswer_arr() {
        return answer_arr;
    }

    public void setAnswer_arr(List<String> answer_arr) {
        this.answer_arr = answer_arr;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
