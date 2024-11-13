package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Owner;
import ro.itschool.exception.OwnerNotFoundException;
import ro.itschool.service.OwnerService;

import java.util.List;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    // Create Owner
    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner){
        if (owner.getId() != null && ownerService.getOwnerById(owner.getId()).isPresent()) {
            throw new OwnerNotFoundException("Owner with ID " + owner.getId() + " already exists");
        }
        Owner createdOwner = ownerService.saveOwner(owner);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    // Update Owner
    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Integer id, @RequestBody Owner owner) {
        if (!ownerService.getOwnerById(id).isPresent()) {
            throw new OwnerNotFoundException("Owner with ID " + id + " doesn't exist");
        }
        owner.setId(id); // Ensure the ID from path variable is used for the update
        Owner updatedOwner = ownerService.updateOwner(owner);
        return new ResponseEntity<>(updatedOwner, HttpStatus.OK);
    }

    // Get All Owners
    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerService.getAllOwners();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    // Get Owner by ID
    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Integer id) {
        return ownerService.getOwnerById(id)
                .map(owner -> new ResponseEntity<>(owner, HttpStatus.OK))
                .orElseThrow(() -> new OwnerNotFoundException("Owner with ID " + id + " not found"));
    }

    // Delete Owner by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwnerById(@PathVariable Integer id) {
        if (!ownerService.getOwnerById(id).isPresent()) {
            throw new OwnerNotFoundException("Owner with ID " + id + " not found");
        }
        ownerService.deleteOwnerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
