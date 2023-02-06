import java.util.*;

public class MeetingBruno implements MeetingInterface {
    private Map<Integer, PawnPosition> pawnIdToPawnPosition;
    private List<Integer> pawnIds;
    private Position meetingPointPosition;

    private record MoveRecord(int x, int y) {
    }

    public MeetingBruno() {
        this.pawnIds = new ArrayList<>();
        this.pawnIdToPawnPosition = new HashMap<>();
        this.meetingPointPosition = null;
    }

    @Override
    public void addPawns(List<PawnPosition> positions) {
        if (positions == null) return;
        for (var entry : positions) {
            this.pawnIds.add(entry.pawnId());
            pawnIdToPawnPosition.put(entry.pawnId(), entry);
        }
    }

    @Override
    public void addMeetingPoint(Position meetingPointPosition) {
        this.meetingPointPosition = meetingPointPosition;
    }

    @Override
    public void move() {
        if (this.meetingPointPosition == null || this.pawnIds == null) return;

        int roundCounter = 0;
        boolean hasUpdatedAnyPosition = false;

        do {
            roundCounter++;
            hasUpdatedAnyPosition = false;

            List<Integer> orderedPawnIds = new ArrayList<>(this.pawnIds);
            if (roundCounter % 2 == 0) {
                Collections.reverse(orderedPawnIds);
            }

            for (var id : orderedPawnIds) {
                var currentPosition = this.pawnIdToPawnPosition.get(id);
                var newPosition = calculatePawnMovePosition(currentPosition);
                if (this.isPositionFree(newPosition)) {
                    this.pawnIdToPawnPosition.put(id, newPosition);
                    hasUpdatedAnyPosition = true;
                }
            }
        } while (hasUpdatedAnyPosition);
    }

    @Override
    public Set<PawnPosition> getAllPawns() {
        this.move();
        return new HashSet<>(this.pawnIdToPawnPosition.values());
    }

    @Override
    public Set<PawnPosition> getNeighbours(int pawnId) {
        Set<PawnPosition> neighbours = new HashSet<>();
        var referencePawn = this.pawnIdToPawnPosition.get(pawnId);

        if (referencePawn == null) return neighbours;

        return new HashSet<>(
                this.pawnIdToPawnPosition.values().stream()
                        .filter(candidate -> this.areNeighbours(referencePawn, candidate))
                        .toList()
        );
    }

    private PawnPosition calculatePawnMovePosition(PawnPosition position) {
        int dx = this.getNumbersDistance(position.x(), this.meetingPointPosition.x());
        int dy = this.getNumbersDistance(position.y(), this.meetingPointPosition.y());

        MoveRecord moveRecord = dx > dy
                ? new MoveRecord(this.getStepTowards(position.x(), this.meetingPointPosition.x(), 1), 0)
                : new MoveRecord(0, this.getStepTowards(position.y(), meetingPointPosition.y(), 1));

        return new PawnPosition2D(position.pawnId(), position.x() + moveRecord.x(), position.y() + moveRecord.y());
    }

    private int getStepTowards(int from, int to, int maxStepDistance) {
        return Math.max(Math.min(to - from, 1), -1);
    }

    private boolean isPositionFree(Position position) {
        return this.pawnIdToPawnPosition.values().stream()
                .filter(p -> this.arePositionsEqual(p, position))
                .findAny()
                .orElse(null) == null;
    }

    private boolean arePositionsEqual(Position a, Position b) {
        return a.x() == b.x() && a.y() == b.y();
    }

    private boolean areNeighbours(PawnPosition a, PawnPosition b) {
        return a.pawnId() != b.pawnId()
                && this.getNumbersDistance(a.x(), b.x()) <= 1
                && this.getNumbersDistance(a.y(), b.y()) <= 1;
    }

    private int getNumbersDistance(int a, int b) {
        return Math.abs(a - b);
    }
}
