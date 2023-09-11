import com.alibaba.fastjson.JSON;
import mubei.ah.CommonMapFunction;
import mubei.ah.CommonReduce;
import mubei.ah.biz.WordCount;
import mubei.ah.common.util.LogUtil;
import mubei.ah.func.ReduceFunc;
import mubei.ah.mapreduce.KeyValue;
import org.junit.Test;

/**
 * @author 帅小伙呀
 * @date 2023/9/5 00:10
 */
public class CommonReduceTest {



    @Test
    public void testDoReduce() {
        long stime = System.currentTimeMillis();
        CommonReduce commonReduce = new CommonReduce();
        ReduceFunc reduceFunc = new WordCount().reduceFunc;
        commonReduce.doReduce(reduceFunc,0,1,"src/main/resources/temp/re");
        long etime = System.currentTimeMillis();
        LogUtil.log(String.format("CommonReduceTest.testDoReduce执行时长：%d 毫秒.", etime - stime));

    }


    @Test
    public void testJson() {
        KeyValue keyValue = JSON.parseObject("{\"key\":\"said\",\"value\":\"1\"}", KeyValue.class);
        System.out.println(keyValue);
    }

}
