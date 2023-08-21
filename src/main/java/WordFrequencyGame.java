import java.util.*;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEWLINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";

    public String getResult(String inputStr) {
        if (inputStr.split(SPACE_DELIMITER).length == 1) {
            return inputStr + " 1";
        } else {

            try {
                String[] words = inputStr.split(SPACE_DELIMITER);

                List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
                for (String word : words) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(word, 1);
                    wordFrequencyInfoList.add(wordFrequencyInfo);
                }

                Map<String, List<WordFrequencyInfo>> wordFrequencyMap = getListMap(wordFrequencyInfoList);

                List<WordFrequencyInfo> frequencyInfos = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequencyInfo>> entry : wordFrequencyMap.entrySet()) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
                    frequencyInfos.add(wordFrequencyInfo);
                }
                wordFrequencyInfoList = frequencyInfos;

                wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                return generatePrintLines(wordFrequencyInfoList);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private static String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        StringJoiner joiner = new StringJoiner(NEWLINE_DELIMITER);
        for (WordFrequencyInfo word : wordFrequencyInfoList) {
            String s = word.getWord() + SPACE_CHAR + word.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> map = new HashMap<>();
        for (WordFrequencyInfo wordFrequencyInfo : wordFrequencyInfoList) {
            if (!map.containsKey(wordFrequencyInfo.getWord())) {
                List<WordFrequencyInfo> wordFrequencyInfos = new ArrayList<>();
                wordFrequencyInfos.add(wordFrequencyInfo);
                map.put(wordFrequencyInfo.getWord(), wordFrequencyInfos);
            } else {
                map.get(wordFrequencyInfo.getWord()).add(wordFrequencyInfo);
            }
        }
        return map;
    }
}
