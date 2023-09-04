package mubei.ah.biz;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import mubei.ah.func.MapFunc;
import mubei.ah.func.ReduceFunc;
import mubei.ah.mapreduce.KeyValue;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author 帅小伙呀
 * @date 2023/9/1 00:17
 */
public class WordCount {
    private Predicate<Character> predicate = c -> !Character.isLetter(c);

    public WordCount predicate(Predicate<Character> predicate) {
        this.predicate = predicate;
        return this;
    }

    // 传入内容和规则 进行分割
    public MapFunc mapFunc = (cnt) -> {
        List<String> words = splitToWords(cnt, predicate);
        return words.stream().map(word -> new KeyValue(word, "1")).collect(Collectors.toList());
    };

    public ReduceFunc reduceFunc = (key, values) ->
            String.valueOf(values.size());


    private List<String> splitToWords(String cnt, Predicate<Character> predicate) {
        return Splitter.on(CharMatcher.forPredicate(predicate::test))
                .omitEmptyStrings()
                .splitToList(cnt);
    }


}
