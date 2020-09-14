package engine;

public class AnswerResponse {
    private boolean success;
    private String feedback;

    public AnswerResponse(boolean success, String feedback) {
        this.feedback = feedback;
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean correct) {
        success = correct;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
