package us.vicentini.ws.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

import us.vicentini.ws.util.RequestContext;

/**
 * Transactional Entity class.
 *
 * @author Shulander
 */
@MappedSuperclass
public class TransactionalEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String referenceId = UUID.randomUUID().toString();

    @Version
    private Integer version;

    @NotNull
    private String createdBy;

    @NotNull
    private DateTime createdAt;

    private String updatedBy;

    private DateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final TransactionalEntity other = (TransactionalEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @PrePersist
    public void beforePersist() {

        setCreatedAt(new DateTime());

        String username = RequestContext.getUsername();
        if (username == null) {
            throw new IllegalArgumentException(
                    "Cannot persist a TransactionalEntity without a username in the RequestContext for this thread.");
        }

        setCreatedBy(username);
    }

    @PreUpdate
    public void beforeUpdate() {
        setUpdatedAt(new DateTime());

        String username = RequestContext.getUsername();

        if (username == null) {
            throw new IllegalArgumentException(
                    "Cannot update a TransactionalEntity without a username in the RequestContext for this thread.");
        }

        setUpdatedBy(username);
    }
}
