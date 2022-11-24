import java.util.Comparator; 

class BookComparator implements Comparator<BookOne> {

    @Override
    public int compare(BookOne one, BookOne two) {
        if (one.fare(one.getService()) < one.fare(two.getService())) {
            return -1;
        } else if (one.fare(one.getService()) > two.fare(two.getService())) {
            return 1;
        } else {
            if (one.getWait() < two.getWait()) {
                return -1;
            } else if (one.getWait() > two.getWait()) {
                return 1;
            } 
            return 0;
        }
    }
}

