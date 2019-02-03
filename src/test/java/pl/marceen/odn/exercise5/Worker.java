package pl.marceen.odn.exercise5;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.odn.FileReader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcin Zaremba
 */
class Worker {
    private static final Logger logger = LoggerFactory.getLogger(Worker.class);

    @Test
    void work() throws Exception {
        // given
        String rawValues = FileReader.read(getClass(), "exercise5/liczby1.txt");
        String[] strings = rawValues.split(System.lineSeparator());

        List<Integer> numbers = new ArrayList<>();

        int lastNumber = 0;

        for (int i = 0; i < strings.length; i++) {
            if (i == 0) {
                continue;
            }
            int value = Integer.valueOf(strings[i]);
            if (getPower(value + 1) > 0) {
                if (lastNumber > value) {
                    numbers = new ArrayList<>();
                }
                numbers.add(value);
                lastNumber = value;
            }
        }


        List<String> result = new ArrayList<>();
        result.add(getHeader(numbers));
        numbers.forEach(integer -> result.add(String.valueOf(integer)));

        logger.info("Result: {}", result);
    }

    private String getHeader(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return "brak";
        }

        return String.valueOf(numbers.size());
    }

    private int getPower(int number) {
        var value = 0;
        if (number == 1) return value;

        while (number % 2 == 0) {
            number = number / 2;
            value++;
        }

        return number == 1 ? value : 0;
    }
}
