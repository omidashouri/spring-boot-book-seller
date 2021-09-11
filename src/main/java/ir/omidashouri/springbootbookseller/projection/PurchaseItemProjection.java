package ir.omidashouri.springbootbookseller.projection;


import java.time.LocalDateTime;


public interface PurchaseItemProjection {

    String getTitle();

    Double getPrice();

    LocalDateTime getPurchaseTime();

}
