package se.iths.exceptions;

public class ResponseAsJson {

    private int statusCode;
    private String status;
    private String description;

    public ResponseAsJson(){

    }

    public ResponseAsJson( int statusCode, String status, String description){
        this.statusCode = statusCode;
        this.status = status;
        this.description = description;

    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
