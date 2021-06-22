package CA.CAPS.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.enrolmentUPK;

public interface EnrolmentRepository extends JpaRepository<Enrolment, enrolmentUPK> {

}
