package mubei;

import mubei.ah.CommonFile;
import mubei.ah.CommonMapFunction;
import mubei.ah.CommonReduce;
import mubei.ah.common.util.LogUtil;
import mubei.ah.func.MapFunc;
import mubei.ah.func.ReduceFunc;

import java.util.Collections;
import java.util.List;

/**
 * @author 帅小伙呀
 * @date 2023/9/5 23:45
 */
public class Single {

    private final CommonMapFunction commonMapFunction = new CommonMapFunction();
    private final CommonReduce commonReduce = new CommonReduce();

    /**
     * @param mapFunc
     * @param reduceFunc
     * @param paths      文件地址
     */
    public void run(MapFunc mapFunc, ReduceFunc reduceFunc, List<String> paths) {
        long stime = System.currentTimeMillis();
        paths.forEach(
                path -> commonMapFunction.doMap(mapFunc, 0, path, 1)
        );
        commonReduce.doReduce(reduceFunc,0,1, CommonFile.reduceOutFile(0));
        CommonFile.mergeReduceOutFiles(Collections.singletonList(CommonFile.reduceOutFile(0)));
        long etime = System.currentTimeMillis();
        LogUtil.log(String.format("mubei.Single.run执行时长：%d 毫秒.", etime - stime));
    }


}
