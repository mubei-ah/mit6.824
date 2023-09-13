import mubei.Single;
import mubei.ah.biz.WordCount;
import mubei.ah.common.util.FileUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author 帅小伙呀
 * @date 2023/9/6 00:01
 */
public class MRWordCountTest {

    private final String ARTICLES_DIR = "src/main/resources/articles";
    private final String TEMP_FILE_DIR = "src/main/resources/temp";
    private final String OUT_FILE_DIR = "src/main/resources/out";
    private final List<String> paths = FileUtil.dirFiles(ARTICLES_DIR);

    @Test
    public void testWordCountSeq() {
        WordCount wordCount = new WordCount();
        new Single().run(
                wordCount.mapFunc,
                wordCount.reduceFunc,
                paths
        );
        //compareOutFile();
    }

//    private void compareOutFile() {
//        String expect = FileUtil.readFile(CommonFileTest.MR_EXPECT_OUT);
//        String act = FileUtil.readFile(CommonFile.MR_MERGE_OUT);
//        Assert.assertEquals(expect, act);
//    }

}
