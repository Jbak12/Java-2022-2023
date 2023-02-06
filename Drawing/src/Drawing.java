import java.util.Arrays;

public class Drawing implements  SimpleDrawing {

    int[][] painting;
    int xCoordinate;
    int yCoordinate;

    Geometry geometry;

    @Override
    public void setCanvasGeometry(Geometry input) {
        painting = new int[input.getSize()][input.getSize()];
        for(int[] arr : painting) {
            for(int i : arr) {
                i = 0;
            }
        }
        xCoordinate = input.getInitialFirstCoordinate();
        yCoordinate = input.getInitialSecondCoordinate();

    }

    @Override
    public void draw(Segment segment) {
        switch (segment.getDirection()) {
            case 1:
                int x = xCoordinate;
                for(int i = x; i < x + segment.getLength(); i++ ){
                    if( i < painting.length) {
                        painting[i][yCoordinate] = segment.getColor();
                        xCoordinate = i;
                    } else { break; }
                }

        }
    }

    @Override
    public int[][] getPainting() {
        return  painting;
    }

    @Override
    public void clear() {
        for(int[] arr : painting) {
            for(int i : arr) {
                i = 0;
            }
        }
    }
}
