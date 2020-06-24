package com.TextoPredictivo;

import java.util.BitSet;
import java.util.Random;
import java.util.Iterator;

public class AscensoALaColina implements InterDiccionario {
    private BitSet hashes;
    private RandomInRange randomInRange;
    private int numHashFunc;
    private static final double LN2 = 0.6931471805599453;
    private static final int MAX_ELEMENTS = 1400000;

    /**
     * Create a new bloom filter.
     * @param n Expected number of elements
     * @param m Desired size of the container in bits
     **/
    private AscensoALaColina(int n, int m) {
        numHashFunc = (int) Math.round(LN2 * m / n);
        if (numHashFunc <= 0) numHashFunc = 1;
        this.hashes = new BitSet(m);
        this.randomInRange = new RandomInRange(m, numHashFunc);
    }

    /**
     * Create a bloom filter of 1Mib.
     * @param n Expected number of elements
     **/
    private AscensoALaColina(int n) {
        this(n, 1024*1024*8);
    }

    private static class SingletonHelper{
        private static final AscensoALaColina INSTANCE = new AscensoALaColina(MAX_ELEMENTS);
    }

    public static AscensoALaColina getInstance(){
        return AscensoALaColina.SingletonHelper.INSTANCE;
    }


    public void add(String key) {
        randomInRange.init(key);
        for (RandomInRange r : randomInRange) hashes.set(r.value);
    }

    /**
     * Returns true if the element is in the container.
     * Returns false with a probability ≈ 1-e^(-ln(2)² * m/n)
     * if the element is not in the container.
     **/
    public boolean contains(String key) {
        randomInRange.init(key);
        for (RandomInRange r : randomInRange)
            if (!hashes.get(r.value))
                return false;
        return true;
    }

    private class RandomInRange
            implements Iterable<RandomInRange>, Iterator<RandomInRange> {

        private Random random;
        private int max;
        private int count;
        private int i = 0;
        public int value;

        RandomInRange(int maximum, int k) {
            max = maximum;
            count = k;
            random = new Random();
        }
        public void init(Object o) {
            random.setSeed(o.hashCode());
        }
        public Iterator<RandomInRange> iterator() {
            i = 0;
            return this;
        }
        public RandomInRange next() {
            i++;
            value = random.nextInt() % max;
            if (value<0) value = -value;
            return this;
        }
        public boolean hasNext() {
            return i < count;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

