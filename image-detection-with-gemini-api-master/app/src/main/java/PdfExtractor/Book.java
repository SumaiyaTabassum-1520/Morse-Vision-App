package PdfExtractor;

import java.util.Arrays;
import java.util.List;

public class Book {
    private List<Page> pages;
    private int numberOfPages;

    public Book(int numberOfPages) {
        this.numberOfPages = numberOfPages;
        this.pages = Arrays.asList(new Page[numberOfPages]);
        // Initialize the pages with empty Page objects
        for (int i = 0; i < numberOfPages; i++) {
            pages.set(i, new Page(""));
        }
    }

    public void clearPages() {
        // Reset the list to a new, empty list, which removes all pages
        this.pages = Arrays.asList(new Page[0]);
    }



    public String getPageContent(int pageNumber) {
        return pages.get(pageNumber).getContents();
    }

    public void setPageContent(int pageNumber, String content) {
        pages.get(pageNumber).setContents(content);
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}
