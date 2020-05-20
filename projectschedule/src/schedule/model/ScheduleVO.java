package schedule.model;

public class ScheduleVO {
	private int seq;
	private String id;
	private String sdate;
	private String schedule;
	private String memo;
	
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Override
	public String toString() {
		return "ScheduleVO [seq=" + seq + ", sdate=" + sdate + ", id=" + id + ", schedule=" + schedule + ", memo="
				+ memo + "]";
	}
	


	

}
