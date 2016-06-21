package us.vicentini.ws.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Shulander
 */
@Entity
public class Role {
    
    @Id
    private Long id;
    
    private String code;
    
    private String label;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
    
}
