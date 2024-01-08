package com.gini.repository;

import com.gini.model.Route;
import com.gini.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface RouteRepository extends JpaRepository<Route, String> {


    @Query("""
                    SELECT r FROM Route r
            """)
    Page<Route> getAllRoutes(Pageable pageable);

    @Query("""
            SELECT r FROM Route r
            WHERE r.startLocation =:destination OR r.endLocation =:destination
            """)
    Page<Route> getRoutesByDestination(Pageable pageable, String destination);
}
