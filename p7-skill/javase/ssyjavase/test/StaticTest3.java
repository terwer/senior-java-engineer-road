class StaticTest3{
	public static void main(String[] args){
		N n = new N();
		n.output();
	}
}

class M{
	public static void output(){
		System.out.println("M");
	}
}

class N extends M{ 
	public static void output(){
		System.out.println("N");
	}
}
