import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * كلاس التجربة والتطبيق الرئيسي (BankDemo)
 * يغطي متطلبات الاستكشاف الواردة في السلايدات (الصفحات 26 - 31)
 * من مادة برمجة الكائنات (PBO 4: Array & ArrayList).
 * 
 * يتيح البرنامج:
 * 1. استكشاف كائنات البنك والعملاء والحسابات وإدارتها عبر المصفوفات (Arrays).
 * 2. استكشاف دوال فئة المرافق java.util.Arrays.
 * 3. استكشاف القوائم الديناميكية ArrayList ومقارنتها مع المصفوفات العادية.
 * 4. تشغيل قائمة ATM تفاعلية باستخدام Scanner لمحاكاة العمليات البنكية.
 */
public class BankDemo {

    public static void main(String[] args) {
        // التحقق مما إذا تم تشغيل البرنامج بوضع الاختبار التلقائي
        if (args.length > 0 && (args[0].equalsIgnoreCase("--auto") || args[0].equalsIgnoreCase("--test"))) {
            runAutomatedExploration();
            return;
        }

        // إنشاء كائن البنك بسعة 10 عملاء وتعبئته ببيانات تجريبية أولية
        Bank myBank = new Bank(10);
        initializeSampleData(myBank);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=================================================");
        System.out.println("   BANK & ARRAY/ARRAYLIST EXPLORATION SYSTEM     ");
        System.out.println("       PBO 4 - Universitas Mataram (UNRAM)       ");
        System.out.println("=================================================");

        // حلقة القائمة الرئيسية لتشغيل نظام الصراف الآلي / البنك
        while (running) {
            printMenu();
            System.out.print("Select an option [1-8]: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayAllCustomers(myBank);
                    break;
                case "2":
                    addNewCustomer(myBank, scanner);
                    break;
                case "3":
                    addAccountToCustomer(myBank, scanner);
                    break;
                case "4":
                    performDeposit(myBank, scanner);
                    break;
                case "5":
                    performWithdraw(myBank, scanner);
                    break;
                case "6":
                    checkBalance(myBank, scanner);
                    break;
                case "7":
                    runAutomatedExploration();
                    break;
                case "8":
                    System.out.println("\n[INFO] Thank you for using the Banking System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println(">> [ERROR] Invalid selection! Please enter a number between 1 and 8.");
            }
            System.out.println();
        }

        scanner.close();
    }

    /**
     * طباعة القائمة التفاعلية للمستخدم (ATM / Bank Menu)
     */
    private static void printMenu() {
        System.out.println("\n------------- [ ATM / BANK MENU ] -------------");
        System.out.println(" 1. Display All Customers & Accounts");
        System.out.println(" 2. Add New Customer");
        System.out.println(" 3. Add Bank Account to Customer");
        System.out.println(" 4. Deposit Money");
        System.out.println(" 5. Withdraw Money");
        System.out.println(" 6. Check Customer Account Balance");
        System.out.println(" 7. Run Array vs ArrayList Exploration (Slides 11, 23, 26-31)");
        System.out.println(" 8. Exit");
        System.out.println("-------------------------------------------------");
    }

    /**
     * تهيئة بيانات أولية داخل البنك لاختبار عمليات الإضافة واسترجاع المصفوفات
     * 
     * @param bank كائن البنك
     */
    private static void initializeSampleData(Bank bank) {
        // إضافة 4 عملاء في مصفوفة العملاء (Customer[])
        bank.addCustomer("Jane", "Simms");
        bank.addCustomer("Owen", "Bryant");
        bank.addCustomer("Tim", "Soleh");
        bank.addCustomer("Maria", "Anders");

        // إضافة حسابات بنكية بمبالغ أولية لكل عميل
        bank.getCustomer(0).setAccount(new Account(500.00));
        bank.getCustomer(0).setAccount(new Account(150.00)); // حساب ثانٍ للعميل الأول

        bank.getCustomer(1).setAccount(new Account(200.00));
        bank.getCustomer(2).setAccount(new Account(1500.50));
        bank.getCustomer(3).setAccount(new Account(350.75));
    }

    /**
     * عرض جميع العملاء المخزنين داخل مصفوفة البنك وتفاصيل حساباتهم
     * 
     * @param bank كائن البنك
     */
    private static void displayAllCustomers(Bank bank) {
        int totalCustomers = bank.getNumOfCustomers();
        System.out.println("\n====== Bank Customers List (Total: " + totalCustomers + ") ======");
        if (totalCustomers == 0) {
            System.out.println("No customers registered yet.");
            return;
        }

        // التكرار عبر مصفوفة العملاء باستخدام الفهرس
        for (int i = 0; i < totalCustomers; i++) {
            Customer c = bank.getCustomer(i);
            System.out.println("Customer [" + (i + 1) + "]: " + c.getFirstName() + " " + c.getLastName());
            int accCount = c.getNumOfAccounts();
            System.out.println("   Number of Accounts: " + accCount);
            // التكرار عبر مصفوفة حسابات العميل
            for (int j = 0; j < accCount; j++) {
                Account acc = c.getAccount(j);
                System.out.printf(Locale.US, "   - Account [%d]: Balance = $%.2f%n", (j + 1), acc.getBalance());
            }
        }
    }

    /**
     * إضافة عميل جديد إلى مصفوفة البنك
     */
    private static void addNewCustomer(Bank bank, Scanner scanner) {
        System.out.print("Enter Customer First Name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Enter Customer Last Name: ");
        String lastName = scanner.nextLine().trim();

        // التحقق من صحة المدخلات
        if (firstName.isEmpty() || lastName.isEmpty()) {
            System.out.println(">> [ERROR] Customer names cannot be empty!");
            return;
        }

        // استدعاء دالة addCustomer لإضافة العميل في المصفوفة
        bank.addCustomer(firstName, lastName);
        System.out.println(">> [SUCCESS] Customer added! Total customers: " + bank.getNumOfCustomers());
    }

    /**
     * إضافة حساب بنكي جديد لعميل محدد (بحد أقصى 5 حسابات حسب السلايد 29)
     */
    private static void addAccountToCustomer(Bank bank, Scanner scanner) {
        displayAllCustomers(bank);
        System.out.print("\nSelect Customer Number to add account [1-" + bank.getNumOfCustomers() + "]: ");
        int customerIndex = parseIndex(scanner.nextLine()) - 1;

        Customer customer = bank.getCustomer(customerIndex);
        if (customer == null) {
            System.out.println(">> [ERROR] Customer not found!");
            return;
        }

        // التأكد من عدم تجاوز سعة المصفوفة
        if (customer.getNumOfAccounts() >= 5) {
            System.out.println(">> [WARNING] This customer has reached the maximum of 5 accounts!");
            return;
        }

        System.out.print("Enter Initial Deposit for New Account: $");
        double initialBalance = parseDouble(scanner.nextLine());
        if (initialBalance < 0) {
            System.out.println(">> [ERROR] Initial balance cannot be negative!");
            return;
        }

        // إنشاء الحساب وإضافته لمصفوفة حسابات العميل
        customer.setAccount(new Account(initialBalance));
        System.out.printf(Locale.US, ">> [SUCCESS] Account created successfully with balance: $%.2f%n", initialBalance);
    }

    /**
     * تنفيذ عملية إيداع في حساب عميل
     */
    private static void performDeposit(Bank bank, Scanner scanner) {
        displayAllCustomers(bank);
        System.out.print("\nSelect Customer Number: ");
        int customerIndex = parseIndex(scanner.nextLine()) - 1;
        Customer customer = bank.getCustomer(customerIndex);

        if (customer == null) {
            System.out.println(">> [ERROR] Customer not found!");
            return;
        }

        if (customer.getNumOfAccounts() == 0) {
            System.out.println(">> [ERROR] Customer has no bank accounts!");
            return;
        }

        System.out.print("Select Account Number [1-" + customer.getNumOfAccounts() + "]: ");
        int accIndex = parseIndex(scanner.nextLine()) - 1;
        Account account = customer.getAccount(accIndex);

        if (account == null) {
            System.out.println(">> [ERROR] Invalid account number!");
            return;
        }

        System.out.print("Enter Deposit Amount: $");
        double amount = parseDouble(scanner.nextLine());

        // استدعاء دالة الإيداع deposit
        boolean success = account.deposit(amount);
        if (success) {
            System.out.printf(Locale.US, ">> [SUCCESS] Deposit successful! New balance: $%.2f%n", account.getBalance());
        } else {
            System.out.println(">> [ERROR] Deposit failed! Amount must be greater than 0.");
        }
    }

    /**
     * تنفيذ عملية سحب من حساب عميل
     */
    private static void performWithdraw(Bank bank, Scanner scanner) {
        displayAllCustomers(bank);
        System.out.print("\nSelect Customer Number: ");
        int customerIndex = parseIndex(scanner.nextLine()) - 1;
        Customer customer = bank.getCustomer(customerIndex);

        if (customer == null) {
            System.out.println(">> [ERROR] Customer not found!");
            return;
        }

        if (customer.getNumOfAccounts() == 0) {
            System.out.println(">> [ERROR] Customer has no bank accounts!");
            return;
        }

        System.out.print("Select Account Number [1-" + customer.getNumOfAccounts() + "]: ");
        int accIndex = parseIndex(scanner.nextLine()) - 1;
        Account account = customer.getAccount(accIndex);

        if (account == null) {
            System.out.println(">> [ERROR] Invalid account number!");
            return;
        }

        System.out.printf(Locale.US, "Current Balance: $%.2f%n", account.getBalance());
        System.out.print("Enter Withdrawal Amount: $");
        double amount = parseDouble(scanner.nextLine());

        // استدعاء دالة السحب withdraw
        boolean success = account.withdraw(amount);
        if (success) {
            System.out.printf(Locale.US, ">> [SUCCESS] Withdrawal successful! New balance: $%.2f%n", account.getBalance());
        } else {
            System.out.println(">> [ERROR] Withdrawal failed! Insufficient funds or invalid amount.");
        }
    }

    /**
     * الاستعلام عن أرصدة جميع حسابات عميل محدد
     */
    private static void checkBalance(Bank bank, Scanner scanner) {
        displayAllCustomers(bank);
        System.out.print("\nSelect Customer Number: ");
        int customerIndex = parseIndex(scanner.nextLine()) - 1;
        Customer customer = bank.getCustomer(customerIndex);

        if (customer == null) {
            System.out.println(">> [ERROR] Customer not found!");
            return;
        }

        if (customer.getNumOfAccounts() == 0) {
            System.out.println(">> Customer currently has no accounts.");
            return;
        }

        System.out.println("\nAccounts for " + customer.getFirstName() + " " + customer.getLastName() + ":");
        for (int i = 0; i < customer.getNumOfAccounts(); i++) {
            System.out.printf(Locale.US, " - Account [%d]: $%.2f%n", (i + 1), customer.getAccount(i).getBalance());
        }
    }

    /**
     * استكشاف تفصيلي لمفاهيم السلايدات (Array و ArrayList)
     * يتضمن اختبارات برمجية مباشرة على كل مفهوم ورد في المحاضرة.
     */
    public static void runAutomatedExploration() {
        System.out.println("\n=======================================================");
        System.out.println("   AUTOMATED EXPLORATION: ARRAY & ARRAYLIST (PBO 4)   ");
        System.out.println("=======================================================");

        // [1] استكشاف مصفوفات الكائنات العادية (Array of Objects) من السلايدات 26-31
        System.out.println("\n--- [PART 1: Bank & Customer Objects Array (Slides 26-31)] ---");
        Bank testBank = new Bank(5);
        testBank.addCustomer("Jane", "Simms");
        testBank.addCustomer("Owen", "Bryant");
        testBank.addCustomer("Tim", "Soleh");

        System.out.println("Initial Customer Count (bank.getNumOfCustomers()): " + testBank.getNumOfCustomers());
        for (int i = 0; i < testBank.getNumOfCustomers(); i++) {
            Customer cust = testBank.getCustomer(i);
            System.out.println("  Index " + i + ": " + cust.getFirstName() + " " + cust.getLastName());
        }

        // اختبار عمليات الحساب البنكي (Account)
        Customer c1 = testBank.getCustomer(0);
        c1.setAccount(new Account(500.00));
        Account acc = c1.getAccount(0);
        System.out.println("\nTesting Account operations for " + c1.getFirstName() + ":");
        System.out.printf(Locale.US, "  Initial Balance: $%.2f%n", acc.getBalance());

        // تجربة الإيداع
        acc.deposit(150.00);
        System.out.printf(Locale.US, "  After deposit $150.00: $%.2f%n", acc.getBalance());

        // تجربة سحب ناجح
        boolean ok1 = acc.withdraw(200.00);
        System.out.printf(Locale.US, "  Withdraw $200.00 [Result: %b] -> Balance: $%.2f%n", ok1, acc.getBalance());

        // تجربة سحب مبلغ يتجاوز الرصيد (Insufficient Funds)
        boolean ok2 = acc.withdraw(1000.00);
        System.out.printf(Locale.US, "  Withdraw $1000.00 [Result: %b] -> Balance: $%.2f%n", ok2, acc.getBalance());

        // [2] تجربة فئة المرافق java.util.Arrays (السلايد 11)
        System.out.println("\n--- [PART 2: java.util.Arrays Utility Methods (Slide 11)] ---");
        int[] numbers = {5, 2, 8, 1, 9};
        System.out.println("Original array: " + Arrays.toString(numbers));
        Arrays.sort(numbers);
        System.out.println("After Arrays.sort(): " + Arrays.toString(numbers));
        int[] copy = Arrays.copyOf(numbers, 7);
        System.out.println("After Arrays.copyOf(numbers, 7): " + Arrays.toString(copy));

        // [3] استكشاف ArrayList الديناميكية (السلايدات 16 إلى 25)
        System.out.println("\n--- [PART 3: Dynamic ArrayList Exploration (Slides 16-25)] ---");
        ArrayList<String> names = new ArrayList<>();
        // إضافة عناصر بالترتيب (add)
        names.add("Emily");
        names.add("Bob");
        names.add("Cindy");
        System.out.println("ArrayList after additions: " + names);
        System.out.println("ArrayList size(): " + names.size());

        // إدراج عنصر في موقع محدد add(index, element) (سلايد 21)
        names.add(1, "Ann");
        System.out.println("After inserting 'Ann' at index 1: " + names);

        // تعديل عنصر باستخدام set(index, element) (سلايد 20 و 21)
        names.set(3, "Carolyn");
        System.out.println("After replacing element at index 3 with 'Carolyn': " + names);

        // حذف عنصر باستخدام remove(index) (سلايد 20 و 21)
        names.remove(0);
        System.out.println("After removing element at index 0: " + names);
        System.out.println("First element (get(0)): " + names.get(0));
        System.out.println("Last element (get(size() - 1)): " + names.get(names.size() - 1));

        // [4] جدول مقارنة بين المصفوفة الثابتة و ArrayList (السلايد 23)
        System.out.println("\n--- [PART 4: Array vs ArrayList Summary (Slide 23)] ---");
        System.out.println("+----------------------+-------------------------+-------------------------+");
        System.out.println("| Aspect               | Array int[] / Object[]  | ArrayList<T>            |");
        System.out.println("+----------------------+-------------------------+-------------------------+");
        System.out.println("| Size                 | Fixed at creation       | Grows / shrinks dynamic |");
        System.out.println("| Holds                | Primitives OR Objects   | Objects only (Wrappers) |");
        System.out.println("| Access               | a[i]                    | get(i) / set(i, x)      |");
        System.out.println("| Size Query           | a.length (field)        | size() (method)         |");
        System.out.println("| Add / Remove         | Manual resize/copy      | add(), remove() builtin |");
        System.out.println("| Best when            | Fixed size, primitives  | Dynamic size, objects   |");
        System.out.println("+----------------------+-------------------------+-------------------------+");
    }

    /**
     * تحويل النص إلى رقم صحيح بأمان لمنع انهيار البرنامج عند الإدخال الخاطئ
     */
    private static int parseIndex(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * تحويل النص إلى رقم عشري بأمان
     */
    private static double parseDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
