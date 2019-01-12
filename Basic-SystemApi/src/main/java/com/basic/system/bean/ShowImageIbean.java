package com.basic.system.bean;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年11月13日 上午11:33:07
 * 
 * @Description: 查看图片请求参数
 *
 */
public class ShowImageIbean implements Serializable {

	private static final long serialVersionUID = -3052649680377834117L;
	
	private String isOriginal;//是否原图，true：是、false：否
	
	private String isRatio;//是否保持原比例，true：是、false：否
	private String zoom;//放大缩小，true：放大、false：缩小
	private int zoomRatio;//放大缩小比例
	
	private int width;//宽度
	private int height;//高度
	
	private Long fileInfoId;//文件id
	
	public String getIsRatio() {
		return isRatio;
	}
	public void setIsRatio(String isRatio) {
		this.isRatio = isRatio;
	}
	public String getZoom() {
		return zoom;
	}
	public void setZoom(String zoom) {
		this.zoom = zoom;
	}
	public int getZoomRatio() {
		return zoomRatio;
	}
	public void setZoomRatio(int zoomRatio) {
		this.zoomRatio = zoomRatio;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Long getFileInfoId() {
		return fileInfoId;
	}
	public void setFileInfoId(Long fileInfoId) {
		this.fileInfoId = fileInfoId;
	}
	public String getIsOriginal() {
		return isOriginal;
	}
	public void setIsOriginal(String isOriginal) {
		this.isOriginal = isOriginal;
	}
	
}
