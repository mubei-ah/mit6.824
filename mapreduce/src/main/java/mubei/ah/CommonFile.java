package mubei.ah;

/**
 * @author 帅小伙呀
 * @date 2023/9/3 22:08
 */
public class CommonFile {

    public static String mrTempFile(Integer mapId, Integer reduceId) {
        String base = "src/main/resources/temp/mr-iterm-%s-%s";
        return String.format(base, mapId, reduceId);
    }

}
