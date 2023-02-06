import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Pawn implements PawnPosition {
    private int pawnId;
    private int x;
    private int y;

    public Pawn(int pawnId, int x, int y) {
        this.pawnId = pawnId;
        this.x = x;
        this.y = y;
    }

    public int pawnId() {
        return pawnId;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class GPTMeeting2 implements MeetingInterface {
    private List<Pawn> pawns;
    private Position meetingPoint;

    public GPTMeeting2() {
        pawns = new ArrayList<Pawn>();
    }

    @Override
    public void addPawns(List<PawnPosition> positions) {
        for(PawnPosition pawn : positions) {
            Pawn pion = new Pawn(pawn.pawnId(),pawn.x(),pawn.y());
            pawns.add(pion);
        }

    }

    public void addMeetingPoint(Position meetingPointPosition) {
        meetingPoint = meetingPointPosition;
    }

    public void move() {
        boolean moved = true;
        while (moved) {
            moved = false;
            for (Pawn pawn : pawns) {
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
                    pawn.setPosition(x, y);
                }
            }
        }
    }

    public Set<PawnPosition> getAllPawns() {
        return new HashSet<PawnPosition>(pawns);
    }

    public Set<PawnPosition> getNeighbours(int pawnId) {
        Set<PawnPosition> neighbours = new HashSet<PawnPosition>();
        Pawn pawn = null;
        for (Pawn p : pawns) {
            if (p.pawnId() == pawnId) {
                pawn = p;
                break;
            }
        }
        if (pawn != null) {
            int x = pawn.x();
            int y = pawn.y();
            for (Pawn p : pawns) {
                if (p.x() == x && p.y() == y+1) {
                    neighbours.add(p);
                } else if (p.x() == x && p.y() == y-1) {
                    neighbours.add(p);
                } else if (p.x() == x+1 && p.y() == y) {
                    neighbours.add(p);
                }
            }
        }
        return neighbours;
    }
}