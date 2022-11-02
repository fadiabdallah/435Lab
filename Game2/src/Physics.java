import java.util.LinkedList;

public class Physics {
	
	public static boolean Collisions(EntityA enta, LinkedList<EntityB> entb) {
		//collision
		//if A class runs into B class then true then collision occurred 
		for(int i=0; i < entb.size(); i++) {
			if(enta.getBounds().intersects(entb.get(i).getBounds())) {
				return true;
			}
		}
		return false;
	}
	

}
