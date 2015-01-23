package model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Question {
	public Question() {	}
	@Id 	
	String question;
	String category;
	int level;

	@OneToMany(mappedBy = "quest")
	List<Answer> answers;
	protected String getQuestion() {
		return question;
	}
	protected void setQuestion(String question) {
		this.question = question;
	}
	protected String getCategory() {
		return category;
	}
	protected void setCategory(String category) {
		this.category = category;
	}
	protected int getLevel() {
		return level;
	}
	protected void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "Question " + question + ", category=" + category
				+ ", level=" + level;
	}
}
