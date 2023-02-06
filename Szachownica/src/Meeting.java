import java.util.*;
import java.util.stream.Collectors;


public class Meeting implements MeetingInterface {
    private List<PawnPosition2D> pawns;
    private Position meetingPoint;

    public Meeting() {
        pawns = new ArrayList<PawnPosition2D>();
    }

    @Override
    public void addPawns(List<PawnPosition> positions) {
        for(PawnPosition pawn : positions) {
            PawnPosition2D pion = new PawnPosition2D(pawn.pawnId(),pawn.x(),pawn.y());
            pawns.add(pion);
        }

    }

    public void addMeetingPoint(Position meetingPointPosition) {
        meetingPoint = meetingPointPosition;
    }

    public void move() {
        Integer moveCount = 0;
        boolean moved = true;
        while(moved) {
           List<PawnPosition2D> helpPawns = new ArrayList<>(this.pawns);
           moveCount++;
            moved = false;
            if (moveCount%2 == 0) {
                Collections.reverse(helpPawns);
           }
            for (int i = 0; i < helpPawns.size(); i++) {
                PawnPosition2D pawn = helpPawns.get(i);
                PawnPosition2D hypotethicalMove = calculateMove(pawn);
                if (isMovePossible(hypotethicalMove,helpPawns)) {
                    helpPawns.set(i,hypotethicalMove);
                    moved = true;
                }
            }
        }
    }


    PawnPosition2D calculateMove(PawnPosition2D pawn){
        Integer dx = meetingPoint.x() - pawn.x();
        Integer dy = meetingPoint.x() - pawn.x();
        if(Math.abs(dx) > Math.abs(dy)) {
            Integer increment = dx > 0 ? 1 : -1;
            return new PawnPosition2D(pawn.pawnId(),pawn.x()+increment, pawn.y());
        } else if ( (Math.abs(dx) <= Math.abs(dy))) {
            Integer increment = dy > 0 ? 1 : -1;
            return new PawnPosition2D(pawn.pawnId(),pawn.x()+increment, pawn.y()+increment);
        } else {
            return pawn;
        }
    }

    Boolean isMovePossible(PawnPosition2D pawn, List<PawnPosition2D> pawnList ) {
        return(pawnList.stream()
                .filter( pP2D -> pP2D.x() == pawn.x() && pP2D.y() == pawn.y())
                .collect(Collectors.toList()).isEmpty());

    }
    public Set<PawnPosition> getAllPawns() {
        return new HashSet<PawnPosition>(pawns);
    }

    public Set<PawnPosition> getNeighbours(int pawnId) {
        Set<PawnPosition> neighbours = new HashSet<PawnPosition>();
        PawnPosition2D pawn = null;
        for (PawnPosition2D p : pawns) {
            if (p.pawnId() == pawnId) {
                pawn = p;
                break;
            }
        }
        if (pawn != null) {
            for (PawnPosition2D p : pawns) {
                if(Math.abs(pawn.x()-p.x()) == 1 || (Math.abs(pawn.y()-p.y())) == 1) {
                    neighbours.add(p);
                }
            }
        }
        return neighbours;
    }
}