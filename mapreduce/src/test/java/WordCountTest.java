import mubei.ah.biz.WordCount;
import mubei.ah.mapreduce.KeyValue;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 帅小伙呀
 * @date 2023/9/1 00:40
 */

public class WordCountTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testCount() {
        String cnt = "Apart from counting words and characters, our online editor can help you to improve word choice and writing style, and, optionally, help you to detect grammar mistakes and plagiarism. To check word count, simply place your cursor into the text box above and start typing. You'll see the number of characters and words increase or decrease as you type, delete, and edit them. You can also copy and paste text from another program over into the online editor above. The Auto-Save feature will make sure you won't lose any changes while editing, even if you leave the site and come back later. Tip: Bookmark this page now.";
        WordCount wordCount = new WordCount().predicate(c -> !Character.isLetter(c));

        Map<String, String> retMap = wordCount.mapFunc.mapF(cnt)
                .stream()
                // 将key value - key：v1、v2、v3
                .collect(Collectors.groupingBy(
                        KeyValue::getKey,
                        Collectors.mapping(KeyValue::getValue, Collectors.toList())
                ))
                .entrySet().stream()
                // 聚合 size()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> wordCount.reduceFunc.reduceF(entry.getKey(), entry.getValue())
                ));

        Assert.assertEquals("2", retMap.get("words"));
        Assert.assertEquals("1", retMap.get("counting"));
        Assert.assertEquals("9", retMap.get("and"));
    }

}
