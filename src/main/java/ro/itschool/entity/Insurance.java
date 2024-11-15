package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INSURANCE")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String policyNumber;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Double coverageAmount;

    @ManyToMany(mappedBy = "insurances")
    private Set<Owner> owners;

    @ManyToMany(mappedBy = "insurances")
    private Set<Vehicle> vehicles;

    public Insurance(String policyNumber, String provider, LocalDate startDate, LocalDate endDate, Double coverageAmount) {
        this.policyNumber = policyNumber;
        this.provider = provider;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverageAmount = coverageAmount;
    }

}
