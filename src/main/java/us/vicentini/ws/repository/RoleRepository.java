package us.vicentini.ws.repository;

import java.util.Collection;
import java.util.Date;
import org.joda.time.DateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import us.vicentini.ws.model.Role;

/**
 *
 * @author Shulander
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Collection<Role> findByEffectiveAtBeforeAndExpiresAtAfterOrExpiresAtNullOrderByOrdinal(
            Date effectiveAt, Date expiresAt);

    @Query("select r from Role r where r.effectiveAt <= :effectiveAt "
            + "and (r.expiresAt is null or r.expiresAt > :effectiveAt) order by r.ordinal asc")
    Collection<Role> findAllEfective(@Param("effectiveAt") DateTime effectiveAt);
    
    Role findByCodeAndEffectiveAtBeforeAndExpiresAtAfterOrExpiresAtNull(
            String code, Date effectiveAt, Date expiresAt);
    
    @Query("select r from Role r where r.code = :code and r.effectiveAt <= :effectiveAt "
            + "and (r.expiresAt is null or r.expiresAt > :effectiveAt) order by r.ordinal asc")
    Role findByCodeAndEffective(@Param("code") String code, @Param("effectiveAt") DateTime effectiveAt);
}
