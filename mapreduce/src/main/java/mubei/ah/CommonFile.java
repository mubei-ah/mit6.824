package mubei.ah;

import com.alibaba.fastjson.JSON;
import mubei.ah.common.util.FileUtil;
import mubei.ah.common.util.LogUtil;
import mubei.ah.mapreduce.KeyValue;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author 帅小伙呀
 * @date 2023/9/3 22:08
 */
public class CommonFile {

    public static final String MR_MERGE_OUT = "src/main/resources/out/mr-merge-out.txt";
    public static String mrTempFile(Integer mapId, Integer reduceId) {
        String base = "src/main/resources/temp/mr-iterm-%s-%s";
        return String.format(base, mapId, reduceId);
    }

    public static String reduceOutFile(int reduceId) {
        String base = "src/main/resources/temp/mr-reduce-%s";
        return String.format(base, reduceId);
    }

    public static void mergeReduceOutFiles(List<String> reduceFiles) {
        HashMap<String, String> kvs = new HashMap<>();

        reduceFiles.forEach(reduceFile -> {
            Stream<String> stream = FileUtil.stream(reduceFile);
            if (stream == null) {
                return;
            }
            stream.forEach(s -> {
                KeyValue keyValue = JSON.parseObject(s.getBytes(StandardCharsets.UTF_8), KeyValue.class);
                kvs.put(keyValue.getKey(),keyValue.getValue());
            });
        });
        ArrayList<String> keys = new ArrayList<>(kvs.keySet());
        keys.sort(String::compareTo);
        keys.forEach(key -> {
            FileUtil.append(MR_MERGE_OUT,
                    key +": " + kvs.get(key));
        });
        LogUtil.log("ok to merge all files");
    }

}
