package ir.omidashouri.springbootbookseller.services;

import ir.omidashouri.springbootbookseller.model.BookEntity;
import ir.omidashouri.springbootbookseller.repository.BookEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class BookEntityServiceImpl implements BookEntityService{

    private final BookEntityRepository bookEntityRepository;

    @Override
    public BookEntity saveBook(BookEntity newBook){

        newBook.setCreateTime(LocalDateTime.now());
        return newBook;
    }

    @Override
    public void deleteBook(Long id){
        bookEntityRepository.deleteById(id);
    }

    @Override
    public List<BookEntity> findAllBooks(){
        return bookEntityRepository.findAll();
    }
}
