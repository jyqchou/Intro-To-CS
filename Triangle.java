public class Triangle{
	public static void main(String[] args){

System.out.println("Enter a number:");	
int n = IO.readInt();

for (int j=0; j<n; j++){
	for (int i=0;i<=j;i++){
		System.out.print("*");
	}
	System.out.println();
	}

for (int j=0; j<n-1;j++){
	for (int i=n-j;i>1;i--){
		System.out.print("*");
	}
	System.out.println();
	}
	}
}