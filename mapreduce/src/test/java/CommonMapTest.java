import mubei.ah.CommonMapFunction;
import mubei.ah.biz.WordCount;
import mubei.ah.common.util.FileUtil;
import mubei.ah.common.util.LogUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author 帅小伙呀
 * @date 2023/9/3 22:13
 */
public class CommonMapTest {

    private final String ARTICLES_DIR = "src/main/resources/articles";

    private final List<String> paths = FileUtil.dirFiles(ARTICLES_DIR);

    @Test
    public void testDoMap() {
        long stime = System.currentTimeMillis();
        WordCount wordCount = new WordCount().predicate(c -> !Character.isLetter(c));
        CommonMapFunction commonMapFunction = new CommonMapFunction();
        assert paths != null;
        paths.forEach(
                file -> commonMapFunction.doMap(wordCount.mapFunc, 1, file, 1)
        );
        long etime = System.currentTimeMillis();

        LogUtil.log(String.format("CommonFileTest.testDoMap执行时长：%d 毫秒.", etime - stime));

    }


}
