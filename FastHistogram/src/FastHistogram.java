import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FastHistogram implements Histogram {
    private int threads;
    private ExecutorService executor;
    private AtomicInteger readyThreads;
    private Map<Integer, Integer> histogram;
    private Vector vector;

    @Override
    public void setup(int threads, int bins) {
        this.threads = threads;
        this.executor = Executors.newFixedThreadPool(threads);
        this.histogram = new ConcurrentHashMap<>();
        this.readyThreads = new AtomicInteger(0);
    }

    @Override
    public void setVector(Vector vector) {
        this.vector = vector;
        this.readyThreads.set(0);
        this.histogram.clear();

        int size = vector.getSize();
        int chunkSize = size / threads;
        int start = 0;
        int end = chunkSize;

        for (int i = 0; i < threads; i++) {
            if (i == threads - 1) {
                end = size;
            }
            executor.submit(new HistogramTask(start, end));
            start = end;
            end += chunkSize;
        }
    }

    @Override
    public boolean isReady() {
        return readyThreads.get() == threads;
    }

    @Override
    public Map<Integer, Integer> histogram() {
        return histogram;
    }

    private class HistogramTask implements Runnable {
        private int start;
        private int end;

        public HistogramTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                int value = vector.getValue(i);
                synchronized (histogram) {
                    histogram.put(value, histogram.getOrDefault(value, 0) + 1);
                }
            }
            readyThreads.incrementAndGet();
        }
    }
}