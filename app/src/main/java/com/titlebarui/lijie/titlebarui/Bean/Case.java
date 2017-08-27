package com.titlebarui.lijie.titlebarui.Bean;

import java.io.Serializable;

import com.titlebarui.lijie.titlebarui.serialization.JsonField;

public class Case implements Serializable {
	
	
	public String autoId;//��������
	
	@JsonField(name = "caseId")
	public String caseId;// ����
	
	@JsonField(name = "department")
	public String department;//����
	
	@JsonField(name = "visitingTime")
	public String visitingTime;// ����ʱ��
	
	@JsonField(name = "updateTime")
	public String updateTime;// ����ʱ��
	
	@JsonField(name = "createTime")
	public String createTime;// ����ʱ��
	
	@JsonField(name = "patientName")
	public String patientName;// ����
	
	@JsonField(name = "patientSex")
	public String patientSex;// �Ա�
	
	@JsonField(name = "personId")
	public String personId;//���֤
	
	@JsonField(name = "patientAge")
	public String patientAge;//����
	
	@JsonField(name = "birthday")
	public String birthday;// ��������
	
	@JsonField(name = "speciesOne")
	public String speciesOne;// �������1
	
	@JsonField(name = "numberOne")
	public String numberOne;// �������1�ı��
	
	@JsonField(name = "spaciesTwo")
	public String spaciesTwo;//�������2
	
	@JsonField(name = "numberTwo")
	public String numberTwo;//����2�ı��
	
	@JsonField(name = "contact")
	public String contact;//��ϵ��
	
	@JsonField(name = "phoneNumber")
	public String phoneNumber;//�ֻ���
	
	@JsonField(name = "Tel")
	public String Tel;//�̻�
	
	@JsonField(name = "email")
	public String email;//����
	
	@JsonField(name = "occupation")
	public String occupation;//ְҵ
	
	@JsonField(name = "introducer")
	public String introducer;//������
	
	@JsonField(name = "address")
	public String address;//��ַ
	
	@JsonField(name = "remarks")
	public String remarks;//��Ӹ����ı���Ϣ
	
	@JsonField(name = "basicCondition")
	public String basicCondition;//��Ӹ��ಡ�����ϣ��������飩
	
	@JsonField(name = "caseVersion")
	public String caseVersion;//�����汾��
	
	@JsonField(name = "isDel")
	public String isDel;//�Ƿ�ɾ��
	
	public String userId;//���userId
	public String groupId;//���groupId
	
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getVisitingTime() {
		return visitingTime;
	}
	public void setVisitingTime(String visitingTime) {
		this.visitingTime = visitingTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	public String getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSpeciesOne() {
		return speciesOne;
	}
	public void setSpeciesOne(String speciesOne) {
		this.speciesOne = speciesOne;
	}
	public String getNumberOne() {
		return numberOne;
	}
	public void setNumberOne(String numberOne) {
		this.numberOne = numberOne;
	}
	public String getSpaciesTwo() {
		return spaciesTwo;
	}
	public void setSpaciesTwo(String spaciesTwo) {
		this.spaciesTwo = spaciesTwo;
	}
	public String getNumberTwo() {
		return numberTwo;
	}
	public void setNumberTwo(String numberTwo) {
		this.numberTwo = numberTwo;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getIntroducer() {
		return introducer;
	}
	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getBasicCondition() {
		return basicCondition;
	}
	public void setBasicCondition(String basicCondition) {
		this.basicCondition = basicCondition;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCaseVersion() {
		return caseVersion;
	}
	public void setCaseVersion(String caseVersion) {
		this.caseVersion = caseVersion;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	 

}
