package com.pluralsight.utilities;

import java.util.Scanner;

public class DealershipMenu {
    public static int userMenu() {
        Scanner read = new Scanner(System.in);

        System.out.println("\n☁️☁️☁️ === 🚗 Car Dealership Menu 🚗 === ☁️☁️☁️");
        System.out.println("1️⃣  Search by Price 💰");
        System.out.println("2️⃣  Search by Make & Model 🏷️🚙");
        System.out.println("3️⃣  Search by Year 📅");
        System.out.println("4️⃣  Search by Color 🎨");
        System.out.println("5️⃣  Search by Mileage ⏱️");
        System.out.println("6️⃣  Search by Vehicle Type 🚐");
        System.out.println("7️⃣  View All Vehicles 🚘");
        System.out.println("8️⃣  Add a Vehicle ➕🚗");
        System.out.println("9️⃣  Remove a Vehicle ➖🚗");
        System.out.println("🔟  Sell a Vehicle 💵🚗");
        System.out.println("⓫  Lease a Vehicle 📄🔑");
        System.out.println("0️⃣  Exit ❌");
        System.out.print("Please select an option: \n");


        int choice = read.nextInt();
        read.nextLine();
        return choice;
    }
}
