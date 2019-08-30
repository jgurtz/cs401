import javax.swing.JOptionPane;

public class CheckFees {
	public static void main(String[] args) {
        final double BASE_FEE = 10.00;
        double perCheckFee = 0.04;  // Default cost for 60 or more per mo.
		String input = JOptionPane.showInputDialog("Enter number of checks issued this mounth:");
		int numChecks = Integer.parseInt(input);

        // Adjust per-check fee for the smaller quantities
        switch( (int) numChecks/10 ) {
            case 0: case 1: // 0-19
                perCheckFee = 0.10;
                break;
            case 2: case 3: // 20-39
                perCheckFee = 0.08;
                break;
            case 4: case 5: // 40-59
                perCheckFee = 0.06;
                break;
            default: // 60+ fee set at var init
        }
		String totalFee = String.format("Your fees this mounth: $%.2f", (numChecks * perCheckFee + BASE_FEE));
		JOptionPane.showMessageDialog(null, totalFee);
		
		System.exit(0);
	}
}
