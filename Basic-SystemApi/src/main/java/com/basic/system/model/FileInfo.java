package com.basic.system.model;

import java.io.Serializable;
import java.util.Date;

public class FileInfo implements Serializable{

	private static final long serialVersionUID = 8538222122436053894L;

	private Long fileInfoId;

    private Long fileConfigId;

    private String srcFileName;

    private String tagetFileName;

    private Date createTime;

    private Long size;//文件大小，Bytes
    
    public Long getFileInfoId() {
        return fileInfoId;
    }

    public void setFileInfoId(Long fileInfoId) {
        this.fileInfoId = fileInfoId;
    }

    public Long getFileConfigId() {
        return fileConfigId;
    }

    public void setFileConfigId(Long fileConfigId) {
        this.fileConfigId = fileConfigId;
    }

    public String getSrcFileName() {
        return srcFileName;
    }

    public void setSrcFileName(String srcFileName) {
        this.srcFileName = srcFileName;
    }

    public String getTagetFileName() {
        return tagetFileName;
    }

    public void setTagetFileName(String tagetFileName) {
        this.tagetFileName = tagetFileName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
}