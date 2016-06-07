package dessert.rvo;

/**
 * @author 小春
 *
 */
public class ResultVO {

	private int success;

	private String message;

	public ResultVO() {

	}

	public ResultVO(int success, String message) {
		this.success = success;
		this.message = message;
	}

	public ResultVO(ResultVO rvo) {
		this.success = rvo.getSuccess();
		this.message = rvo.getMessage();
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
