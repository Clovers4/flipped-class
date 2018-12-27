package online.templab.flippedclass.common.multipart;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * 文件存储 业务 接口
 *
 * @author wk
 */
public interface FileService {

    /**
     * 初始化上传文件夹。若不存在则创建;若存在则不做处理。
     */
    void init();

    /**
     * 销毁上传文件夹。
     */
    void deleteAll();

    /**
     * 存储一个文件
     *
     * @param file
     */
    void store(MultipartFile file);

    /**
     * 存储一个文件,以 UUID为文件名
     *
     * @param file
     */
    String storeWithUUID(MultipartFile file);

    /**
     * 导出所有的存在的文件的Stream
     *
     * @return
     */
    Stream<Path> loadAll();

    /**
     * 加载一个指定文件，返回Path
     *
     * @param filename
     * @return
     */
    Path load(String filename);

    /**
     * 加载一个指定文件，返回Resource
     *
     * @param filename
     * @return
     */
    Resource loadAsResource(String filename);

}
