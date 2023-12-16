package com.gini.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "routes")
public class Route {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "start_location", nullable = false)
    @EqualsAndHashCode.Include
    private String startLocation;

    @Column(name = "end_location", nullable = false)
    @EqualsAndHashCode.Include
    private String endLocation;

    @Column(name = "prices", nullable = false)
    private BigDecimal price;

    @OneToOne(mappedBy = "route")
    private Ticket ticket;

    @CreationTimestamp
    private OffsetDateTime departure;

    @UpdateTimestamp
    private OffsetDateTime arrival;


    public Route settingTicket (Ticket ticket){
        this.setTicket(ticket);
        return this;
    }


}
