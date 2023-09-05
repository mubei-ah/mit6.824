package mubei.ah.common.util;

import java.util.Date;

/**
 * @author 帅小伙呀
 * @date 2023/9/3 21:36
 */
public class LogUtil {
    public static void log(Object... args) {
        String now = new Date().toString();
        StringBuffer sb = new StringBuffer();
        for (Object arg : args) {
            sb.append(arg).append("\n");
        }
        String log = now + "-" + sb+"\n";
        FileUtil.append("logs/log.log", log);
        System.out.println(log);
    }


}
