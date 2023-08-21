import exceptions.CalculateError;

import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEWLINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";

    public String getResult(String inputStr) {
        if (inputStr.split(SPACE_DELIMITER).length == 1) {
            return inputStr + " 1";
        }
        try {
            String[] words = inputStr.split(SPACE_DELIMITER);
            Map<String, Integer> wordFrequencyMap = getWordFrequencyCache(words);

            return generatePrintLines(wordFrequencyMap);
        } catch (Exception e) {
            throw new CalculateError();
        }
    }

    private static String generatePrintLines(Map<String, Integer> wordFrequencyInfoMap) {
        StringJoiner joiner = new StringJoiner(NEWLINE_DELIMITER);
        for (Map.Entry<String, Integer> entry : wordFrequencyInfoMap.entrySet()) {
            String s = entry.getKey() + SPACE_CHAR + entry.getValue();
            joiner.add(s);
        }

        return joiner.toString();
    }

    private Map<String, Integer> getWordFrequencyCache(String[] words) {
        Map<String, Integer> wordFrequencyCache = new HashMap<>();
        Arrays.asList(words).forEach(word -> {
            Integer count = wordFrequencyCache.get(word);
            if (!wordFrequencyCache.containsKey(word)) {
                count = 0;
            }
            count += 1;
            wordFrequencyCache.put(word, count);
        });
        return wordFrequencyCache.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
