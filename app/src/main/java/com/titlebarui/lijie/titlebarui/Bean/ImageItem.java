package com.titlebarui.lijie.titlebarui.Bean;

import com.titlebarui.lijie.titlebarui.serialization.JsonField;

import java.io.Serializable;


public class ImageItem implements Serializable {
	private static final long serialVersionUID = 1L;
	public String autoId;//自增id
	@JsonField(name = "imageType")
	public String resourceFlag;//资源标识：1：图片2：录音3：视频
	@JsonField(name = "ProgressNoteId")
	public String ProgressNoteId;
	public String imageId;//id
	@JsonField(name = "imageUrl")
	public String imagePath;
	public int resourceId;

	public boolean isSelected = false;

	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	//	public Bitmap getBitmap() {		
//		if(bitmap == null){
//			try {
//				bitmap = Bimp.revitionImageSize(imagePath);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return bitmap;
//	}
//	public void setBitmap(Bitmap bitmap) {
//		this.bitmap = bitmap;
//	}
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public String getResourceFlag() {
		return resourceFlag;
	}
	public void setResourceFlag(String resourceFlag) {
		this.resourceFlag = resourceFlag;
	}
	public String getProgressNoteId() {
		return ProgressNoteId;
	}
	public void setProgressNoteId(String progressNoteId) {
		ProgressNoteId = progressNoteId;
	}



}
