package lk.ac.sjp.foe.co4353.g6.voteservice.dto;

public class DataWrapper<T> {
    T body;

    public DataWrapper() {
    }

    public DataWrapper(T body) {
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
