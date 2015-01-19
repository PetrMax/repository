package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TableQuestions {
	@Id
	int key;	
	String question;
	String kategory;
	int level;	
	
	@OneToMany(mappedBy="TableAnswer")
	List<TableAnswer> answer;
	
	protected int getId() {
		return key;
	}

	protected void setId(int id) {
		this.key = id;
	}

	protected String getQuestion() {
		return question;
	}

	protected void setQuestion(String question) {
		this.question = question;
	}

	protected String getKategory() {
		return kategory;
	}

	protected void setKategory(String kategory) {
		this.kategory = kategory;
	}

	protected int getLevel() {
		return level;
	}

	protected void setLevel(int level) {
		this.level = level;
	}

	protected List<TableAnswer> getAnswer() {
		return answer;
	}

	protected void setAnswer(List<TableAnswer> answer) {
		this.answer = answer;
	}

		@Override
	public String toString() {
		return "TableQuestions [id=" + key + ", question=" + question
				+ ", kategory=" + kategory + ", level=" + level + ", answer="
				+ answer + "]";
	}
	

}
