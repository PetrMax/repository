package model;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Question {
	public Question() {	}

	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id=0;

	@Id 
	@Column(columnDefinition="TEXT")
	private String question = "How to...";
	@Column(columnDefinition="TEXT")
	private String sampleQuestion;
	private String category;	
	private int level;

	@OneToMany(mappedBy = "quest")	
	List<Answer> answers;	

	public long getId() {
		return id;
	}
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
	protected String getSampleQuestion() {
		return sampleQuestion;
	}
	protected void setSampleQuestion(String sampleQuestion) {
		this.sampleQuestion = sampleQuestion;
	}
	@Override
	public String toString() {
		return "Question: " + question + "<br> Sample Question Text: "
				+ sampleQuestion + "<br> Category - " + category + "<br> level="
				+ level;
	}
}
