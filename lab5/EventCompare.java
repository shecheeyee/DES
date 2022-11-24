import java.util.Comparator;

class EventCompare implements Comparator<Event> {
    @Override
    public int compare(Event eventOne, Event eventTwo) {
        double time1 = eventOne.getTime();
        double time2 = eventTwo.getTime();
        double rank1 = eventOne.getPriorityInt();
        double rank2 = eventTwo.getPriorityInt();


        if (time1 == time2) {
            if (rank1 == rank2) {
                if (eventOne.getCus().getId() < eventTwo.getCus().getId()) {
                    return -1;
                } else if (eventOne.getCus().getId() > eventTwo.getCus().getId()) {
                    return 1;
                } else {
                    return 0;
                }       
            } else if (rank1 < rank2) {
                return -1;
            } else {
                return 1;
            }
        } else if (time1 < time2) {
            return -1;
        } else if (time1 > time2) {
            return 1;
        }
        return 0;
    }
}
