package mubei.ah;

import com.alibaba.fastjson.JSON;
import mubei.ah.common.util.FileUtil;
import mubei.ah.common.util.LogUtil;
import mubei.ah.func.MapFunc;
import mubei.ah.mapreduce.KeyValue;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author 帅小伙呀
 * @date 2023/9/3 21:09
 */
public class CommonMapFunction {
    /**
     * map function
     *
     * @param mapFunc
     * @param workerId
     * @param filePath
     * @param nReduce
     */
    public void doMap(MapFunc mapFunc, Integer workerId, String filePath, Integer nReduce) {
        long stime = System.currentTimeMillis();

        String cnt = FileUtil.readFile(filePath);
        List<KeyValue> values = mapFunc.mapF(cnt);
        // key = 文件名   value: 写入的内容
        Map<String, String> toWrite = values.stream()
                // key  value
                .collect(Collectors.groupingBy(
                        p -> tempFilePath(workerId, p.getKey(), nReduce),
                        Collectors.mapping(JSON::toJSONString, Collectors.joining("\n"))
                ));


        toWrite.forEach(FileUtil::write);
        long etime = System.currentTimeMillis();

        LogUtil.log("map for id : %d done".formatted(workerId),
                String.format("mubei.ah.CommonMapFunction.doMap执行时长：%d 毫秒.", etime - stime)
        );
    }

    /**
     * 计算单词应该写入的文件
     *
     * @param key
     * @param nReduce
     * @return
     */
    private Integer hash(String key, Integer nReduce) {
        return Math.abs(key.hashCode()) % nReduce;
    }

    /**
     * 中间文件路径
     * mapFunc - 文件  - reduceFuc
     *
     * @param workerId
     * @param key
     * @param nReduce
     * @return
     */
    private String tempFilePath(Integer workerId, String key, Integer nReduce) {
        Integer hashNum = hash(key, nReduce);
        return CommonFile.mrTempFile(workerId, hashNum);
    }


}
