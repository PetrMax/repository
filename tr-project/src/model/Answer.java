package model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Answer {
	public Answer() {}
	@Id
	@GeneratedValue
	protected long keyQuestion;

	protected int numberOfAnswer;
	protected boolean answer;
	protected String answerText;

	@ManyToOne	
	Question quest;

	protected long getKeyQuestion() {
		return keyQuestion;
	}
	protected void setKeyQuestion(long keyQuestion) {
		this.keyQuestion = keyQuestion;
	}
	protected int getNumberOfAnswer() {
		return numberOfAnswer;
	}
	protected void setNumberOfAnswer(int numberOfAnswer) {
		this.numberOfAnswer = numberOfAnswer;
	}
	protected boolean isAnswer() {
		return answer;
	}
	protected void setAnswer(boolean answer) {
		this.answer = answer;
	}
	protected String getAnswerText() {
		return answerText;
	}
	protected void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	protected Question getQuest() {
		return quest;
	}
	protected void setQuest(Question quest) {
		this.quest = quest;
	}
	@Override
	public String toString() {
		return answerText;
	}


}
