import java.util.*;

class Pos implements Position {

    int x,y;
    @Override
    public int getRow() {
        return this.y;
    }

    @Override
    public int getCol() {
        return this.x;
    }

    Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Pos(Position position) {
        this.x = position.getCol();
        this.y = position.getRow();
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pos other = (Pos) obj;

        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}


public class Graphics implements GraphicsInterface{


    CanvasInterface canvas;

    List<Pos> queue = new ArrayList<>();

    Map<Pos,Boolean> visited = new HashMap<>();

    void clearAll() {
        visited.clear();
        queue.clear();
    }
    @Override
    public void setCanvas(CanvasInterface canvas) {
        clearAll();
        this.canvas = canvas;
    }

    @Override
    public void fillWithColor(Position startingPosition, Color color) throws WrongStartingPosition, NoCanvasException {
        Pos startPosition  = new Pos(startingPosition);
        visited.put(startPosition, true);
        if(canvas == null) {
            throw new NoCanvasException();
        }
        try {
            canvas.setColor(startingPosition,color);
        } catch (CanvasInterface.CanvasBorderException ex){
            throw new WrongStartingPosition();
        } catch (CanvasInterface.BorderColorException ex) {
            try {
                canvas.setColor(startingPosition,ex.previousColor);
            } catch (CanvasInterface.BorderColorException| CanvasInterface.CanvasBorderException exc) {
                System.out.println("aaa jakis blad co do diaska");
            }
            throw new WrongStartingPosition();
        }

        List<Pos> neighbors = findNeighbors(startPosition);
        if(neighbors.size() > 0 ) {
            Pos n = neighbors.get(0);
            bfs(n,color);

        }


    }

    public void bfs(Pos startingPosition, Color color) {
        Boolean exceptionDidOccur;
        Pos position;
        queue.add(startingPosition);
        while(!queue.isEmpty()) {
            exceptionDidOccur = false;

            position = queue.get(0);
            queue.remove(0);
            visited.put(position,true);
            try {
                canvas.setColor(position,color);
            } catch (CanvasInterface.BorderColorException e) {
                try {
                    canvas.setColor(position,e.previousColor);
                } catch (CanvasInterface.CanvasBorderException | CanvasInterface.BorderColorException ex) {
                    System.out.println("oh no");
                }
                exceptionDidOccur = true;

            } catch(CanvasInterface.CanvasBorderException e) {
                System.out.println("out of canvas : (");
                exceptionDidOccur = true;
            }
            if(!exceptionDidOccur) {
                queue.addAll(findNeighbors(position));
            }

        }

    }

    List<Pos> findNeighbors(Pos position) {
        List<Pos> neighbors = new ArrayList<>();
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        Pos pos;
        for (int i = 0;i<4;i=i+1){
            pos = new Pos(position.getCol()+dx[i], position.getRow()+dy[i]);
            if (visited.getOrDefault(pos, false) == false) {
                neighbors.add(pos);
            }
        }
        return neighbors;
    }

//    public void ziomal(int x) throws MyExc {
//        if(x == 3) {
//            throw new MyExc();
//        }
//        System.out.println("");
//    }

//    public static void main(String[] args) throws MyExc {
//
//        Pos pos1 = new Pos(1,2);
//        Pos pos2 = new Pos(2,3);
//        Map<Pos,Boolean> map = new HashMap<>();
//        map.put(pos1,true);
//        System.out.println(pos1.hashCode());
//        System.out.println(pos2.hashCode());
//        System.out.println(map.get(new Pos(1,2)));
//        pos1 = new Pos(2,3);
//        System.out.println(map.get(pos2));
//        System.out.println(map.get(pos1));
//
//        Graphics graphics = new Graphics();
//        try {
//            graphics.ziomal(3);
//        } catch(MyExc e){
//            System.out.println("yeah przekazano 3");
//        }
//
//        System.out.println("KUUURDE");
//
//    }

}
//class MyExc extends Exception {
//    MyExc(){
//    }
//}