
public class CustomVector implements Vector {
	Integer[] examplevector;
	Integer size;
	
	CustomVector(int a) {
		examplevector = new Integer[a];
		size = a;
	}
	
	@Override
	public int getValue(int idx) {
		return examplevector[idx];
	}

	@Override
	public int getSize() {
		return size;
	}

}
