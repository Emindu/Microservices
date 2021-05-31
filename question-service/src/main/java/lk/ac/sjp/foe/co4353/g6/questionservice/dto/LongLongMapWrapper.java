package lk.ac.sjp.foe.co4353.g6.questionservice.dto;

import java.util.Map;

public class LongLongMapWrapper {
    private Map<Long, Long> body;

    public LongLongMapWrapper() {
    }

    public LongLongMapWrapper(Map<Long, Long> body) {
        this.body = body;
    }

    public Map<Long, Long> getBody() {
        return body;
    }

    public void setBody(Map<Long, Long> body) {
        this.body = body;
    }
}
