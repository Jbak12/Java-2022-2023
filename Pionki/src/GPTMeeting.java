import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class GPTMeeting implements MeetingInterface {
    private List<PawnPosition> pawns;
    private Position meetingPoint;

    public GPTMeeting() {
        pawns = new ArrayList<PawnPosition>();
    }

    public void addPawns(List<PawnPosition> positions) {
        pawns.addAll(positions);
    }

    public void addMeetingPoint(Position meetingPointPosition) {
        meetingPoint = meetingPointPosition;
    }

    public void move() {
        boolean moved = true;
        while (moved) {
            moved = false;
            for (PawnPosition pawn : pawns) {
                int x = pawn.x();
                int y = pawn.y();
                if (x < meetingPoint.x()) {
                    x++;
                    moved = true;
                } else if (x > meetingPoint.x()) {
                    x--;
                    moved = true;
                }
                if (y < meetingPoint.y()) {
                    y++;
                    moved = true;
                } else if (y > meetingPoint.y()) {
                    y--;
                    moved = true;
                }
                if (moved) {
                   // pawn.setPosition(x, y);
                }
            }
        }
    }

    public Set<PawnPosition> getAllPawns() {
        return new HashSet<PawnPosition>(pawns);
    }

    public Set<PawnPosition> getNeighbours(int pawnId) {
        Set<PawnPosition> neighbours = new HashSet<PawnPosition>();
        PawnPosition pawn = null;
        for (PawnPosition p : pawns) {
            if (p.pawnId() == pawnId) {
                pawn = p;
                break;
            }
        }
        if (pawn != null) {
            int x = pawn.x();
            int y = pawn.y();
            for (PawnPosition p : pawns) {
                if (p.x() == x && p.y() == y + 1) {
                    neighbours.add(p);
                } else if (p.x() == x && p.y() == y - 1) {
                    neighbours.add(p);
                } else if (p.x() == x + 1 && p.y() == y) {
                    neighbours.add(p);
                } else if (p.x() == x - 1 && p.y() == y) {
                    neighbours.add(p);
                }
            }
        }
        return neighbours;
    }
}