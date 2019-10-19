package bowlingopg;

import java.util.List;

public class PointsResult {

	private String token;
	private List<Integer> points;

	public PointsResult() {

	}

	public PointsResult(List<Integer> points, String token) {
		this.points = points;
		this.token = token;
	}

	public List<Integer> getPoints() {
		return points;
	}

	public String getToken() {
		return token;
	}

	public void setPoints(List<Integer> points) {
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
