package model;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Answer {
	public Answer() {}

	@Id
        protected String num; // auto genereted
	protected String textAnswer;
	protected boolean answer; // this is true answer number 


}
