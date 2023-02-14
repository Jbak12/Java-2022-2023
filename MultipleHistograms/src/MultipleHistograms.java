import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class MultipleHistograms implements Histogram {
    private Consumer<HistogramResult> histogramConsumer;
    private Map<Integer, Map<Integer, Integer>> histograms = new ConcurrentHashMap<>();

    @Override
    public void setup(int bins, Consumer<HistogramResult> histogramConsumer) {
        this.histogramConsumer = histogramConsumer;
    }

    @Override
    public void addVector(int vectorID, Vector vector) {

        HistogramTask task   = new HistogramTask(vectorID,vector);
        Thread thread = new Thread(task);
        thread.start();
    }

    public void printOut(int vectorId, Map<Integer, Integer> histogram  ) {

        Runnable task = new Runnable() {
            @Override
            public void run() {
                histogramConsumer.accept(new HistogramResult(vectorId, histogram));
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }
    private class HistogramTask implements Runnable {
        Map<Integer, Integer> histogram = new ConcurrentHashMap<>();

        int vectorID;

        Vector vector;
        public HistogramTask(int vectorID, Vector vector) {
            this.vectorID = vectorID;
            this.vector = vector;
        }

        @Override
        public void run() {
            int size = vector.getSize();
            for (int i = 0; i < size; i++) {
                int key = vector.getValue(i);
                histogram.compute(key, (k, v) -> v == null ? 1 : v + 1);
            }

            histograms.put(vectorID, histogram);
            printOut(vectorID, histogram);
        }
    }
}
