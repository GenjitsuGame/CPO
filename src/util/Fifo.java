
package util;

/**
 *
 * @author Pascal Luttgens
 * @param <E> Le type d'élément à stocker dans la FIFO
 */
public interface Fifo<E> {

    void add(E e);

    E get();

}
