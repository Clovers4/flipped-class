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
public interface StorageService {

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
    void store(MultipartFile file, String fileType);

    /**
     * 存储 ppt
     *
     * @param file
     */
    void storePpt(MultipartFile file);

    /**
     * 存储 report
     *
     * @param file
     */
    void storeReport(MultipartFile file);

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
