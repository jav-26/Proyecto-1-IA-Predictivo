package com.TextoPredictivo;

import java.util.BitSet;
import java.util.Random;
import java.util.Iterator;

/**
 * Clase que implementa la búsqueda de las palabras por la búsqueda de Ascenso a La Colina
 */
public class AscensoALaColina implements InterDiccionario {
    private BitSet hashes;
    private RandomInRange randomInRange;
    private int numHashFunc;
    private static final double LN2 = 0.6931471805599453;
    private static final int MAX_ELEMENTS = 1400000;

    /**
     * recibe valores numéricos para crear una función Hash para comparar las palabras y determinar la mejor opción
     * @param n primer valor entero.
     * @param m segundo valor entero.
     */
    private AscensoALaColina(int n, int m) {
        numHashFunc = (int) Math.round(LN2 * m / n);
        if (numHashFunc <= 0) numHashFunc = 1;
        this.hashes = new BitSet(m);
        this.randomInRange = new RandomInRange(m, numHashFunc);
    }


    private AscensoALaColina(int n) {
        this(n, 1024*1024*8);
    }

    private static class SingletonHelper{
        private static final AscensoALaColina INSTANCE = new AscensoALaColina(MAX_ELEMENTS);
    }

    public static AscensoALaColina getInstance(){
        return AscensoALaColina.SingletonHelper.INSTANCE;
    }

    /**
     * Permite agregar una palabra a la lista en caso de que se necesite hacer (todas las palabras recolectadas).
     * @param key recibe la palabra a ser agregada.
     */
    public void add(String key) {
        randomInRange.init(key);
        for (RandomInRange r : randomInRange) hashes.set(r.value);
    }

    /**
     * Este método nos permite buscar la palabra para conocer si existe o no en búsqueda Ascenso a la Colina
     * @param key es la palabra a buscar.
     * @return true si ha sido encontrada y false en caso contrario.
     */
    public boolean contains(String key) {
        randomInRange.init(key);
        for (RandomInRange r : randomInRange)
            if (!hashes.get(r.value))
                return false;
        return true;
    }

    /**
     * Esta clase nos permite conocer el peso de las palabras iterando dentro de la lista de palabras.
     */
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

