package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {
}
