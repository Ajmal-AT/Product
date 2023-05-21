package projectporduct.ajmal.com;

import java.util.Scanner;

public class finalProject {
	
    public static void main(String[] args) {
    	
        Scanner ar = new Scanner(System.in);
        
        DiscountRules arr = new DiscountRules();
        Fees fees = new Fees();
        Products pro = new Products();

        //Product catalog
//        String[] products = {"Product A", "Product B", "Product C"};
//        double[] prices = {20, 40, 50};

        

        int[] quantities = new int[3];
        boolean[] giftWraps = new boolean[3];

        for (int i = 0; i < pro.products.length; i++) {
            System.out.print("Enter the quantity of " + pro.products[i] + " : ");
            quantities[i] = ar.nextInt();

            System.out.print("Is " + pro.products[i] + " wrapped as a gift? (true/false): ");
            giftWraps[i] = ar.nextBoolean();
        }

        double subtotal = 0;
        for (int i = 0; i < pro.products.length; i++) {
            double productTotal = quantities[i] * pro.prices[i];
            subtotal += productTotal;
            System.out.println(pro.products[i] + " - Quantity : " + quantities[i] + " - Total : $ " + productTotal);
        }

        String discountName = "";
        double discountAmount = 0;

        if (subtotal > 200) {
            discountName = "flat-10-discount";
            discountAmount = arr.flat10;
        } else if (quantities[0] > 10 || quantities[1] > 10 || quantities[2] > 10) {
            discountName = "bulk-5-discount";
            discountAmount = subtotal * arr.bulk5;
        } else if (subtotal > 20) {
            discountName = "bulk-10-discount";
            discountAmount = subtotal * arr.bulk10;
        } else if (subtotal > 30 && (quantities[0] > 15 || quantities[1] > 15 || quantities[2] > 15)) {
            discountName = "tiered_50_discount";
            double discountableQuantity = Math.max(Math.max(quantities[0], quantities[1]), quantities[2]) - 15;
            discountAmount = discountableQuantity * pro.prices[0] * arr.tiered50;
        }

        int totalUnits = quantities[0] + quantities[1] + quantities[2];
        int numPackages = (int) Math.ceil((double) totalUnits / 10);
        double totalShippingFee = numPackages * fees.shippingFee;

        double totalGiftWrapFee = 0;
        for (int i = 0; i < pro.products.length; i++) {
            if (giftWraps[i]) {
                totalGiftWrapFee += quantities[i] * fees.giftWrapFee;
            }
        }

        double total = subtotal - discountAmount + totalShippingFee + totalGiftWrapFee;

        System.out.println("Subtotal : $ " + subtotal);
        System.out.println("Discount applied : " + discountName + " - Amount : $ " + discountAmount);
        System.out.println("GiftWrap fee : $ " + totalGiftWrapFee);
        System.out.println("Shipping fee : $ " + totalShippingFee);
        System.out.println("Total Amount : $ " +total); 
    }
}