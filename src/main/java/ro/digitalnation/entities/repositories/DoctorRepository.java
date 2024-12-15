package ro.digitalnation.entities.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.digitalnation.entities.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	 Optional<Doctor> findByEmail(String email);
	 List<Doctor> findByNume(String nume);
}
