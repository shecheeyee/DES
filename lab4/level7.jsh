ImList<String> findBestBooking(Request request, List<CabType> cabs) {
    ImList<BookOne> bookingList = new ImList<BookOne>();
    ImList<String> stringList = new ImList<String>();
    Comparator<BookOne> cmp = new BookComparator();
    for (CabType cab : cabs) {
        for (Service ser: cab.services()) {
            BookOne a = new BookOne(cab, request, ser);
            bookingList = bookingList.add(a);
        }
    }
    bookingList = bookingList.sort(cmp);
    for (BookOne ele: bookingList) {
        stringList = stringList.add(ele.toString());
    }
    return stringList;
}
