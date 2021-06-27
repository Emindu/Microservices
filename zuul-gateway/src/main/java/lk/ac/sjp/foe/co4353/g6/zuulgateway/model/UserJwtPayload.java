package lk.ac.sjp.foe.co4353.g6.zuulgateway.model;

public class UserJwtPayload {
    private String jwt;

    public UserJwtPayload() {
    }

    public UserJwtPayload(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
