package com.titlebarui.lijie.titlebarui.DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.titlebarui.lijie.titlebarui.Bean.Case;
import com.titlebarui.lijie.titlebarui.util.CalendarNow;

public class CaseDB extends BaseDB {


	public CaseDB(Context context) {
		super(context);
	}

	/** 根据病例夹外键分组id进行查询得到更有该分组下的病历 */
	public List<Case> getCaseGroupId(String Id,String userId) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = null;
		if (Id != null && !Id.equals("")) {
			sql = "select * from CASES where caseVersion!=-1 and  groupId = " + Id;
		} else {
			sql = "select * from CASES where caseVersion!=-1  ";
		}
		
		if (userId != null && !userId.equals("")) {
			sql = sql+" and(userId is null or userId='"+userId+"' ) and caseVersion!=-1";
		} else {
			sql = sql+" and userId is null and caseVersion!=-1";
		}
		
		Cursor myCursor = (Cursor) db.rawQuery(sql, null);
		List<Case> caseList = new ArrayList<Case>();
		if (myCursor.moveToFirst()) {// 判断游标是否为空
			do {
				// myCursor.move(i);// 移动到指定记录
				

				String autoId = myCursor.getString(myCursor
						.getColumnIndex("autoId"));
				String caseid = myCursor.getString(myCursor
						.getColumnIndex("caseId"));
				String department = myCursor.getString(myCursor
						.getColumnIndex("department"));// 科室
				String visitingTime = myCursor.getString(myCursor
						.getColumnIndex("visitingTime"));// 就诊时间
				String updateTime = myCursor.getString(myCursor
						.getColumnIndex("updateTime"));// 更新时间
				String createTime = myCursor.getString(myCursor
						.getColumnIndex("createTime"));// 创建时间
				String patientName = myCursor.getString(myCursor
						.getColumnIndex("patientName"));// 病人姓名
				String patientSex = myCursor.getString(myCursor
						.getColumnIndex("patientSex"));// 病人性别
				String patientAge = myCursor.getString(myCursor
						.getColumnIndex("patientAge"));// 病人年龄
				String birthday = myCursor.getString(myCursor
						.getColumnIndex("patientSex"));// 病人生日
				String speciesOne = myCursor.getString(myCursor
						.getColumnIndex("speciesOne"));// 编号种类1
				String numberOne = myCursor.getString(myCursor
						.getColumnIndex("numberOne"));// 编号1
				String spaciesTwo = myCursor.getString(myCursor
						.getColumnIndex("spaciesTwo"));// 编号种类2
				String numberTwo = myCursor.getString(myCursor
						.getColumnIndex("numberTwo"));// 编号2
				String contact = myCursor.getString(myCursor
						.getColumnIndex("contact"));// 联系人
				String phoneNumber = myCursor.getString(myCursor
						.getColumnIndex("phoneNumber"));// 联系人手机号码
				
				String Tel = myCursor.getString(myCursor.getColumnIndex("Tel"));// 固定电话
				
				
				String email = myCursor.getString(myCursor
						.getColumnIndex("email"));// 邮箱
				String occupation = myCursor.getString(myCursor
						.getColumnIndex("occupation"));// 职业
				String introducer = myCursor.getString(myCursor
						.getColumnIndex("introducer"));// 介绍人
				String address = myCursor.getString(myCursor
						.getColumnIndex("address"));// 地址
				String remarks = myCursor.getString(myCursor
						.getColumnIndex("remarks"));// 更多文本信息
				String basicCondition = myCursor.getString(myCursor
						.getColumnIndex("basicCondition"));// 基本病情
				String groupId = myCursor.getString(myCursor
						.getColumnIndex("groupId"));// 外键分组id
				
				String caseVersion = myCursor.getString(myCursor
						.getColumnIndex("caseVersion"));// 外键分组id
				Case c = new Case();
				c.setAutoId(autoId);
				c.setCaseId(caseid);
				c.setDepartment(department);
				c.setVisitingTime(visitingTime);
				c.setUpdateTime(updateTime);
				c.setCreateTime(createTime);
				c.setPatientName(patientName);
				c.setPatientAge(patientAge);
				c.setPatientSex(patientSex);
				c.setBirthday(birthday);
				c.setSpeciesOne(speciesOne);
				c.setNumberOne(numberOne);
				c.setSpaciesTwo(spaciesTwo);
				c.setNumberTwo(numberTwo);
				c.setContact(contact);
				c.setPhoneNumber(phoneNumber);
				c.setTel(Tel);
				c.setEmail(email);
				c.setOccupation(occupation);
				c.setIntroducer(introducer);
				c.setAddress(address);
				c.setRemarks(remarks);
				c.setBasicCondition(basicCondition);
				c.setGroupId(groupId);
				c.setCaseVersion(caseVersion);
				caseList.add(c);
			} while (myCursor.moveToNext());

		}
		myCursor.close();
		db.close();
		return caseList;
	}

	public List<Case> select(String TABLE_NAME,String userId,boolean sync) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor myCursor = null;
		
		if(userId==null){
			//没有指明用户时取得是所有用户都不是的
			String[] args = {"-1"};
			myCursor = db.query(TABLE_NAME, null,"userId is null and caseVersion!=?", args, null, null,
					null);
		}else{
			if(sync){//查找需要同步的
				String[] args = {userId,"1"};
				myCursor = db.query(TABLE_NAME, null, "(userId is null or userId = ?) and needSync=?", args, null, null,
						null);
			}else{
				String[] args = {userId,"-1"};
				myCursor = db.query(TABLE_NAME, null, "(userId is null or userId = ?)  and caseVersion!=?", args, null, null,
						null);
			}
			
		}
		
		List<Case> caseList = new ArrayList<Case>();
		if (myCursor.moveToFirst()) {// 判断游标是否为空
			do {
				// myCursor.move(i);// 移动到指定记录
				String autoId = myCursor.getString(myCursor
						.getColumnIndex("autoId"));
				String caseid = myCursor.getString(myCursor
						.getColumnIndex("caseId"));
				String department = myCursor.getString(myCursor
						.getColumnIndex("department"));// 科室
				String visitingTime = myCursor.getString(myCursor
						.getColumnIndex("visitingTime"));// 就诊时间
				String updateTime = myCursor.getString(myCursor
						.getColumnIndex("updateTime"));// 更新时间
				String createTime = myCursor.getString(myCursor
						.getColumnIndex("createTime"));// 创建时间
				String patientName = myCursor.getString(myCursor
						.getColumnIndex("patientName"));// 病人姓名
				String patientSex = myCursor.getString(myCursor
						.getColumnIndex("patientSex"));// 病人性别
				String patientAge = myCursor.getString(myCursor
						.getColumnIndex("patientAge"));// 病人年龄
				String birthday = myCursor.getString(myCursor
						.getColumnIndex("patientSex"));// 病人生日
				String speciesOne = myCursor.getString(myCursor
						.getColumnIndex("speciesOne"));// 编号种类1
				String numberOne = myCursor.getString(myCursor
						.getColumnIndex("numberOne"));// 编号1
				String spaciesTwo = myCursor.getString(myCursor
						.getColumnIndex("spaciesTwo"));// 编号种类2
				String numberTwo = myCursor.getString(myCursor
						.getColumnIndex("numberTwo"));// 编号2
				String contact = myCursor.getString(myCursor
						.getColumnIndex("contact"));// 联系人
				String phoneNumber = myCursor.getString(myCursor
						.getColumnIndex("phoneNumber"));// 联系人手机号码
				String Tel = myCursor.getString(myCursor.getColumnIndex("Tel"));// 固定电话
				String email = myCursor.getString(myCursor
						.getColumnIndex("email"));// 邮箱
				String occupation = myCursor.getString(myCursor
						.getColumnIndex("occupation"));// 职业
				String introducer = myCursor.getString(myCursor
						.getColumnIndex("introducer"));// 介绍人
				String address = myCursor.getString(myCursor
						.getColumnIndex("address"));// 地址
				String remarks = myCursor.getString(myCursor
						.getColumnIndex("remarks"));// 更多文本信息
				String basicCondition = myCursor.getString(myCursor
						.getColumnIndex("basicCondition"));// 基本病情
				String groupId = myCursor.getString(myCursor
						.getColumnIndex("groupId"));// 外键分组id
				String caseVersion = myCursor.getString(myCursor
						.getColumnIndex("caseVersion"));// 外键分组id

				Case c = new Case();
				c.setAutoId(autoId);
				c.setCaseId(caseid);
				c.setDepartment(department);
				c.setVisitingTime(visitingTime);
				c.setUpdateTime(updateTime);
				c.setCreateTime(createTime);
				c.setPatientName(patientName);
				c.setPatientAge(patientAge);
				c.setPatientSex(patientSex);
				c.setBirthday(birthday);
				c.setSpeciesOne(speciesOne);
				c.setNumberOne(numberOne);
				c.setSpaciesTwo(spaciesTwo);
				c.setNumberTwo(numberTwo);
				c.setContact(contact);
				c.setPhoneNumber(phoneNumber);
				c.setTel(Tel);
				c.setEmail(email);
				c.setOccupation(occupation);
				c.setIntroducer(introducer);
				c.setAddress(address);
				c.setRemarks(remarks);
				c.setBasicCondition(basicCondition);
				c.setGroupId(groupId);
				c.setCaseVersion(caseVersion);
				caseList.add(c);
			} while (myCursor.moveToNext());

		}
		myCursor.close();
		db.close();
		return caseList;
	}
	public List<Map<String, String>> selectExistCaseId(String TABLE_NAME,String userId) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor myCursor = null;
		
		String[] args = {userId,"-1"};
		String[] columns={"autoId","caseId","caseVersion"};
		
		myCursor = db.query(TABLE_NAME, columns, "userId = ?  and caseVersion!=?", args, null, null,
				null);
		
		List<Map<String, String>> caseList = new ArrayList<Map<String, String>>();
		if (myCursor.moveToFirst()) {// 判断游标是否为空
			do {
				// myCursor.move(i);// 移动到指定记录
				
				String autoId = myCursor.getString(myCursor
						.getColumnIndex("autoId"));
				String caseId = myCursor.getString(myCursor
						.getColumnIndex("caseId"));
				String caseVersion = myCursor.getString(myCursor
						.getColumnIndex("caseVersion"));
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("autoId", autoId);
				map.put("caseId", caseId);
				map.put("caseVersion", caseVersion);
				System.out.println("查找到本地Case----->autoId"+autoId+"-----caseId"+caseId+"------>caseVersion"+caseVersion);
				caseList.add(map);
			} while (myCursor.moveToNext());

		}
		myCursor.close();
		db.close();
		return caseList;
	}

	public long insert(String caseId, String department, String visitingTime,
			String updateTime, String createTime, String patientName,
			String patientSex, String patientAge, String birthday,
			String speciesOne, String numberOne, String spaciesTwo,
			String numberTwo, String contact, String phoneNumber, String Tel,
			String email, String occupation, String introducer, String address,
			String remarksMore, String basicCondition, String userId,
			String groupId,String caseVersion,String needSync) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		if(caseId!=null){
			cv.put("caseId", caseId);
		}
		cv.put("department", department);
		cv.put("visitingTime", visitingTime);
		cv.put("updateTime", updateTime);
		cv.put("createTime", createTime);
		cv.put("patientName", patientName);
		cv.put("patientSex", patientSex);
		cv.put("patientAge", patientAge);
		cv.put("birthday", birthday);
		cv.put("speciesOne", speciesOne);
		cv.put("numberOne", numberOne);
		cv.put("spaciesTwo", spaciesTwo);
		cv.put("numberTwo", numberTwo);
		cv.put("contact", contact);
		cv.put("phoneNumber", phoneNumber);
		cv.put("Tel", Tel);
		cv.put("email", email);
		cv.put("occupation", occupation);
		cv.put("introducer", introducer);
		cv.put("address", address);
		cv.put("remarks", remarksMore);
		cv.put("basicCondition", basicCondition);
		if(userId!=null){
			cv.put("userId", userId);
		}
		cv.put("groupId", groupId);
		if(needSync!=null){
			cv.put("needSync", "1");
		}else{
			cv.put("needSync", "0");
		}
		
		
		if(caseVersion!=null&&!caseVersion.equals("")){
			cv.put("caseVersion", caseVersion);
		}else{
			cv.put("caseVersion", "0");
		}
		long row = db.insert("CASES", null, cv);
		return row;
	}

	// 根据病例夹病人名字进行模糊查询
	public List<Case> selectBypatientName(String patientName_) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from CASES where patientName like '%"
				+ patientName_ + "%'";
		Cursor CaseCursor = (Cursor) db.rawQuery(sql, null);
		List<Case> caseList = new ArrayList<Case>();
		if (CaseCursor.moveToFirst()) {// 判断游标是否为空
			do {
				// myCursor.move(i);// 移动到指定记录
				String caseid = CaseCursor.getString(CaseCursor
						.getColumnIndex("caseId"));
				String caseAutoId = CaseCursor.getString(CaseCursor
						.getColumnIndex("autoId"));
				String department = CaseCursor.getString(CaseCursor
						.getColumnIndex("department"));// 科室
				String visitingTime = CaseCursor.getString(CaseCursor
						.getColumnIndex("visitingTime"));// 就诊时间
				String updateTime = CaseCursor.getString(CaseCursor
						.getColumnIndex("updateTime"));// 更新时间
				String createTime = CaseCursor.getString(CaseCursor
						.getColumnIndex("createTime"));// 创建时间
				String patientName = CaseCursor.getString(CaseCursor
						.getColumnIndex("patientName"));// 病人姓名
				String patientSex = CaseCursor.getString(CaseCursor
						.getColumnIndex("patientSex"));// 病人性别
				String patientAge = CaseCursor.getString(CaseCursor
						.getColumnIndex("patientAge"));// 病人年龄
				String birthday = CaseCursor.getString(CaseCursor
						.getColumnIndex("patientSex"));// 病人生日
				String speciesOne = CaseCursor.getString(CaseCursor
						.getColumnIndex("speciesOne"));// 编号种类1
				String numberOne = CaseCursor.getString(CaseCursor
						.getColumnIndex("numberOne"));// 编号1
				String spaciesTwo = CaseCursor.getString(CaseCursor
						.getColumnIndex("spaciesTwo"));// 编号种类2
				String numberTwo = CaseCursor.getString(CaseCursor
						.getColumnIndex("numberTwo"));// 编号2
				String contact = CaseCursor.getString(CaseCursor
						.getColumnIndex("contact"));// 联系人
				String phoneNumber = CaseCursor.getString(CaseCursor
						.getColumnIndex("phoneNumber"));// 联系人手机号码
				String Tel = CaseCursor.getString(CaseCursor
						.getColumnIndex("Tel"));// 固定电话
				String email = CaseCursor.getString(CaseCursor
						.getColumnIndex("email"));// 邮箱
				String occupation = CaseCursor.getString(CaseCursor
						.getColumnIndex("occupation"));// 职业
				String introducer = CaseCursor.getString(CaseCursor
						.getColumnIndex("introducer"));// 介绍人
				String address = CaseCursor.getString(CaseCursor
						.getColumnIndex("address"));// 地址
				String remarks = CaseCursor.getString(CaseCursor
						.getColumnIndex("remarks"));// 更多文本信息
				String basicCondition = CaseCursor.getString(CaseCursor
						.getColumnIndex("basicCondition"));// 基本病情

				Case c = new Case();
				c.setAutoId(caseAutoId);
				c.setCaseId(caseid);
				c.setDepartment(department);
				c.setVisitingTime(visitingTime);
				c.setUpdateTime(updateTime);
				c.setCreateTime(createTime);
				c.setPatientName(patientName);
				c.setPatientAge(patientAge);
				c.setPatientSex(patientSex);
				c.setBirthday(birthday);
				c.setSpeciesOne(speciesOne);
				c.setNumberOne(numberOne);
				c.setSpaciesTwo(spaciesTwo);
				c.setNumberTwo(numberTwo);
				c.setContact(contact);
				c.setPhoneNumber(phoneNumber);
				c.setTel(Tel);
				c.setEmail(email);
				c.setOccupation(occupation);
				c.setIntroducer(introducer);
				c.setAddress(address);
				c.setRemarks(remarks);
				c.setBasicCondition(basicCondition);
				caseList.add(c);
			} while (CaseCursor.moveToNext());

		}
		return caseList;
	}

	/** 根据病例夹外键分组id进行查询得到更有该分组下的病历数量 */
	public int getCaseGroupIdCount(String groupId) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from CASES where groupId = " + groupId;
		Cursor cursor = (Cursor) db.rawQuery(sql, null);
		int count = cursor.getCount();
		return count;
	}

	// 根据病例id获取病理信息
	public List<Case> selectById(String id) {
		List<Case> caseList = new ArrayList<Case>();
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from CASES where autoId = " + id;
		Cursor caseCursor = (Cursor) db.rawQuery(sql, null);
		if (caseCursor.moveToFirst()) {// 判断游标是否为空
			String patientName = caseCursor.getString(caseCursor
					.getColumnIndex("patientName"));
			String patientSex = caseCursor.getString(caseCursor
					.getColumnIndex("patientSex"));
			String patientAge = caseCursor.getString(caseCursor
					.getColumnIndex("patientAge"));
			String department = caseCursor.getString(caseCursor
					.getColumnIndex("department"));
			String visitingTime = caseCursor.getString(caseCursor
					.getColumnIndex("visitingTime"));
			String birthday = caseCursor.getString(caseCursor
					.getColumnIndex("birthday"));
			String speciesOne = caseCursor.getString(caseCursor
					.getColumnIndex("speciesOne"));
			String numberOne = caseCursor.getString(caseCursor
					.getColumnIndex("numberOne"));
			String spaciesTwo = caseCursor.getString(caseCursor
					.getColumnIndex("spaciesTwo"));
			String numberTwo = caseCursor.getString(caseCursor
					.getColumnIndex("numberTwo"));
			String personId = caseCursor.getString(caseCursor
					.getColumnIndex("personId"));
			String contact = caseCursor.getString(caseCursor
					.getColumnIndex("contact"));
			String phoneNumber = caseCursor.getString(caseCursor
					.getColumnIndex("phoneNumber"));
			String Tel = caseCursor.getString(caseCursor.getColumnIndex("Tel"));
			String email = caseCursor.getString(caseCursor
					.getColumnIndex("email"));
			String occupation = caseCursor.getString(caseCursor
					.getColumnIndex("occupation"));
			String introducer = caseCursor.getString(caseCursor
					.getColumnIndex("introducer"));
			String address = caseCursor.getString(caseCursor
					.getColumnIndex("address"));
			String remarks = caseCursor.getString(caseCursor
					.getColumnIndex("remarks"));
			String basicCondition = caseCursor.getString(caseCursor
					.getColumnIndex("basicCondition"));
			String groupId = caseCursor.getString(caseCursor
					.getColumnIndex("groupId"));
			
			String caseId = caseCursor.getString(caseCursor
					.getColumnIndex("caseId"));
			String autoId = caseCursor.getString(caseCursor
					.getColumnIndex("autoId"));
			String caseVersion = caseCursor.getString(caseCursor
					.getColumnIndex("caseVersion"));
			Case c = new Case();
			c.setPatientName(patientName);
			c.setPatientAge(patientAge);
			c.setPatientSex(patientSex);
			c.setDepartment(department);
			c.setVisitingTime(visitingTime);
			c.setBirthday(birthday); 
			c.setSpeciesOne(speciesOne);
			c.setNumberOne(numberOne);
			c.setSpaciesTwo(spaciesTwo);
			c.setNumberTwo(numberTwo);
			c.setPersonId(personId);
			c.setContact(contact);
			c.setPhoneNumber(phoneNumber);
			c.setTel(Tel);
			c.setEmail(email);
			c.setOccupation(occupation);
			c.setIntroducer(introducer);
			c.setAddress(address);
			c.setRemarks(remarks);
			c.setBasicCondition(basicCondition);
			c.setGroupId(groupId);
			c.setAutoId(autoId);
			c.setCaseVersion(caseVersion);
			c.setCaseId(caseId);
			caseList.add(c);
		}
		caseCursor.close();
		db.close();
		return caseList;
	}
	// 根据病例id获取病理信息
		public Case selectByCaseId(String id) {
			Case casee = new Case();
			SQLiteDatabase db = this.getWritableDatabase();
			String sql = "select * from CASES where caseId = '" + id+"'";
			Cursor caseCursor = (Cursor) db.rawQuery(sql, null);
			if (caseCursor.moveToFirst()) {// 判断游标是否为空
				String patientName = caseCursor.getString(caseCursor
						.getColumnIndex("patientName"));
				String patientSex = caseCursor.getString(caseCursor
						.getColumnIndex("patientSex"));
				String patientAge = caseCursor.getString(caseCursor
						.getColumnIndex("patientAge"));
				String department = caseCursor.getString(caseCursor
						.getColumnIndex("department"));
				String visitingTime = caseCursor.getString(caseCursor
						.getColumnIndex("visitingTime"));
				String speciesOne = caseCursor.getString(caseCursor
						.getColumnIndex("speciesOne"));
				String numberOne = caseCursor.getString(caseCursor
						.getColumnIndex("numberOne"));
				String spaciesTwo = caseCursor.getString(caseCursor
						.getColumnIndex("spaciesTwo"));
				String numberTwo = caseCursor.getString(caseCursor
						.getColumnIndex("numberTwo"));
				String personId = caseCursor.getString(caseCursor
						.getColumnIndex("personId"));
				String contact = caseCursor.getString(caseCursor
						.getColumnIndex("contact"));
				String phoneNumber = caseCursor.getString(caseCursor
						.getColumnIndex("phoneNumber"));
				String Tel = caseCursor.getString(caseCursor.getColumnIndex("Tel"));
				String email = caseCursor.getString(caseCursor
						.getColumnIndex("email"));
				String occupation = caseCursor.getString(caseCursor
						.getColumnIndex("occupation"));
				String introducer = caseCursor.getString(caseCursor
						.getColumnIndex("introducer"));
				String address = caseCursor.getString(caseCursor
						.getColumnIndex("address"));
				String remarks = caseCursor.getString(caseCursor
						.getColumnIndex("remarks"));
				String basicCondition = caseCursor.getString(caseCursor
						.getColumnIndex("basicCondition"));
				String groupId = caseCursor.getString(caseCursor
						.getColumnIndex("groupId"));
				
				String caseId = caseCursor.getString(caseCursor
						.getColumnIndex("caseId"));
				String autoId = caseCursor.getString(caseCursor
						.getColumnIndex("autoId"));
				String caseVersion = caseCursor.getString(caseCursor
						.getColumnIndex("caseVersion"));
				Case c = new Case();
				c.setPatientName(patientName);
				c.setPatientAge(patientAge);
				c.setPatientSex(patientSex);
				c.setDepartment(department);
				c.setVisitingTime(visitingTime);
				c.setSpeciesOne(speciesOne);
				c.setNumberOne(numberOne);
				c.setSpaciesTwo(spaciesTwo);
				c.setNumberTwo(numberTwo);
				c.setPersonId(personId);
				c.setContact(contact);
				c.setPhoneNumber(phoneNumber);
				c.setTel(Tel);
				c.setEmail(email);
				c.setOccupation(occupation);
				c.setIntroducer(introducer);
				c.setAddress(address);
				c.setRemarks(remarks);
				c.setBasicCondition(basicCondition);
				c.setGroupId(groupId);
				c.setAutoId(autoId);
				c.setCaseVersion(caseVersion);
				c.setCaseId(caseId);
				caseCursor.close();
				db.close();
				return c;
			}
			caseCursor.close();
			db.close();
			return null;
		}
		// 根据病例id获取病理信息
				public Case selectByCaseAutoId(String CaseAutoId) {
					Case casee = new Case();
					SQLiteDatabase db = this.getWritableDatabase();
					String sql = "select * from CASES where autoId = '" + CaseAutoId+"'";
					Cursor caseCursor = (Cursor) db.rawQuery(sql, null);
					if (caseCursor.moveToFirst()) {// 判断游标是否为空
						String patientName = caseCursor.getString(caseCursor
								.getColumnIndex("patientName"));
						String patientSex = caseCursor.getString(caseCursor
								.getColumnIndex("patientSex"));
						String patientAge = caseCursor.getString(caseCursor
								.getColumnIndex("patientAge"));
						String department = caseCursor.getString(caseCursor
								.getColumnIndex("department"));
						String visitingTime = caseCursor.getString(caseCursor
								.getColumnIndex("visitingTime"));
						String speciesOne = caseCursor.getString(caseCursor
								.getColumnIndex("speciesOne"));
						String numberOne = caseCursor.getString(caseCursor
								.getColumnIndex("numberOne"));
						String spaciesTwo = caseCursor.getString(caseCursor
								.getColumnIndex("spaciesTwo"));
						String numberTwo = caseCursor.getString(caseCursor
								.getColumnIndex("numberTwo"));
						String personId = caseCursor.getString(caseCursor
								.getColumnIndex("personId"));
						String contact = caseCursor.getString(caseCursor
								.getColumnIndex("contact"));
						String phoneNumber = caseCursor.getString(caseCursor
								.getColumnIndex("phoneNumber"));
						String Tel = caseCursor.getString(caseCursor.getColumnIndex("Tel"));
						String email = caseCursor.getString(caseCursor
								.getColumnIndex("email"));
						String occupation = caseCursor.getString(caseCursor
								.getColumnIndex("occupation"));
						String introducer = caseCursor.getString(caseCursor
								.getColumnIndex("introducer"));
						String address = caseCursor.getString(caseCursor
								.getColumnIndex("address"));
						String remarks = caseCursor.getString(caseCursor
								.getColumnIndex("remarks"));
						String basicCondition = caseCursor.getString(caseCursor
								.getColumnIndex("basicCondition"));
						String groupId = caseCursor.getString(caseCursor
								.getColumnIndex("groupId"));
						
						String caseId = caseCursor.getString(caseCursor
								.getColumnIndex("caseId"));
						String autoId = caseCursor.getString(caseCursor
								.getColumnIndex("autoId"));
						String caseVersion = caseCursor.getString(caseCursor
								.getColumnIndex("caseVersion"));
						Case c = new Case();
						c.setPatientName(patientName);
						c.setPatientAge(patientAge);
						c.setPatientSex(patientSex);
						c.setDepartment(department);
						c.setVisitingTime(visitingTime);
						c.setSpeciesOne(speciesOne);
						c.setNumberOne(numberOne);
						c.setSpaciesTwo(spaciesTwo);
						c.setNumberTwo(numberTwo);
						c.setPersonId(personId);
						c.setContact(contact);
						c.setPhoneNumber(phoneNumber);
						c.setTel(Tel);
						c.setEmail(email);
						c.setOccupation(occupation);
						c.setIntroducer(introducer);
						c.setAddress(address);
						c.setRemarks(remarks);
						c.setBasicCondition(basicCondition);
						c.setGroupId(groupId);
						c.setAutoId(autoId);
						c.setCaseVersion(caseVersion);
						c.setCaseId(caseId);
						caseCursor.close();
						db.close();
						return c;
					}
					caseCursor.close();
					db.close();
					return null;
				}
	// 根据groupId获取病理信息
	public List<Case> selectBygroupId(String id) {
		List<Case> caseList = new ArrayList<Case>();
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from CASES where groupId = " + id;
		Cursor caseCursor = (Cursor) db.rawQuery(sql, null);
		if (caseCursor.moveToFirst()) {// 判断游标是否为空
			String groupId = caseCursor.getString(caseCursor
					.getColumnIndex("groupId"));
			String caseId = caseCursor.getString(caseCursor
					.getColumnIndex("caseId"));
			Case c = new Case();
			// c.setPatientName(patientName);
			// c.setPatientAge(patientAge);
			// c.setPatientSex(patientSex);
			// c.setDepartment(department);
			// c.setVisitingTime(visitingTime);
			// c.setSpeciesOne(speciesOne);
			// c.setNumberOne(numberOne);
			// c.setSpaciesTwo(spaciesTwo);
			// c.setNumberTwo(numberTwo);
			// c.setPersonId(personId);
			// c.setContact(contact);
			// c.setPhoneNumber(phoneNumber);
			// c.setTel(Tel);
			// c.setEmail(email);
			// c.setOccupation(occupation);
			// c.setIntroducer(introducer);
			// c.setAddress(address);
			// c.setRemarks(remarks);
			// c.setBasicCondition(basicCondition);
			c.setGroupId(groupId);
			c.setCaseId(caseId);
			caseList.add(c);
		}
		caseCursor.close();
		db.close();
		return caseList;
	}

	// 更改基本详情病例夹信息病例 2015-01-08
	public int updateGroupId(String autoId, String groupId, String updateTime) {

		SQLiteDatabase db = this.getWritableDatabase();
		String where = "autoId = ?";
		String[] whereValue = { autoId };
		ContentValues cv = new ContentValues();
		cv.put("groupId", groupId);
		cv.put("updateTime", updateTime);
		cv.put("needSync", "1");
		int row = db.update("CASES", cv, where, whereValue);
		db.close();
		return row;
	}

	/** 重置更新时间 */
	public int resetUpdateTime(String autoId) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "autoId = ?";
		String[] whereValue = { autoId };
		ContentValues cv = new ContentValues();
		cv.put("updateTime", CalendarNow.nowDate());
		cv.put("needSync", "1");
		int row = db.update("CASES", cv, where, whereValue);
		return row;
	}

	// 删除病例
	public void delete(String autoId) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "autoId = ?";
		String[] whereValue = { autoId };
		db.delete("CASES", where, whereValue);
	}
	// 删除病例
	public void deleteLogic(String autoId) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "autoId = ?";
		String[] whereValue = { autoId };
		ContentValues cv = new ContentValues();
		cv.put("caseVersion", "-1");
		cv.put("needSync", "1");
		db.update("CASES", cv, where, whereValue);
	}
	// 更新病例 2015-01-08
	public int update(String autoId, String department, String visitingTime,
			String patientName, String patientSex, String patientAge,
			String birthday, String personId, String speciesOne,
			String numberOne, String spaciesTwo, String numberTwo,
			String contact, String phoneNumber, String Tel, String email,
			String occupation, String introducer, String address,
			String remarks, String updateTime,String caseVersion,boolean isSynced) {

		SQLiteDatabase db = this.getWritableDatabase();
		String where = "autoId = ?";
		String[] whereValue = { autoId };
		ContentValues cv = new ContentValues();
		cv.put("department", department);
		cv.put("visitingTime", visitingTime);
		cv.put("patientName", patientName);
		cv.put("patientSex", patientSex);
		cv.put("patientAge", patientAge);
		cv.put("birthday", birthday);
		cv.put("personId", personId);
		cv.put("speciesOne", speciesOne);
		cv.put("numberOne", numberOne);
		cv.put("spaciesTwo", spaciesTwo);
		cv.put("numberTwo", numberTwo);
		cv.put("contact", contact);
		cv.put("phoneNumber", phoneNumber);
		cv.put("Tel", Tel);
		cv.put("email", email);
		cv.put("occupation", occupation);
		cv.put("introducer", introducer);
		cv.put("address", address);
		cv.put("remarks", remarks);
		cv.put("updateTime", updateTime);
		
		if(caseVersion!=null&&!caseVersion.equals("")){
			cv.put("caseVersion", caseVersion);
		}else{
			cv.put("caseVersion", "0");
		}
		if(isSynced){
			cv.put("needSync", "0");
		}else{
			cv.put("needSync", "1");
		}
		
		int row = db.update("CASES", cv, where, whereValue);
		return row;
	}
	
	// 更新病例 2015-01-08
		public int updateCaseId(String autoId,String caseId,String userId,boolean isSynced) {

			SQLiteDatabase db = this.getWritableDatabase();
			String where = "autoId = ?";
			String[] whereValue = { autoId };
			ContentValues cv = new ContentValues();
			
			cv.put("caseId", caseId);
			cv.put("userId", userId);
			
			if(isSynced){
				cv.put("needSync", "0");
			}else{
				cv.put("needSync", "1");
			}
			
			int row = db.update("CASES", cv, where, whereValue);
			return row;
		}
		public int updateCaseVersion(String autoId,String version,boolean isSynced) {

			SQLiteDatabase db = this.getWritableDatabase();
			String where = "autoId = ?";
			String[] whereValue = { autoId };
			ContentValues cv = new ContentValues();
			
			cv.put("caseVersion", version);
			
			if(isSynced){
				cv.put("needSync", "0");
			}else{
				cv.put("needSync", "1");
			}
			
			int row = db.update("CASES", cv, where, whereValue);
			return row;
		}
	// 更改基本详情病例夹信息病例 2015-01-08
	public int updateBasicCondition(String autoId, String basicCondition,
			String updateTime) {

		SQLiteDatabase db = this.getWritableDatabase();
		String where = "autoId = ?";
		String[] whereValue = { autoId };
		ContentValues cv = new ContentValues();
		cv.put("basicCondition", basicCondition);
		cv.put("updateTime", updateTime);
		cv.put("needSync", "1");
		int row = db.update("CASES", cv, where, whereValue);
		return row;
	}

	// 更新病例 2015-02-09
	public int update_group(String autoId, String groupId) {

		SQLiteDatabase db = this.getWritableDatabase();
		String where = "autoId = ?";
		String[] whereValue = { autoId };
		ContentValues cv = new ContentValues();
		cv.put("groupId", groupId);
		cv.put("needSync", "1");
		int row = db.update("CASES", cv, where, whereValue);
		return row;
	}
	// 得到分组表中id的最大值并且+1
		public String getMaxIdAddOne() {
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.query("CASES", null, null, null, null, null, null);
			if (cursor.moveToLast()) {
				String maxId = cursor.getString(cursor.getColumnIndex("autoId"));
				int i = Integer.parseInt(maxId);
				maxId = (i + 1) + "";// 加一
				cursor.close();
				db.close();
				return maxId;
			}
			return "1";
		}
}
