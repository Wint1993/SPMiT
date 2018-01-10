package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private Warehouse warehouseStart;

    @OneToOne
    private Warehouse warehouseEnd;

    private String description;

    private String startRoute;

    private String endRoute;

   /* @JsonSerialize(using = ToStringSerializer.class)
  //  @JsonDeserialize(using = ParseDeserializer.class)
    private LocalDateTime startRoute1;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime endRoute1;*/

    @OneToOne
    private Transport transport;

    @OneToMany(mappedBy = "route")
    private List<Package> packages = new ArrayList<>();

    @JsonIgnore
    private boolean isArrived = false;
}
