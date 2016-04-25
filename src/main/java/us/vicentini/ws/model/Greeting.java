package us.vicentini.ws.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Shulander
 */
@Entity
public class Greeting implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private String text;

	public Greeting() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
