package ir.omidashouri.springbootbookseller.repository;

import ir.omidashouri.springbootbookseller.model.PurchaseHistoryEntity;
import ir.omidashouri.springbootbookseller.projection.PurchaseItemProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseHistoryEntityRepository extends JpaRepository<PurchaseHistoryEntity, Long> {

    @Query("select b.title as title, b.price as price, ph.purchaseTime as purchaseTime " +
            " from PurchaseHistoryEntity  ph left join BookEntity b on " +
            " ph.bookId=b.id where ph.userId=:userId ")
    List<PurchaseItemProjection> findAllPurchaseOfUser(@Param("userId") Long userId);
}
