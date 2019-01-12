package com.basic.system.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;

public class FileInst implements Serializable {

	private static final long serialVersionUID = -2048359443914012375L;

	private InputStream inputStream;
	
	private OutputStream outputStream;
	
    private Long fileInfoId;

    private String fileName;

    private Date createTeime;

    private String fileType;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public Long getFileInfoId() {
		return fileInfoId;
	}

	public void setFileInfoId(Long fileInfoId) {
		this.fileInfoId = fileInfoId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreateTeime() {
		return createTeime;
	}

	public void setCreateTeime(Date createTeime) {
		this.createTeime = createTeime;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
    
}
