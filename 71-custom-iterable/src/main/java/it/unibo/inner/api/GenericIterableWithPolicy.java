package it.unibo.inner.api;

import java.util.Iterator;

@SuppressWarnings("rawtypes")
public class GenericIterableWithPolicy<T> implements IterableWithPolicy {

    final private T[] genericArray;

    private Predicate<T> policy;

    public GenericIterableWithPolicy(final T[] array) {
        this(array, new Predicate<T>() {

            @Override
            public boolean test(T elem) {
                return true;
            }

        });
    }

    public int getLength() {
        return this.genericArray.length;
    }

    public GenericIterableWithPolicy(final T[] array, final Predicate<T> anonymousPredicate) {
        this.genericArray = array;

        setIterationPolicy(anonymousPredicate);
    }

    public class InnerIterator implements Iterator {

        private int currentIndex;

        public InnerIterator() {
            this.currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            if (this.currentIndex < getLength()) {
                if (policy.test(genericArray[this.currentIndex])) {
                    return true;
                } else {
                    this.currentIndex++;
                    return this.hasNext();
                }
            } else {
                return false;
            }
        }

        @Override
        public Object next() {
            this.currentIndex++;
            if (policy.test(genericArray[this.currentIndex - 1])) {
                return genericArray[this.currentIndex - 1];
            } else {
                return this.next();
            }

        }

    }

    @Override
    public Iterator iterator() {
        return new InnerIterator();
    }

    @Override
    public void setIterationPolicy(Predicate filter) {
        this.policy = filter;
    }

}
