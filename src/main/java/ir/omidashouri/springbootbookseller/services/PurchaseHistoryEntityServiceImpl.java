package ir.omidashouri.springbootbookseller.services;

import ir.omidashouri.springbootbookseller.model.PurchaseHistoryEntity;
import ir.omidashouri.springbootbookseller.projection.PurchaseItemProjection;
import ir.omidashouri.springbootbookseller.repository.PurchaseHistoryEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class PurchaseHistoryEntityServiceImpl implements PurchaseHistoryEntityService{

    private final PurchaseHistoryEntityRepository purchaseHistoryEntityRepository;

    @Override
    public PurchaseHistoryEntity savePurchaseHistory(PurchaseHistoryEntity newPurchaseHistory){
        newPurchaseHistory.setPurchaseTime(LocalDateTime.now());
        return purchaseHistoryEntityRepository.save(newPurchaseHistory);
    }

    @Override
    public List<PurchaseItemProjection> findPurchaseHistoryOfUser(Long userId){
        return purchaseHistoryEntityRepository.findAllPurchaseOfUser(userId);
    }
}
