package model;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Answer {
	public Answer() {}
	
	@Many-To-One
        Question question;
        
	@Id(GeneratedValue)
	protected long id;// auto genereted
        protected int num; // number of answer 
	protected String textAnswer;
	protected boolean answer; // this is true answer number 

}
