package bowlingopg;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Bowling {

	public static final String URL = "http://13.74.31.101/api/points";

	public static void main(String[] args) {

		RestTemplate restTemplate = new RestTemplate();
		Points points = restTemplate.getForObject(URL, Points.class);
		List<Integer> result = BowlingAlgorithm.calculateSum(points.getPoints());
		PointsResult pointsResult = new PointsResult();
		pointsResult.setPoints(result);
		pointsResult.setToken(points.getToken());
		ResponseEntity<Success> response = restTemplate.postForEntity(URL, pointsResult, Success.class);
		System.out.println("Points: " + points.getPoints());
		System.out.println("Akkumulerende point resultater: " + pointsResult.getPoints().toString());
		System.out.println("Svar fra Skats API: " + response.getStatusCode());
		System.out.println("Svar fra Skats API: " + response.getBody().toString());

	}
}
