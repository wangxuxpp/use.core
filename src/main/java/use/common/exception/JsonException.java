package use.common.exception;

import use.common.json.JSONResult;

public class JsonException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3894863291592341801L;

	
	private JSONResult json = null;
	
	public JsonException(JSONResult v)
	{
		super();
		json = v;
	}
	public JsonException(Throwable cause , JSONResult v)
	{
		super(cause);
		json = v;
	}
	public JsonException(String message, Throwable cause, JSONResult v)
	{
		super(message, cause);
		json = v;
	}
	public JSONResult getJson() {
		return json;
	}
	public void setJson(JSONResult json) {
		this.json = json;
	}
	
	
}
