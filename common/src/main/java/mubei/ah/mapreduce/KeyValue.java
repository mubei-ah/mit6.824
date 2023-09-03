package mubei.ah.mapreduce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 帅小伙呀
 * @date 2023/9/1 00:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue {

    private String key;
    private String value;
}

