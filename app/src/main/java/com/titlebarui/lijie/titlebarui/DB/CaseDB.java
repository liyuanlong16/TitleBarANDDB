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

	/** ���ݲ������������id���в�ѯ�õ����и÷����µĲ��� */
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
		if (myCursor.moveToFirst()) {// �ж��α��Ƿ�Ϊ��
			do {
				// myCursor.move(i);// �ƶ���ָ����¼
				

				String autoId = myCursor.getString(myCursor
						.getColumnIndex("autoId"));
				String caseid = myCursor.getString(myCursor
						.getColumnIndex("caseId"));
				String department = myCursor.getString(myCursor
						.getColumnIndex("department"));// ����
				String visitingTime = myCursor.getString(myCursor
						.getColumnIndex("visitingTime"));// ����ʱ��
				String updateTime = myCursor.getString(myCursor
						.getColumnIndex("updateTime"));// ����ʱ��
				String createTime = myCursor.getString(myCursor
						.getColumnIndex("createTime"));// ����ʱ��
				String patientName = myCursor.getString(myCursor
						.getColumnIndex("patientName"));// ��������
				String patientSex = myCursor.getString(myCursor
						.getColumnIndex("patientSex"));// �����Ա�
				String patientAge = myCursor.getString(myCursor
						.getColumnIndex("patientAge"));// ��������
				String birthday = myCursor.getString(myCursor
						.getColumnIndex("patientSex"));// ��������
				String speciesOne = myCursor.getString(myCursor
						.getColumnIndex("speciesOne"));// �������1
				String numberOne = myCursor.getString(myCursor
						.getColumnIndex("numberOne"));// ���1
				String spaciesTwo = myCursor.getString(myCursor
						.getColumnIndex("spaciesTwo"));// �������2
				String numberTwo = myCursor.getString(myCursor
						.getColumnIndex("numberTwo"));// ���2
				String contact = myCursor.getString(myCursor
						.getColumnIndex("contact"));// ��ϵ��
				String phoneNumber = myCursor.getString(myCursor
						.getColumnIndex("phoneNumber"));// ��ϵ���ֻ�����
				
				String Tel = myCursor.getString(myCursor.getColumnIndex("Tel"));// �̶��绰
				
				
				String email = myCursor.getString(myCursor
						.getColumnIndex("email"));// ����
				String occupation = myCursor.getString(myCursor
						.getColumnIndex("occupation"));// ְҵ
				String introducer = myCursor.getString(myCursor
						.getColumnIndex("introducer"));// ������
				String address = myCursor.getString(myCursor
						.getColumnIndex("address"));// ��ַ
				String remarks = myCursor.getString(myCursor
						.getColumnIndex("remarks"));// �����ı���Ϣ
				String basicCondition = myCursor.getString(myCursor
						.getColumnIndex("basicCondition"));// ��������
				String groupId = myCursor.getString(myCursor
						.getColumnIndex("groupId"));// �������id
				
				String caseVersion = myCursor.getString(myCursor
						.getColumnIndex("caseVersion"));// �������id
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
			//û��ָ���û�ʱȡ���������û������ǵ�
			String[] args = {"-1"};
			myCursor = db.query(TABLE_NAME, null,"userId is null and caseVersion!=?", args, null, null,
					null);
		}else{
			if(sync){//������Ҫͬ����
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
		if (myCursor.moveToFirst()) {// �ж��α��Ƿ�Ϊ��
			do {
				// myCursor.move(i);// �ƶ���ָ����¼
				String autoId = myCursor.getString(myCursor
						.getColumnIndex("autoId"));
				String caseid = myCursor.getString(myCursor
						.getColumnIndex("caseId"));
				String department = myCursor.getString(myCursor
						.getColumnIndex("department"));// ����
				String visitingTime = myCursor.getString(myCursor
						.getColumnIndex("visitingTime"));// ����ʱ��
				String updateTime = myCursor.getString(myCursor
						.getColumnIndex("updateTime"));// ����ʱ��
				String createTime = myCursor.getString(myCursor
						.getColumnIndex("createTime"));// ����ʱ��
				String patientName = myCursor.getString(myCursor
						.getColumnIndex("patientName"));// ��������
				String patientSex = myCursor.getString(myCursor
						.getColumnIndex("patientSex"));// �����Ա�
				String patientAge = myCursor.getString(myCursor
						.getColumnIndex("patientAge"));// ��������
				String birthday = myCursor.getString(myCursor
						.getColumnIndex("patientSex"));// ��������
				String speciesOne = myCursor.getString(myCursor
						.getColumnIndex("speciesOne"));// �������1
				String numberOne = myCursor.getString(myCursor
						.getColumnIndex("numberOne"));// ���1
				String spaciesTwo = myCursor.getString(myCursor
						.getColumnIndex("spaciesTwo"));// �������2
				String numberTwo = myCursor.getString(myCursor
						.getColumnIndex("numberTwo"));// ���2
				String contact = myCursor.getString(myCursor
						.getColumnIndex("contact"));// ��ϵ��
				String phoneNumber = myCursor.getString(myCursor
						.getColumnIndex("phoneNumber"));// ��ϵ���ֻ�����
				String Tel = myCursor.getString(myCursor.getColumnIndex("Tel"));// �̶��绰
				String email = myCursor.getString(myCursor
						.getColumnIndex("email"));// ����
				String occupation = myCursor.getString(myCursor
						.getColumnIndex("occupation"));// ְҵ
				String introducer = myCursor.getString(myCursor
						.getColumnIndex("introducer"));// ������
				String address = myCursor.getString(myCursor
						.getColumnIndex("address"));// ��ַ
				String remarks = myCursor.getString(myCursor
						.getColumnIndex("remarks"));// �����ı���Ϣ
				String basicCondition = myCursor.getString(myCursor
						.getColumnIndex("basicCondition"));// ��������
				String groupId = myCursor.getString(myCursor
						.getColumnIndex("groupId"));// �������id
				String caseVersion = myCursor.getString(myCursor
						.getColumnIndex("caseVersion"));// �������id

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
		if (myCursor.moveToFirst()) {// �ж��α��Ƿ�Ϊ��
			do {
				// myCursor.move(i);// �ƶ���ָ����¼
				
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
				System.out.println("���ҵ�����Case----->autoId"+autoId+"-----caseId"+caseId+"------>caseVersion"+caseVersion);
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

	// ���ݲ����в������ֽ���ģ����ѯ
	public List<Case> selectBypatientName(String patientName_) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from CASES where patientName like '%"
				+ patientName_ + "%'";
		Cursor CaseCursor = (Cursor) db.rawQuery(sql, null);
		List<Case> caseList = new ArrayList<Case>();
		if (CaseCursor.moveToFirst()) {// �ж��α��Ƿ�Ϊ��
			do {
				// myCursor.move(i);// �ƶ���ָ����¼
				String caseid = CaseCursor.getString(CaseCursor
						.getColumnIndex("caseId"));
				String caseAutoId = CaseCursor.getString(CaseCursor
						.getColumnIndex("autoId"));
				String department = CaseCursor.getString(CaseCursor
						.getColumnIndex("department"));// ����
				String visitingTime = CaseCursor.getString(CaseCursor
						.getColumnIndex("visitingTime"));// ����ʱ��
				String updateTime = CaseCursor.getString(CaseCursor
						.getColumnIndex("updateTime"));// ����ʱ��
				String createTime = CaseCursor.getString(CaseCursor
						.getColumnIndex("createTime"));// ����ʱ��
				String patientName = CaseCursor.getString(CaseCursor
						.getColumnIndex("patientName"));// ��������
				String patientSex = CaseCursor.getString(CaseCursor
						.getColumnIndex("patientSex"));// �����Ա�
				String patientAge = CaseCursor.getString(CaseCursor
						.getColumnIndex("patientAge"));// ��������
				String birthday = CaseCursor.getString(CaseCursor
						.getColumnIndex("patientSex"));// ��������
				String speciesOne = CaseCursor.getString(CaseCursor
						.getColumnIndex("speciesOne"));// �������1
				String numberOne = CaseCursor.getString(CaseCursor
						.getColumnIndex("numberOne"));// ���1
				String spaciesTwo = CaseCursor.getString(CaseCursor
						.getColumnIndex("spaciesTwo"));// �������2
				String numberTwo = CaseCursor.getString(CaseCursor
						.getColumnIndex("numberTwo"));// ���2
				String contact = CaseCursor.getString(CaseCursor
						.getColumnIndex("contact"));// ��ϵ��
				String phoneNumber = CaseCursor.getString(CaseCursor
						.getColumnIndex("phoneNumber"));// ��ϵ���ֻ�����
				String Tel = CaseCursor.getString(CaseCursor
						.getColumnIndex("Tel"));// �̶��绰
				String email = CaseCursor.getString(CaseCursor
						.getColumnIndex("email"));// ����
				String occupation = CaseCursor.getString(CaseCursor
						.getColumnIndex("occupation"));// ְҵ
				String introducer = CaseCursor.getString(CaseCursor
						.getColumnIndex("introducer"));// ������
				String address = CaseCursor.getString(CaseCursor
						.getColumnIndex("address"));// ��ַ
				String remarks = CaseCursor.getString(CaseCursor
						.getColumnIndex("remarks"));// �����ı���Ϣ
				String basicCondition = CaseCursor.getString(CaseCursor
						.getColumnIndex("basicCondition"));// ��������

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

	/** ���ݲ������������id���в�ѯ�õ����и÷����µĲ������� */
	public int getCaseGroupIdCount(String groupId) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from CASES where groupId = " + groupId;
		Cursor cursor = (Cursor) db.rawQuery(sql, null);
		int count = cursor.getCount();
		return count;
	}

	// ���ݲ���id��ȡ������Ϣ
	public List<Case> selectById(String id) {
		List<Case> caseList = new ArrayList<Case>();
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from CASES where autoId = " + id;
		Cursor caseCursor = (Cursor) db.rawQuery(sql, null);
		if (caseCursor.moveToFirst()) {// �ж��α��Ƿ�Ϊ��
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
	// ���ݲ���id��ȡ������Ϣ
		public Case selectByCaseId(String id) {
			Case casee = new Case();
			SQLiteDatabase db = this.getWritableDatabase();
			String sql = "select * from CASES where caseId = '" + id+"'";
			Cursor caseCursor = (Cursor) db.rawQuery(sql, null);
			if (caseCursor.moveToFirst()) {// �ж��α��Ƿ�Ϊ��
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
		// ���ݲ���id��ȡ������Ϣ
				public Case selectByCaseAutoId(String CaseAutoId) {
					Case casee = new Case();
					SQLiteDatabase db = this.getWritableDatabase();
					String sql = "select * from CASES where autoId = '" + CaseAutoId+"'";
					Cursor caseCursor = (Cursor) db.rawQuery(sql, null);
					if (caseCursor.moveToFirst()) {// �ж��α��Ƿ�Ϊ��
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
	// ����groupId��ȡ������Ϣ
	public List<Case> selectBygroupId(String id) {
		List<Case> caseList = new ArrayList<Case>();
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from CASES where groupId = " + id;
		Cursor caseCursor = (Cursor) db.rawQuery(sql, null);
		if (caseCursor.moveToFirst()) {// �ж��α��Ƿ�Ϊ��
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

	// ���Ļ������鲡������Ϣ���� 2015-01-08
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

	/** ���ø���ʱ�� */
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

	// ɾ������
	public void delete(String autoId) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "autoId = ?";
		String[] whereValue = { autoId };
		db.delete("CASES", where, whereValue);
	}
	// ɾ������
	public void deleteLogic(String autoId) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "autoId = ?";
		String[] whereValue = { autoId };
		ContentValues cv = new ContentValues();
		cv.put("caseVersion", "-1");
		cv.put("needSync", "1");
		db.update("CASES", cv, where, whereValue);
	}
	// ���²��� 2015-01-08
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
	
	// ���²��� 2015-01-08
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
	// ���Ļ������鲡������Ϣ���� 2015-01-08
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

	// ���²��� 2015-02-09
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
	// �õ��������id�����ֵ����+1
		public String getMaxIdAddOne() {
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.query("CASES", null, null, null, null, null, null);
			if (cursor.moveToLast()) {
				String maxId = cursor.getString(cursor.getColumnIndex("autoId"));
				int i = Integer.parseInt(maxId);
				maxId = (i + 1) + "";// ��һ
				cursor.close();
				db.close();
				return maxId;
			}
			return "1";
		}
}
