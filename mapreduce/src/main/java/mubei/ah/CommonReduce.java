package mubei.ah;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import mubei.ah.common.util.FileUtil;
import mubei.ah.common.util.LogUtil;
import mubei.ah.func.ReduceFunc;
import mubei.ah.mapreduce.KeyValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @author 帅小伙呀
 * @date 2023/9/4 23:13
 */
public class CommonReduce {


    public void doReduce(ReduceFunc reduceFunc, Integer id, Integer nMap, String reduceFilePath) {
        long stime = System.currentTimeMillis();
        Map<String, List<String>> kvsMap = new ConcurrentHashMap<>();

        for (int i = 1; i <= nMap; i++) {
            String tempFile = CommonFile.mrTempFile(i, id);
            try (Stream<String> stream = FileUtil.stream(tempFile)) {
                stream.map(s -> {
                            try {
                                return JSON.parseObject(s, KeyValue.class);
                            } catch (JSONException e) {
                                System.out.println("Invalid JSON: " + s);
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .forEach(keyValue -> kvsMap
                                .computeIfAbsent(keyValue.getKey(), k -> new ArrayList<>())
                                .add(keyValue.getValue()));
            }
        }

        List<String> keys = new ArrayList<>(kvsMap.keySet()).stream()
                .sorted(String::compareTo)
                .toList();
        keys.forEach(
                k -> {
                    List<String> v = kvsMap.get(k);
                    String cnt = reduceFunc.reduceF(k, v);
                    FileUtil.append(reduceFilePath, JSON.toJSONString(new KeyValue(k, cnt)));
                }
        );

        long etime = System.currentTimeMillis();
        LogUtil.log("ok to do reduce for id: " + id,
                String.format("mubei.ah.CommonReduce.doReduce执行时长：%d 毫秒.", etime - stime)
        );


    }

}
