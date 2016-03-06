package cn.itcast.dbutils.demo1;

public class Stu {
	private int SID;
	private String SNAME;
	private String SAGE;
	private String SEX;
	public Stu() {
		super();
	}
	public Stu(int sID, String sNAME, String sAGE, String sEX) {
		super();
		SID = sID;
		SNAME = sNAME;
		SAGE = sAGE;
		SEX = sEX;
	}
	public int getSID() {
		return SID;
	}
	public void setSID(int sID) {
		SID = sID;
	}
	public String getSNAME() {
		return SNAME;
	}
	public void setSNAME(String sNAME) {
		SNAME = sNAME;
	}
	public String getSAGE() {
		return SAGE;
	}
	public void setSAGE(String sAGE) {
		SAGE = sAGE;
	}
	public String getSEX() {
		return SEX;
	}
	public void setSEX(String sEX) {
		SEX = sEX;
	}
	@Override
	public String toString() {
		return "Stu [SID=" + SID + ", SNAME=" + SNAME + ", SAGE=" + SAGE
				+ ", SEX=" + SEX + "]";
	}
	
}
