package cn.lb.oss.entity;

/**
 * 文件信息
 *
 * @author RainSoul
 * @create 2024-09-07
 */
/**
 * FileInfo类提供了文件信息的管理，包括文件名、目录标志和ETag.
 * ETag是一个HTTP标准，用于标识内容的唯一性，可以用于判断内容是否发生变化.
 */
public class FileInfo {

    // 文件名，标识文件的名称
    private String fileName;

    // 目录标志，用于标识是否为目录
    private Boolean directoryFlag;

    // ETag值，用于标识文件内容的唯一性
    private String etag;


    public Boolean getDirectoryFlag() {
        return directoryFlag;
    }

    public void setDirectoryFlag(Boolean directoryFlag) {
        this.directoryFlag = directoryFlag;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

