package domain;

import java.util.Random;

public class RandomNumberRangeGenerator implements NumberRangeGenerator {

    private final Random random = new Random();

    @Override
    public int generateRandomNumberInRange(final int start, final int end) {
        return random.nextInt(end - start) + start;
    }
}
