package ro.itschool.exception;

public class InsuranceNotFoundException extends RuntimeException{
    public InsuranceNotFoundException(String message){
        super(message);
    }
}
