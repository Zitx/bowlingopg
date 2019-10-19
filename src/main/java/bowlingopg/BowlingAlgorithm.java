package bowlingopg;

import java.util.ArrayList;
import java.util.List;

public final class BowlingAlgorithm {

	private static final int MAX_FRAMES = 10;
	private static final int MAX_PINS = 10;
	private static final int FIRST_BALL = 0;
	private static final int SECOND_BALL = 1;

	private BowlingAlgorithm() {

	}

	private static boolean frameIsStrike(List<Integer> frame) {
		return frame.get(FIRST_BALL) == MAX_PINS;

	}

	private static boolean frameIsSpare(List<Integer> frame) {
		return frame.get(FIRST_BALL) + frame.get(SECOND_BALL) == MAX_PINS;

	}

	private static int frameSum(List<Integer> frame) {
		return frame.get(FIRST_BALL) + frame.get(SECOND_BALL);

	}

	public static List<Integer> calculateSum(List<List<Integer>> points) {
		int score = 0;
		List<Integer> sumResult = new ArrayList<Integer>();
		List<Integer> nextFrame;
		if (points.isEmpty()) {
			return sumResult;
		}
		List<Integer> currentFrame = points.get(0);
		for (int frame = 0; frame + 1 < points.size(); frame++) {
			nextFrame = points.get(frame + 1);
			if (frameIsStrike(currentFrame)) {
				score += frameSum(nextFrame);
				if (frameIsStrike(nextFrame) && (frame + 1) < MAX_FRAMES) {
					score += points.get(frame + 2).get(FIRST_BALL);
				}
			} else if (frameIsSpare(currentFrame)) {
				score += nextFrame.get(FIRST_BALL);
			}
			score += frameSum(currentFrame);
			sumResult.add(score);
			currentFrame = nextFrame;

		}
		if (points.size() > MAX_FRAMES) {
			return sumResult;
		}
		score += frameSum(currentFrame);
		sumResult.add(score);
		return sumResult;
	}
}
