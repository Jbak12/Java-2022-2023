class Tester {

    public static void main(String[] args) {
        Drawing drawing = new Drawing();
        TestGeometry geometry1 = new TestGeometry(10,1,2);
        Segment segment1 = new TestSegment(1,2,3);
        drawing.setCanvasGeometry(geometry1);
        drawing.draw(segment1);
        for(int[] arr : drawing.painting) {
            for(int i : arr) {
                System.out.printf(i + "\t");
            }
            System.out.print("\n");
        }
    }
}

class TestGeometry implements Geometry {

    int size;
    int x;
    int y;

    public TestGeometry (int _size, int _x, int _y) {
        size = _size;
        x = _x;
        y = _y;
    }

    public int getSize() {
        return size;
    }

    public int getInitialFirstCoordinate() {
        return x;
    }

    public int getInitialSecondCoordinate() {
        return y;
    }
}

class TestSegment implements Segment {

    int dir;
    int lenght;
    int color;

    public TestSegment (int _dir, int _length, int _color) {
        dir = _dir;
        lenght = _length;
        color = _color;
    }

    public int getDirection() {
        return dir;
    }

    public int getLength() {
        return lenght;
    }

    public int getColor() {
        return color;
    }
}