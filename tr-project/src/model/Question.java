package model;
import java.util.List;

import javax.persistence.*;
@Entity
public class Question {
	// вообще не менял этот класс , !!!! с него надо начать !!!!
@Id
String question;

@ManyToMany
List<Answer>answers;

@ManyToOne
Category publisher;

/*public String getTitle() {
	return question;
}

public void setTitle(String title) {
	this.question = title;
}
*/



}
