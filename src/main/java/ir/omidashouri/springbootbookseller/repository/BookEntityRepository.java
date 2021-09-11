package ir.omidashouri.springbootbookseller.repository;

import ir.omidashouri.springbootbookseller.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookEntityRepository extends JpaRepository<BookEntity,Long> {
}
