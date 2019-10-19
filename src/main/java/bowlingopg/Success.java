package bowlingopg;

public class Success {

	private boolean success;

	public Success() {

	}

	public Success(boolean success) {
		this.success = success;
	}

	public boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return Boolean.toString(success);
	}
}
