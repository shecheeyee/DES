void serveCruises(List<Cruise> cruises, int numOfLoaders) {
    Loader loader = new Loader(numOfLoaders);
    int time = 0;
    for (Cruise c: cruises) {
        int cycles = (int) Math.ceil((double)c.getNumOfLoadersRequired() / (double)numOfLoaders);
        int arrive = c.getArrivalTime();
        if (numOfLoaders == 1) {
            if (arrive > time) {
                time = arrive;
            }
            for (int i=0;i<c.getNumOfLoadersRequired(); i++) {
                System.out.println(new Service(loader, c, time));
                time += c.getServiceTime();
            }
        } else {
            if (arrive > time) {
                time = arrive;
            } else if (arrive == time) {
                time = time;
            }
            int max;
            int min;
            if (c.getNumOfLoadersRequired() < numOfLoaders) {
                min = c.getNumOfLoadersRequired();
                max = numOfLoaders;
            } else {
                min = numOfLoaders;
                max = c.getNumOfLoadersRequired();
            }
            for (int k = 0; k < cycles; k++) {
                if (cycles >1  && k == cycles -1) {
                    min = max % min;
                }
                for (int j = 0;j < min; j++) {
                    System.out.println(new Service(loader, c, time));
                    loader = loader.nextLoader();
                    }
                    time += c.getServiceTime();
            }
            
        }
        
    }
}



  
