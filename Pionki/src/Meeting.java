import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Meeting implements MeetingInterface {
    Position meetingPoint = null;
    List<PawnPosition> positions = new ArrayList<>();

    Set<PawnPosition> allPawns = new HashSet<>();

    @Override
    public void addPawns(List<PawnPosition> positions) {
        this.positions = positions;
    }

    @Override
    public void addMeetingPoint(Position meetingPointPosition) {
        meetingPoint = meetingPointPosition;
    }

    @Override
    public void move() {
        Boolean hasMadeAnyMove = false;
        for (Position point: positions);
    }

    @Override
    public Set<PawnPosition> getAllPawns() {
        return allPawns;
    }

    @Override
    public Set<PawnPosition> getNeighbours(int pawnId) {
        return null;
    }

    Boolean arePointsNeighbours(Integer pawnId1, Integer pawnId2) {
        return true;
    }
}