package org.TeamCodeDefy.googleBooksApi;

import org.TeamCodeDefy.entities.Book;

/**
* Conversion methods for Google Book Response
* */
public class GoogleBookConversion {

    /**
     * Map Google Book Response from Google Books API to Book Entity
     **/
    public Book mapToBookEntity(GoogleBookResponse googleBookResponse, Book book) {

        VolumeInfo bookInfo = googleBookResponse.getItems().get(0).getVolumeInfo();

        book.setTitle(bookInfo.getTitle());
        book.setAuthor(String.join(";", bookInfo.getAuthors()));
        // TODO book isbn - see ItemsItem
        book.setDescription(bookInfo.getDescription());
        book.setPublisher(bookInfo.getPublisher());
        book.setLanguage(bookInfo.getLanguage());
        book.setPageCount(bookInfo.getPageCount());
        // TODO average rating?? not available?
        book.setThumbnailLink(bookInfo.getImageLinks().getThumbnail());

        return book;
    }
}
