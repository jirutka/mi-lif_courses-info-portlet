package cz.cvut.portal.kos.services.support;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
public class ListPaginator <T> implements Paginator<T> {

    private static final Logger LOG = LoggerFactory.getLogger(ListPaginator.class);

    private final DataFetcher<T> fetcher;
    private int itemsPerPage = 10;
    private int startIndex = 0;
    private boolean onLastPage = false;
    private List<T> data;


    public ListPaginator(DataFetcher<T> fetcher) {
        this.fetcher = fetcher;
    }
    public ListPaginator(int itemsPerPage, DataFetcher<T> fetcher) {
        this.itemsPerPage = itemsPerPage;
        this.fetcher = fetcher;
    }


    public static <T> ListPaginator<T> emptyList() {
        return new ListPaginator<T>(0, new DataFetcher<T>() {
            public List<T> fetchPage(int itemsPerPage, int startIndex) {
                return Collections.emptyList();
            }
        });
    }


    public void goToPage(int page) throws IllegalArgumentException {
        Assert.isTrue(page > 0, "Page number must be greater than zero");

        fetch((page - 1) * itemsPerPage);
    }

    public void goToNextPage() throws IllegalStateException {
        if (isLastPage()) throw new IllegalStateException("There's no next page");
        fetch(startIndex + itemsPerPage);
    }

    public void goToPreviousPage() throws IllegalStateException {
        if (isFirstPage()) throw new IllegalStateException("There's no previous page");
        fetch(startIndex - itemsPerPage);
    }

    public int page() {
        return startIndex / itemsPerPage + 1;
    }

    public int nextPage() {
        return isLastPage() ? page() : page() + 1;
    }

    public int prevPage() {
        return isFirstPage() ? page() : page() - 1;
    }

    public boolean isFirstPage() {
        return startIndex == 0;
    }

    public boolean isLastPage() {
        return onLastPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int items) {
        itemsPerPage = items;
        fetch(startIndex);
    }

    public Iterator<T> iterator() {
        if (data == null) fetch(startIndex);
        return data.iterator();
    }


    protected void fetch(int startIndex) {
        LOG.debug("Fetching items {} - {}", startIndex, startIndex + itemsPerPage);
        data = fetcher.fetchPage(itemsPerPage +1, startIndex);

        // test if it's the last page
        onLastPage = data.size() < itemsPerPage +1;
        if (data.size() > itemsPerPage) {
            data.remove(data.size() -1);  //remove last item
        }
        this.startIndex = startIndex;
    }


    public interface DataFetcher <T> {
        
        List<T> fetchPage(int itemsPerPage, int startIndex);
    }
}
