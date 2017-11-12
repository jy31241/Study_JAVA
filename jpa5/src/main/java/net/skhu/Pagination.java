package net.skhu;

public class Pagination {
	int pg = 1; // 현재 페이지
	int sz = 10; // 페이지 당 레코드 수
	int recordCount; // 총 레코드 수

	public int getPg() {
		return pg;
	}

	public void setPg(int pg) {
		this.pg = pg;
	}

	public int getSz() {
		return sz;
	}

	public void setSz(int sz) {
		this.sz = sz;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
}