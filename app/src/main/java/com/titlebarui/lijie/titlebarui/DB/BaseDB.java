package com.titlebarui.lijie.titlebarui.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库控制类
 * 
 * @author 003
 * 
 */
public class BaseDB extends SQLiteOpenHelper {
	
	private final static String DATABASE_NAME = "case_db";
	private final static int DATABASE_VERSION = 1;
	
	
    /**
     * SQLiteDatabase 系统类
     */
    private SQLiteDatabase db;

    /**
     * 构造
     * 
     * @param context
     *            上下文
     */
    public BaseDB(Context context) {
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 打开数据库
     */
    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    /**
     * 关闭数据库
     */
    public void closeDatabase() {
        db.close();
    }

    /**
     * 执行sql语句
     * 
     * @param sql
     *            数据库的语句
     */
    public void execute(String sql) {
        System.out.println(sql);
        db.execSQL(sql);
    }
    
    public int insert(String tableName, ContentValues contentValues) {
    	return (int)db.insert(tableName, null, contentValues);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

		// 创建users表
    	
		/*String createUsersTable = "CREATE TABLE USERS ("
				+ "autoId  INTEGER primary key autoincrement," + "userId text,"
				+ "userName text," + "userPassword text)";
		db.execSQL(createUsersTable);*/
		
		
		
		// 创建病例夹表
		String createCaseTable = "CREATE TABLE CASES  ( "
				+ "autoId INTEGER primary key autoincrement," + "caseId text,"
				+ "personId text," + "department text," + "visitingTime text,"
				+ "updateTime text," + "createTime text," + "patientName text,"
				+ "patientSex text," + "patientAge text," + "birthday text,"
				+ "speciesOne text," + "numberOne text," + "spaciesTwo text,"
				+ "numberTwo text," + "contact text," + "phoneNumber text,"
				+ "Tel text," + "email text," + "occupation text,"+ "needSync text,"
				+ "introducer text," + "address text," + "remarks text,"+ "caseVersion text,"
				+ "basicCondition text," + "userId  text," + "groupId  text,"
//				+ " FOREIGN KEY(userId) REFERENCES USERS(userId)"
				+ "FOREIGN KEY(groupId) REFERENCES GROUPS(groupId))";
		db.execSQL(createCaseTable);
		
		
		
		// 创建病程记录表
		String createProgressNoteTable = "CREATE TABLE PROGRESSNOTE  ( "
				+ "autoId INTEGER primary key autoincrement,"
				+ "progressNoteId text," + "createTime text,"
				+ "progressNoteSpecies text," + "detailedCircumstance text,"+ "userId  text,"
				+ "caseId  text,"+ "caseAutoId  text,"+ "progressNoteVersion text,"+ "needSync text,"
				+ " FOREIGN KEY(caseAutoId) REFERENCES CASES(caseId))";
		db.execSQL(createProgressNoteTable);
		// 创建图库表
//		String createImageTable = "CREATE TABLE IMAGE  ( "
//				+ "autoId INTEGER primary key autoincrement,"
//				+ "imageId text,"
//				+ "imageUrl BLOB,"
//				+ "resourceName BLOB,"
//				+ "resourceFlag text,"
//				+ "ProgressNoteId text,"
//				+ " FOREIGN KEY(ProgressNoteId) REFERENCES PROGRESSNOTE(ProgressNoteId))";
//		db.execSQL(createImageTable);
		
		
		String createImageTable = "CREATE TABLE IMAGE  ( "
				+ "autoId INTEGER primary key autoincrement,"
				+ "imageId text,"
				+ "imagePath text,"
				+ "resourceFlag text,"
				+ "progressNoteId text,"
				+ "resourceId text,"
				+ " FOREIGN KEY(progressNoteId) REFERENCES PROGRESSNOTE(autoId))";
		db.execSQL(createImageTable);
		
		// 创建提醒记录表
		String createRemindTable = "CREATE TABLE Remind  ( "
				+ "autoId INTEGER primary key autoincrement,"
				+ "remindId text," + "remindContent text," + "remindTime text,"
				+ "repeatNumber text," + "isDel text," + "caseId text,"
				+ " FOREIGN KEY(caseId) REFERENCES CASES(autoId))";
		db.execSQL(createRemindTable);
		// 创建分组表
		String createGroupTable = "CREATE TABLE GROUPS  ("
				+ "autoId INTEGER primary key autoincrement," + "groupId text,"
				+ "groupName text," + "groupFlag text)";
		db.execSQL(createGroupTable);
		// 创建诊断表
		String createDiagnosisTable = "CREATE TABLE DIAGNOSIS  ( "
				+ "autoId INTEGER primary key autoincrement,"
				+ "diagnosisId text," + "diagnosisName text," + "caseId text,"
				+ " FOREIGN KEY(caseId) REFERENCES CASES(autoId))";
		db.execSQL(createDiagnosisTable);
	
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		/*String createUsersTable = "DROP TABLE IF EXISTS Users ";
		db.execSQL(createUsersTable);*/
		String createCaseTable = "DROP TABLE IF EXISTS CaseS ";
		db.execSQL(createCaseTable);
		String createProgressNoteTable = "DROP TABLE IF EXISTS ProgressNote ";
		db.execSQL(createProgressNoteTable);
		String createImageTable = "DROP TABLE IF EXISTS Image ";
		db.execSQL(createImageTable);
		String createRemindTable = "DROP TABLE IF EXISTS Remind ";
		db.execSQL(createRemindTable);
		String createGroupTable = "DROP TABLE IF EXISTS Group ";
		db.execSQL(createGroupTable);
		String createDiagnosisTable = "DROP TABLE IF EXISTS Diagnosis ";
		db.execSQL(createDiagnosisTable);
		onCreate(db);
	}
}
