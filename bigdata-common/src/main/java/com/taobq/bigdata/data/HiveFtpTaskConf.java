package com.taobq.bigdata.data;


public class HiveFtpTaskConf {

    /**
     * schema : #{table.schema}
     * tableName : #{table.name}
     * hdfsPath : #{hive.default.db.path}/#{table.schema}/exp_#{table.name}/#{partition.name}=#{partition.value}
     * ftpPath : ftp://#{ftp.export.host}#{export.path}
     * ftpUser : #{ftp.export.user}
     * ftpPass : #{ftp.export.password}
     * fileName : #{filename.prefix}#{filename.starttime}-#{filename.endtime}.csv-{SN}
     * fileSize : 2G
     * compress : 1
     * catalogId : #{catalog.id}
     * serviceCode : #{service.code}
     */

    private String schema;
    private String tableName;
    private String hdfsPath;
    private String ftpPath;
    private String ftpUser;
    private String ftpPass;
    private String fileName;
    private String fileSize;
    private int compress;
    private String catalogId;
    private String serviceCode;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getHdfsPath() {
        return hdfsPath;
    }

    public void setHdfsPath(String hdfsPath) {
        this.hdfsPath = hdfsPath;
    }

    public String getFtpPath() {
        return ftpPath;
    }

    public void setFtpPath(String ftpPath) {
        this.ftpPath = ftpPath;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    public String getFtpPass() {
        return ftpPass;
    }

    public void setFtpPass(String ftpPass) {
        this.ftpPass = ftpPass;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public int getCompress() {
        return compress;
    }

    public void setCompress(int compress) {
        this.compress = compress;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
}
