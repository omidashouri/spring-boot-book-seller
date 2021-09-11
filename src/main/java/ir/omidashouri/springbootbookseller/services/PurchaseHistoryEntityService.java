package ir.omidashouri.springbootbookseller.services;

import ir.omidashouri.springbootbookseller.model.PurchaseHistoryEntity;
import ir.omidashouri.springbootbookseller.projection.PurchaseItemProjection;

import java.util.List;

public interface PurchaseHistoryEntityService {
    PurchaseHistoryEntity savePurchaseHistory(PurchaseHistoryEntity newPurchaseHistory);

    List<PurchaseItemProjection> findPurchaseHistoryOfUser(Long userId);
}
