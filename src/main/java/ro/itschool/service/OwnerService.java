package ro.itschool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Owner;
import ro.itschool.exception.OwnerNotFoundException;
import ro.itschool.repository.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    //Create Owner
    public Owner saveOwner(Owner owner) {
        if (owner.getId() != null && ownerRepository.existsById(owner.getId())) {
            throw new OwnerNotFoundException("Owner with ID " + owner.getId() + " already exists");
        }
        return ownerRepository.save(owner);
    }

    //Update Owner
    public Owner updateOwner(Owner owner) {
        if(owner.getId() == null || !ownerRepository.existsById(owner.getId())){
            throw new OwnerNotFoundException("Owner doesn't exist");
        }
        return ownerRepository.save(owner);
    }

    //Get All Owners
    public List<Owner> getAllOwners(){
        return ownerRepository.findAll();
    }

    //Get Owner By ID
    public Optional<Owner> getOwnerById(Integer id){
        return ownerRepository.findById(id);
    }

    //Delete Owner By ID
    public void deleteOwnerById(Integer id){
        ownerRepository.deleteById(id);
    }


}
