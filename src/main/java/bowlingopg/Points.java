package bowlingopg;

import java.util.List;

public class Points {

	private List<List<Integer>> points;
	private String token;

	public Points() {

	}

	public Points(List<List<Integer>> points, String token) {
		this.points = points;
		this.token = token;
	}

	public List<List<Integer>> getPoints() {
		return points;
	}

	public String getToken() {
		return token;
	}

	public void setPoints(List<List<Integer>> points) {
		this.points = points;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return points.toString();
	}

}
