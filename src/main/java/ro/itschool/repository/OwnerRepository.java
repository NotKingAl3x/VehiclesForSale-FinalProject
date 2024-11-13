package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner,Integer> {
}
