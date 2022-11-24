ImList<String> findBestBooking(Request request, List<CabType> cabs) {
    ImList<BookOne> bookingList = new ImList<BookOne>();
    ImList<BookOne> tempList = new ImList<BookOne>();
    ImList<BookOne> auxList = new ImList<BookOne>();
    ImList<BookOne> auxiList = new ImList<BookOne>();
    ImList<String> stringList = new ImList<String>();
    for (CabType cab : cabs) {
        for (Service ser: cab.services()) {
            BookOne a = new BookOne(cab, request, ser);
            stringList = stringList.add(a.toString());
            bookingList = bookingList.add(a);
        }
    }
    
    tempList = tempList.add(bookingList.get(0));
    bookingList = bookingList.remove(0);
    for (int u = 0; u< bookingList.size(); u++) {
        if (tempList.size() >= 2) {
            for (int i = 0; i< tempList.size() -1; i++) {
                int s = bookingList.get(u).compareTo(tempList.get(i));
                int t = bookingList.get(u).compareTo(tempList.get(i+1));
                if (s < 0) {
                    auxList = auxList.add(bookingList.get(u));
                    tempList = auxList.addAll(tempList);
                  auxList = new ImList<BookOne>();
                    break;
                    } else if (s > 0 && t < 0) {
                        for (int j = 0; j<= i; j++) {
                            auxList = auxList.add(tempList.get(j));
                            }
                            for (int k = i + 1; k< tempList.size() - i; k++) {
                                auxiList = auxiList.add(tempList.get(k));
                            }
                            tempList = auxList.add(bookingList.get(u)).addAll(auxiList);
                            auxList = new ImList<BookOne>();
                            auxiList = new ImList<BookOne>();
                            break;
                    } else { 
                        tempList.add(bookingList.get(u));
                        break;
                    }
            }
        } else {
            if (bookingList.get(0).compareTo(tempList.get(0)) < 0) {
                auxList = auxList.add(bookingList.get(0));
                bookingList = bookingList.remove(0);
                tempList = auxList.addAll(tempList);
                auxList = new ImList<BookOne>();
            } else {
                tempList = tempList.add(bookingList.get(0));
                bookingList = bookingList.remove(0);
            }
        }
    }

    for (BookOne ele: tempList) {
        stringList = stringList.add(ele.toString());
        System.out.println(stringList);
        System.out.println("TESTINGTESTING");
    }
          
    return stringList;
}

    
 
