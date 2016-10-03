public class Smile{
	public static void main(String[] args){
		//draw head
		System.out.println("Enter size of smiley:");
		int size = IO.readInt();
		
		/*
		Picasso.moveDown(size);
		Picasso.moveRight(size);
		Picasso.drawCircle(size);

		//draw face
		//draw eye
		Picasso.moveUp(size/4);
		Picasso.moveLeft(size/4);
		Picasso.drawCircle(size/4);

		Picasso.moveRight(size/3);
		Picasso.drawCircle(size/4);

		//draw mouth
		Picasso.moveDown(size/3);
		Picasso.drawLineDown(size/4);
		Picasso.moveLeft(size/2);
		Picasso.drawLineRight(size/2); 
		*/

		//draw moon
		
		Picasso.moveDown(size);
		Picasso.moveRight(size);
		Picasso.drawCircle(size);

		Picasso.moveUp(size/4);
		Picasso.moveLeft(size/4);

		for(int i = 0; i < 3; i++){
			Picasso.drawCircle(size/4);
			Picasso.moveDown((size/4) + i);
			Picasso.moveRight((size/4) - i);
		}
	}
}