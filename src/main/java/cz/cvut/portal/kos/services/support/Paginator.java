package cz.cvut.portal.kos.services.support;

/**
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
public interface Paginator <T> extends Iterable<T> {

    /**
     * Go to the page, i.e. fetch data and change state of this Paginator.
     *
     * @param page page number
     * @throws IllegalArgumentException if page number is less then 1.
     */
    void goToPage(int page) throws IllegalArgumentException;

    /**
     * Go to next page, i.e. fetch data and change state of this Paginator.
     */
    void goToNextPage();

    /**
     * Go to previous page, i.e. fetch data and change state of this Paginator.
     */
    void goToPreviousPage();

    /**
     * @return current page number
     */
    int page();

    /**
     * @return next page number
     */
    int nextPage();

    /**
     * @return previous page number
     */
    int prevPage();

    /**
     * @return <tt>true</tt> if Paginator is on the first page,
     *         <tt>false</tt> otherwise
     */
    boolean isFirstPage();

    /**
     * @return <tt>true</tt> if Paginator is on the last page,
     *         <tt>false</tt> otherwise
     */
    boolean isLastPage();

    /**
     * How many items to return per once.
     *
     * @return items per page
     */
    int getItemsPerPage();

    /**
     * How many items to return per once.
     *
     * @param items items per page
     */
    void setItemsPerPage(int items);

}
