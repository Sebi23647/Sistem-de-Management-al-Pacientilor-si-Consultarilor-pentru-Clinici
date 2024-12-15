package ro.digitalnation.entities.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.digitalnation.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	 Optional<Admin> findByEmail(String email);
}
