package com.titlebarui.lijie.titlebarui.DB;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;

import com.titlebarui.lijie.titlebarui.Bean.ImageItem;

public class ImageDB extends BaseDB {


	public ImageDB(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public Cursor select() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query("IMAGE", null, null, null, null, null, null);
		// Cursor cursor = db.rawQuery("select * from " + DATABASE_NAME, null);
		return cursor;
	}

	public long insert(String imageId, String imagePath,int resourceId,String progressNoteId, String resourceFlag ) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("imageId", imageId);
		cv.put("imagePath", imagePath);
		cv.put("progressNoteId", progressNoteId);
		cv.put("resourceFlag", resourceFlag);
		cv.put("resourceId", resourceId);
		long row = db.insert("IMAGE", null, cv);
		cv.clear();
		db.close();
		return row;
	}

	// 得到图片库中id的最大值
	public String getMaxId() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query("IMAGE", null, null, null, null, null, null);
		if (cursor.moveToLast()) {
			String maxId = cursor.getString(cursor.getColumnIndex("imageId"));
			cursor.close();
			db.close();
			return maxId;
		}
		return "1";
	}

	// 得到病程记录中id的最大值
	public String getMaxIdAddOne() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query("IMAGE", null, null, null, null, null, null);
		if (cursor.moveToLast()) {
			String maxId = cursor.getString(cursor.getColumnIndex("imageId"));
			int i = Integer.parseInt(maxId);
			maxId = (i + 1) + "";// 加一
			cursor.close();
			db.close();
			return maxId;
		}
		return "1";
	}

	/** 得到与病房记录id相关的所有图片 */
	public ImageItem selectOfId(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = (Cursor)db.rawQuery(
				"select * from IMAGE where imageId = " + id,
				null);
		ImageItem pn = null;
		if (cursor.moveToFirst()) {// 判断游标是否为空
			// Cursor.move(i);// 移动到指定记录
			pn=new ImageItem();
			String imageId = cursor.getString(cursor
					.getColumnIndex("imageId"));
			String imagePath = cursor.getString(cursor
					.getColumnIndex("imagePath"));
			int progressNoteIdIndex=cursor
					.getColumnIndex("progressNoteId");
			String resourceFlag = cursor.getString(cursor
					.getColumnIndex("resourceFlag"));
			if(progressNoteIdIndex!=-1){
				String progressNoteId = cursor.getString(progressNoteIdIndex);
				pn.setProgressNoteId(progressNoteId);
			}


			pn.setImageId(imageId);
			pn.setImagePath(imagePath);
			pn.setResourceFlag(resourceFlag);
		}
		cursor.close();
		db.close();
		return pn;

	}


	/** 得到与病房记录id相关的所有Image对象 */
	/*public List<Image> select(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from IMAGE where ProgressNoteId =" + id;
		Cursor imageCursor = (Cursor) db.rawQuery(sql, null);
		List<Image> imageList = new ArrayList<Image>();
		if (imageCursor.moveToFirst()) {// 判断游标是否为空
			do {
				// myCursor.move(i);// 移动到指定记录
				String imageId = imageCursor.getString(imageCursor
						.getColumnIndex("imageId"));
				String imagePath = imageCursor.getString(imageCursor
						.getColumnIndex("imagePath"));
				String ProgressNoteId = imageCursor.getString(imageCursor
						.getColumnIndex("ProgressNoteId"));

				Image image = new Image();
				image.setImageId(imageId);
				image.setImageURL(imageUrl);
				image.setProgressId(ProgressNoteId);
				image.setBitmap(Transform.convertStringToIcon(imageUrl));
				imageList.add(image);
			} while (imageCursor.moveToNext());

		}
		imageCursor.close();
		db.close();
		return imageList;
	}
	*/
	/** 得到与病房记录id相关的所有ImageItem对象 */
	public ArrayList<ImageItem> selectImageItem(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from IMAGE where progressNoteId =" + id;
		Cursor imageCursor = (Cursor) db.rawQuery(sql, null);
		ArrayList<ImageItem> imageList = new ArrayList<ImageItem>();
		if (imageCursor.moveToFirst()) {// 判断游标是否为空

			do {
				// myCursor.move(i);// 移动到指定记录
				String imageId = imageCursor.getString(imageCursor
						.getColumnIndex("imageId"));
				String imagePath = imageCursor.getString(imageCursor
						.getColumnIndex("imagePath"));
				String resourceFlag = imageCursor.getString(imageCursor
						.getColumnIndex("resourceFlag"));
				String progressNoteId = imageCursor.getString(imageCursor
						.getColumnIndex("progressNoteId"));

				int resourceId = imageCursor.getInt(imageCursor
						.getColumnIndex("resourceId"));

				ImageItem imageItem = new ImageItem();
				imageItem.setImageId(imageId);
				imageItem.setImagePath(imagePath);
				imageItem.setResourceFlag(resourceFlag);
				imageItem.setResourceId(resourceId);
				imageItem.setProgressNoteId(progressNoteId);

				//image.setProgressId(ProgressNoteId);
				//imageItem.setBitmap(Transform.convertStringToIcon(imageUrl));
				imageList.add(imageItem);
			} while (imageCursor.moveToNext());

		}
		imageCursor.close();
		db.close();
		return imageList;
	}

	public long deleteByprogressNoteId(String progressNoteId) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "progressNoteId = ?";
		String[] whereValue = { progressNoteId };
		long raw = db.delete("IMAGE", where, whereValue);
		db.close();
		return raw;
	}
	public long deleteByImageId(String imageId) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "imageId = ?";
		String[] whereValue = { imageId };
		long raw = db.delete("IMAGE", where, whereValue);
		db.close();
		return raw;
	}
}
