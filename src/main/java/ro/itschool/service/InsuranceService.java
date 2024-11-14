package ro.itschool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Insurance;
import ro.itschool.exception.InsuranceNotFoundException;
import ro.itschool.repository.InsuranceRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    // Create insurance
    public Insurance saveInsurance(Insurance insurance) {
        if(insurance.getId() != null && insuranceRepository.existsById(insurance.getId())) {
            throw new InsuranceNotFoundException("Insurance with ID " + insurance.getId() + " already exists");
        }
        return insuranceRepository.save(insurance);
    }

    // Update insurance
    public Insurance updateInsurance(Insurance insurance) {
        if (insurance.getId() == null || !insuranceRepository.existsById(insurance.getId())) {
            throw new InsuranceNotFoundException("Insurance with ID " + insurance.getId() + " does not exist.");
        }
        return insuranceRepository.save(insurance);
    }

    // Get insurance by id
    public Optional<Insurance> getInsuranceById(Integer id) {
        return insuranceRepository.findById(id);
    }

    // Get all insurances
    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }

    // Delete insurance by ID
    public void deleteInsuranceById(Integer id) {
        if (!insuranceRepository.existsById(id)) {
            throw new InsuranceNotFoundException("Insurance with ID " + id + " does not exist.");
        }
        insuranceRepository.deleteById(id);
    }
}
