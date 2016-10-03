public class MatrixOps{

	public static double[][] multiply(double[][] matrix1, double[][] matrix2){
		for (int i=0;i<matrix1.length;i++){
			if (matrix1[0].length != matrix1[i].length)
				return null;
		}
		for (int i=0;i<matrix2.length;i++){
			if (matrix2[0].length != matrix2[i].length)
				return null;
		}

		if (matrix1[0].length != matrix2.length)
			return null;

		double[][] product = new double[matrix1.length][matrix2[0].length];
		double x = 0;

		for (int i=0;i<matrix1.length;i++){
			for (int k=0;k<matrix2[0].length;k++){
				for (int t=0;t<matrix1[0].length;t++){
					x = x + matrix1[i][t]*matrix2[t][k];
				}
				product[i][k] = x;
				x = 0;
			}
		}

		return product;

	}
}
