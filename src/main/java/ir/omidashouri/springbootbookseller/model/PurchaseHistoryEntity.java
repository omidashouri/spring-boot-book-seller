package ir.omidashouri.springbootbookseller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TBL_PURCHASE_HISTORY")
public class PurchaseHistoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "PURCHASE_TIME")
    private LocalDateTime purchaseTime;

}

