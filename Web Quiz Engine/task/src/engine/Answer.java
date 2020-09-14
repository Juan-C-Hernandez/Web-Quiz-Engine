package engine;

import java.util.HashSet;

public class Answer {
    private HashSet<Integer> answer;

    public Answer(HashSet<Integer> answer) {
        this.answer = answer;
    }

    public Answer() {}

    public HashSet<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(HashSet<Integer> answer) {
        this.answer = answer;
    }
}
