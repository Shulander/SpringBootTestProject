package us.vicentini.ws.model;

import javax.persistence.Entity;

/**
 * Greeting model class.
 * 
 * @author Shulander
 */
@Entity
public class Greeting extends TransactionalEntity {

    private String text;

    public Greeting() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
