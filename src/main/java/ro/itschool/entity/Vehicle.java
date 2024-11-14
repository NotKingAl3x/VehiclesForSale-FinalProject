package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VEHICLES")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String vin;

    @Column(nullable = false)
    private String vehicleType;

    @Column(nullable = false)
    private Integer productionYear;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany
    @JoinTable(
            name = "vehicle_insurance",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_id")
    )
    private Set<Insurance> insurances;

    public Vehicle(String vin, String vehicleType, Integer productionYear, String manufacturer, String model, String color) {
        this.vin = vin;
        this.vehicleType = vehicleType;
        this.productionYear = productionYear;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;

    }

    public Vehicle(String vin, String vehicleType, Integer productionYear, String manufacturer, String model, String color, Owner owner) {
        this.vin = vin;
        this.vehicleType = vehicleType;
        this.productionYear = productionYear;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.owner = owner;
    }
}
