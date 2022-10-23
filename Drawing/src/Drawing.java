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
        geometry = input;
    }

    @Override
    public void draw(Segment segment) {
        switch (segment.getDirection()) {
            case 1:
                for(int i = xCoordinate; i < xCoordinate + segment.getLength(); i++ ){
                    if( i < painting.length) {
                        painting[i][yCoordinate] = segment.getColor();
                        xCoordinate = i;
                    } else { break; }
                }
            case 2:
                for(int i = yCoordinate; i < yCoordinate + segment.getLength(); i++ ){
                    if( i < painting.length) {
                        painting[xCoordinate][i] = segment.getColor();
                        yCoordinate++;
                    } else { break; }
                }
            case -1:
                for(int i = xCoordinate; i > xCoordinate - segment.getLength(); i-- ){
                    if (i > 0 ){
                        painting[i][yCoordinate] = segment.getColor();
                        xCoordinate--;
                    } else { break; }

                }
            case -2:
                for(int i = yCoordinate; i > yCoordinate - segment.getLength(); i-- ){
                    if( i > 0 ) {
                        painting[xCoordinate][i] = segment.getColor();
                        yCoordinate--;
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
