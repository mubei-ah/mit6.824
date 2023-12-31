package mubei.ah.func;

import mubei.ah.mapreduce.KeyValue;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author 帅小伙呀
 * @date 2023/9/1 00:11
 */
public interface MapFunc {

    List<KeyValue> mapF(String cnt, Predicate<Character> predicate);

}
