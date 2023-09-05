package mubei.ah.common.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 帅小伙呀
 * @date 2023/9/3 21:35
 */
public class FileUtil {


    public static String readFile(String file) {
        try {
            return new String(Files.readAllBytes(Paths.get(file)));
        } catch (Exception e) {
            LogUtil.log("fail to read file, " + file);
            return null;
        }
    }


    public static void append(String path, String cnt) {
        String toAppend = cnt + "\n";
        try {
            Files.write(
                    Paths.get(path),
                    toAppend.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (Exception e) {
            LogUtil.log("fail to write file, " + path + "err: " + e);
        }
    }

    public static void write(String path,String cnt) {
        try {
            Files.write(
                    Paths.get(path),
                    cnt.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE
            );
        } catch (Exception e) {
            LogUtil.log("fail to write file, %s err: % e");
        }
    }


    // 获取制定文件夹下的所有文件
    public static List<String> dirFiles(String dir) {
        try {
            return Files.walk(Paths.get(dir))
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return null;
        }
    }

    public static Stream<String> stream(String fileName) {
        try {
            return Files.lines(Paths.get(fileName));
        } catch (Exception e) {
            return null;
        }
    }



}
