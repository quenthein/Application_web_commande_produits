package fr.epsi.rennes.poec.domain;

public class Response <T> {
	
	private T data;
	private boolean success = true;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
