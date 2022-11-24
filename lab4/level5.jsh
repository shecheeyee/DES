public void findBestBooking(Request request, List<Driver> cabs) {
    ImList<BookOne> bookingList = new ImList<BookOne>();
    ImList<String> stringList = new ImList<String>();
    Comparator<BookOne> cmp = new BookComparator();
  
    for (Driver caby: cabs) {
        for (Service ser: caby.services()) {
            BookOne a = new BookOne(caby, request, ser);
            bookingList = bookingList.add(a);
        }
    }
    bookingList = bookingList.sort(cmp);
    for (BookOne ele: bookingList) {
        stringList = stringList.add(ele.toString());
    }
    for (int i = 0; i < stringList.size(); i++) {
        System.out.println(stringList.get(i));
    }   
}
