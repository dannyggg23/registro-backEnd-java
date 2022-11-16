package io.danny.back_end.utils;

public class EcReturn {
    
    private boolean correctProcess = false;
    private String message = "";
    private Object data;
    
    
    public EcReturn() {
    }

    public EcReturn(boolean correctProcess, String message, Object data) {
        this.correctProcess = correctProcess;
        this.message = message;
        this.data = data;
    }
    
    public boolean isCorrectProcess() {
        return correctProcess;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setCorrectProcess(boolean correctProcess) {
        this.correctProcess = correctProcess;
    }

    @Override
    public String toString() {
        return "EcReturn [correctProcess=" + correctProcess + ", data=" + data + ", message=" + message + "]";
    }
    

}
