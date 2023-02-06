import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
class DistanceToPointPair {
    int distance;
    LinesInterface.Point point;
    public DistanceToPointPair(int distance, LinesInterface.Point point) {
        this.distance = distance;
        this.point = point;
    }
}
public class Lines implements LinesInterface{

    //list of all points
    Set<Point> points = new HashSet<Point>();
    //list of all segments
    Set<Segment> segments = new HashSet<Segment>();
    //map of points and segments related to them, basically return value of getMapEndpointSegments
    Map<Point, Set<Segment>> pointsToSegmentsMap;
    // adjacency list
    Map<Point,List<Point>> adjacencyList;
    //dfs vsited map
    Map<Point,Boolean> visited = new HashMap<>();

    Map<Point, Map<Integer, Set<Point>>> reachableEndpointMap = new HashMap<>();

    Map<Point,DistanceToPointPair> PointToDistAndPoints = new HashMap<>();

    @Override

    public void addPoints(Set<Point> points) {
        for(Point point: points) {
            visited.put(point,false);
        }
        this.points.addAll(points);
    }

    @Override
    public void addSegments(Set<Segment> segments) {
        this.segments.addAll(segments);
        for(Segment segment: segments) {
//            pointsToSegmentsMap.put(segment.getEndpoint1(), pointsToSegmentsMap.get(segment.getEndpoint1()).add(segment));
//            pointsToSegmentsMap.put(segment.getEndpoint2(), segment);
            List<Point> nowalista = adjacencyList.get(segment.getEndpoint1());
            nowalista.add(segment.getEndpoint2());
            List<Point> nowalista2= adjacencyList.get(segment.getEndpoint2());
            nowalista2.add(segment.getEndpoint1());
            adjacencyList.put(segment.getEndpoint1(),nowalista)
            adjacencyList.put(segment.getEndpoint2(),nowalista2);
        }
        getMapEndpointToSegments();
    }

    @Override
    public List<Segment> findConnection(Point start, Point end) {
            Map<String, Boolean> visited = new HashMap<>();
        return null;
    }

    @Override
    public Map<Point, Set<Segment>> getMapEndpointToSegments() {
        Map<Point, Set<Segment>> map = new HashMap<> ();
        for (Point point: points) {
            Set<Segment> correctSegments = segments.stream()
                    .filter(s -> s.getEndpoint1() == point || s.getEndpoint2() == point)
                    .collect(Collectors.toSet());
            map.put(point,correctSegments);
        }
        this.pointsToSegmentsMap = map;
        return map;
    }



    @Override
    public Map<Point, Map<Integer, Set<Point>>> getReachableEndpoints() {

        for(Point point: points) {
            dfs(point,0,point);
        }
        return null;
    }
    public void dfs (Point startPoint, Integer deepcount,Point rootPoint) {
        visited.put(startPoint,true);
        DistanceToPointPair dystansik = new DistanceToPointPair(deepcount,startPoint);
        PointToDistAndPoints.put(rootPoint,dystansik);

        for(Point point : adjacencyList.get(startPoint)) {
            if(!visited.get(point)) {
                if(deepcount<4) {
                    dfs(point,deepcount+1,rootPoint);
                }
            }
        }
        visited.put(startPoint,false);
    }

    Map<Point, Map<Integer, Set<Point>>> convertMyStructureToHuj( Map<Point,DistanceToPointPair> struktura) {
        Map<Point, Map<Integer, Set<Point>>> endStructure = new HashMap<>();
        endStructure.entrySet();

        return endStructure;
    }
    public Set<Point> findPoints(Integer distance, Point point) {
        if (distance == 0) {
            return Collections.emptySet();
        } else {
            return Collections.emptySet();
        }
    }
}
