package com.gini.repository;

import com.gini.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface TicketRepository extends JpaRepository<Ticket, String> {

    /************************** GET user tickets paginated **********************
     * <br>
     * I don't need to make 2 selects since each ticket has only one route (@ManyToOne)
     * and not a list of routes(@OneToMany) that can cause
     * pagination in the memory of the app.
     * */
    @Query("""
            SELECT t FROM Ticket t
                LEFT JOIN FETCH t.route
            WHERE t.customer.id = :customerId
            """)
    Page<Ticket> getUserTicketPaginated(Pageable pageable, String customerId);
//----------------------------------------------------------------------------------------------------------------------

    @Query("""
            SELECT t FROM Ticket t LEFT JOIN FETCH t.route
            """)
    Page<Ticket> getTicketPaginated(Pageable pageable);

    @Query("""
            SELECT t FROM Ticket t
                LEFT JOIN FETCH t.route
            WHERE t.route.startLocation =:destination OR t.route.endLocation =:destination
            """)
    Page<Ticket> getTicketsByDestination(Pageable pageable, String destination);

}
