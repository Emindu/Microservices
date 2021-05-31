package lk.ac.sjp.foe.co4353.g6.questionservice.dto;

import java.util.List;

public class LongListWrapper {
    List<Long> body;

    public LongListWrapper() {
    }

    public LongListWrapper(List<Long> body) {
        this.body = body;
    }

    public List<Long> getBody() {
        return body;
    }

    public void setBody(List<Long> body) {
        this.body = body;
    }
}
