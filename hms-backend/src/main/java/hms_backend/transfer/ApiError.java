package hms_backend.transfer;

public record ApiError (Integer status,String message,String path){
}
