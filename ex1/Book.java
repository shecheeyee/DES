class Book{
	private final int pgs;
	private final int curr;

	Book(int pgs, int curr){
		this.pgs = pgs;
		this.curr = curr;
	}

	int getPgs(){
		return this.pgs;
	}
	
	int getCurr(){
		return this.curr;
	}

	Book nextPage(){
		if ( getCurr() < getPgs() ) {
			return new Book(getPgs(), getCurr() + 1);
		}
		else {
			System.out.println("This is the last page!");
			return new Book(getPgs(), getCurr());
		}
			
	}


	Book prevPage(){
		if ( getCurr() > 0 ) {
			return new Book(getPgs(), getCurr() - 1);
		}
		else {
			System.out.println("This is the first page!");
			return new Book(getPgs(), getCurr());
		}
	}

	Book gotoPage(int pageNumber){
		if (pageNumber > getPgs() ){
			System.out.println("No such page!");
			return new Book(getPgs(), getCurr());
		}
		else {
			return new Book(getPgs(), pageNumber);
		}
	}


}
