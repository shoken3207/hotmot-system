package models;

public class ResponseMessage {
	String message;
	Boolean isError;
	public ResponseMessage(String message, Boolean isError) {
		super();
		this.message = message;
		this.isError = isError;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getIsError() {
		return isError;
	}
	public void setIsError(Boolean isError) {
		this.isError = isError;
	}

	
	
	

}
