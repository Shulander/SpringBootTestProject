package us.vicentini.ws.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

/**
 *
 * @author Shulander
 */
@MappedSuperclass
public class ReferenceEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private Long id;
    
    @NotNull
    private String code;
    
    @NotNull
    private String label;
    
    @NotNull
    private Integer ordinal;
    
    @NotNull
    private DateTime effectiveAt;
    
    private DateTime expiresAt;
    
    private DateTime createdAt;

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

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }
    
    public DateTime getEffectiveAt() {
        return effectiveAt;
    }

    public void setEffectiveAt(DateTime effectiveAt) {
        this.effectiveAt = effectiveAt;
    }

    public DateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(DateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReferenceEntity other = (ReferenceEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
