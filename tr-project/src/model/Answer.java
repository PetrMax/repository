package model;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Answer {
	public Answer() {}

	@Id
	protected String searchKey;
	protected String answer_1;
	protected String answer_2;
	protected String answer_3;
	protected String answer_4;
	protected int answer; // this is true answer number 
	protected String getSearchKey() {
		return searchKey;
	}
	protected void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	protected String getAnswer_1() {
		return answer_1;
	}
	protected void setAnswer_1(String answer_1) {
		this.answer_1 = answer_1;
	}
	protected String getAnswer_2() {
		return answer_2;
	}
	protected void setAnswer_2(String answer_2) {
		this.answer_2 = answer_2;
	}
	protected String getAnswer_3() {
		return answer_3;
	}
	protected void setAnswer_3(String answer_3) {
		this.answer_3 = answer_3;
	}
	protected String getAnswer_4() {
		return answer_4;
	}
	protected void setAnswer_4(String answer_4) {
		this.answer_4 = answer_4;
	}
	protected int getAnswer() {
		return answer;
	}
	protected void setAnswer(int answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Answer " + searchKey + ", answer_1=" + answer_1
				+ ", answer_2=" + answer_2 + ", answer_3=" + answer_3
				+ ", answer_4=" + answer_4 + ", answer=" + answer;
	}

}
