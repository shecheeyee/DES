
class Server{
    private final String name;

    Server(String name){
        this.name = name;
    }

    @Override
    public String toString(){
            return " served by " + name;
    }



}

