package com.javaex.phone;

public class PersonVo {

	// 필드
	private String name;
	private String ph;
	private String company;
	private int personId;

	// 생성자
	public PersonVo() {
	}

	public PersonVo(String name, String ph, String company) {
		this.name = name;
		this.ph = ph;
		this.company = company;
	}

	public PersonVo(int personId, String name, String ph, String company ) {
		this.personId = personId;
		this.name = name;
		this.ph = ph;
		this.company = company;
		
	}

	// 메소드 g,s
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	// 메소드 일반
	@Override
	public String toString() {
		return "PersonVo [name=" + name + ", ph=" + ph + ", company=" + company + ", personId=" + personId + "]";
	}

}
