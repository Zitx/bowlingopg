package bowlingopg;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import bowlingopg.BowlingAlgorithm;
import bowlingopg.Points;
import bowlingopg.PointsResult;
import bowlingopg.Success;

public class BowlingAlgorithmTest {

	public static final String URL = "http://13.74.31.101/api/points";

	@Test
	public void testCallSkatAPIReturnsSuccess() {
		RestTemplate restTemplate = new RestTemplate();
		Points points = restTemplate.getForObject(URL, Points.class);
		List<Integer> result = BowlingAlgorithm.calculateSum(points.getPoints());
		PointsResult pointsResult = new PointsResult();
		pointsResult.setPoints(result);
		pointsResult.setToken(points.getToken());
		ResponseEntity<Success> response = restTemplate.postForEntity(URL, pointsResult, Success.class);

		assertTrue(response.getBody().getSuccess());
		assertTrue(response.getStatusCode() == HttpStatus.OK);
	}

	@Test
	public void testSpare() {
		List<List<Integer>> scores = List.of(List.of(2, 4), List.of(2, 8), List.of(1, 5), List.of(8, 0), List.of(0, 1));
		List<Integer> expectedResult = List.of(6, 17, 23, 31, 32);

		List<Integer> result = BowlingAlgorithm.calculateSum(scores);

		assertTrue(result.equals(expectedResult));
	}

	@Test
	public void testStrike() {
		List<List<Integer>> scores = List.of(List.of(2, 4), List.of(10, 0), List.of(1, 5), List.of(8, 0),
				List.of(0, 1));
		List<Integer> expectedResult = List.of(6, 22, 28, 36, 37);

		List<Integer> result = BowlingAlgorithm.calculateSum(scores);

		assertTrue(result.equals(expectedResult));
	}

	@Test
	public void testDoubleStrike() {
		List<List<Integer>> scores = List.of(List.of(0, 0), List.of(10, 0), List.of(10, 0), List.of(1, 2),
				List.of(0, 0), List.of(0, 0));
		List<Integer> expectedResult = List.of(0, 21, 34, 37, 37, 37);

		List<Integer> result = BowlingAlgorithm.calculateSum(scores);

		assertTrue(result.equals(expectedResult));
	}

	@Test
	public void testTripleStrike() {
		List<List<Integer>> scores = List.of(List.of(0, 0), List.of(10, 0), List.of(10, 0), List.of(10, 0),
				List.of(1, 2), List.of(0, 0));
		List<Integer> expectedResult = List.of(0, 30, 51, 64, 67, 67);

		List<Integer> result = BowlingAlgorithm.calculateSum(scores);

		assertTrue(result.equals(expectedResult));
	}

	@Test
	public void testEndsEarlyOnStrikeNoBonus() {
		List<List<Integer>> scores = List.of(List.of(5, 5), List.of(2, 8), List.of(1, 5), List.of(8, 0),
				List.of(10, 0));
		List<Integer> expectedResult = List.of(12, 23, 29, 37, 47);

		List<Integer> result = BowlingAlgorithm.calculateSum(scores);

		assertTrue(result.equals(expectedResult));
	}

	@Test
	public void testEndsEarlyOnSpareNoBonus() {
		List<List<Integer>> scores = List.of(List.of(5, 5), List.of(2, 8), List.of(1, 5), List.of(8, 0), List.of(5, 5));
		List<Integer> expectedResult = List.of(12, 23, 29, 37, 47);

		List<Integer> result = BowlingAlgorithm.calculateSum(scores);

		assertTrue(result.equals(expectedResult));
	}

	@Test
	public void testStrikeNinthAndTenthFrame() {
		List<List<Integer>> scores = List.of(List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(0, 0),
				List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(10, 0), List.of(10, 0), List.of(10, 1));
		List<Integer> expectedResult = List.of(0, 0, 0, 0, 0, 0, 0, 0, 30, 51);

		List<Integer> result = BowlingAlgorithm.calculateSum(scores);

		assertTrue(result.equals(expectedResult));
	}

	@Test
	public void testStrikeTenthFrame() {
		List<List<Integer>> scores = List.of(List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(0, 0),
				List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(10, 0), List.of(10, 10));
		List<Integer> expectedResult = List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 30);

		List<Integer> result = BowlingAlgorithm.calculateSum(scores);

		assertTrue(result.equals(expectedResult));

	}

	@Test
	public void testSpareTenthFrame() {
		List<List<Integer>> scores = List.of(List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(0, 0),
				List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(5, 5), List.of(1, 0));
		List<Integer> expectedResult = List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 11);

		List<Integer> result = BowlingAlgorithm.calculateSum(scores);

		assertTrue(result.equals(expectedResult));

	}

	@Test
	public void testTwoBallsTenthFrame() {
		List<List<Integer>> scores = List.of(List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(0, 0),
				List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(0, 0), List.of(1, 2));
		List<Integer> expectedResult = List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 3);

		List<Integer> result = BowlingAlgorithm.calculateSum(scores);

		assertTrue(result.equals(expectedResult));

	}

	@Test
	public void testOneScore() {
		List<List<Integer>> scores = List.of(List.of(10, 0));
		List<Integer> expectedResult = List.of(10);

		List<Integer> result = BowlingAlgorithm.calculateSum(scores);

		assertTrue(result.equals(expectedResult));
	}

	@Test
	public void testNoScores() {
		List<List<Integer>> scores = new ArrayList<List<Integer>>();

		List<Integer> result = BowlingAlgorithm.calculateSum(scores);

		assertTrue(result.isEmpty());
	}

	@Test
	public void testFinishedExampleGame() {

		List<List<Integer>> scores = List.of(List.of(3, 7), List.of(10, 0), List.of(8, 2), List.of(8, 1),
				List.of(10, 0), List.of(3, 4), List.of(7, 0), List.of(5, 5), List.of(3, 2), List.of(2, 5));
		List<Integer> expectedResult = List.of(20, 40, 58, 67, 84, 91, 98, 111, 116, 123);

		List<Integer> result = BowlingAlgorithm.calculateSum(scores);

		assertTrue(result.equals(expectedResult));
	}

	@Test
	public void testPerfectGame() {

		List<List<Integer>> scores = List.of(List.of(10, 0), List.of(10, 0), List.of(10, 0), List.of(10, 0),
				List.of(10, 0), List.of(10, 0), List.of(10, 0), List.of(10, 0), List.of(10, 0), List.of(10, 0),
				List.of(10, 10));
		List<Integer> expectedResult = List.of(30, 60, 90, 120, 150, 180, 210, 240, 270, 300);

		List<Integer> result = BowlingAlgorithm.calculateSum(scores);

		assertTrue(result.equals(expectedResult));
	}
}
