import java.util.LinkedList;

public class Physics {
	
	public static boolean Collision(EntityA enta, EntityB entb) {
		//collision
		//if A class runs into B class then true then collision occurred 
		
		if(enta.getBounds().intersects(entb.getBounds())) {
			return true;
		}
		
		return false;
	}
	
	
	public static boolean Collision(EntityB entb, EntityA enta) {
		//collision
		//if A class runs into B class then true then collision occurred 
	
		if(entb.getBounds().intersects(enta.getBounds())) {
			return true;
			
		}
		return false;
	}
	

}
