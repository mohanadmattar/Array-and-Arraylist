/**
 * كلاس العميل (Customer)
 * بناءً على ما ورد في السلايد رقم 27 و 29 من مادة برمجة الكائنات (PBO).
 * يمثل عميلاً في البنك يمتلك اسماً ومجموعة من الحسابات البنكية مخزنة في مصفوفة (Array).
 */
public class Customer {
    // الاسم الأول للعميل
    private String firstName;
    // اسم العائلة للعميل
    private String lastName;
    // مصفوفة لتخزين حسابات العميل (سعة ثابتة بحد أقصى 5 حسابات حسب السلايد 29)
    private Account[] accounts = new Account[5];
    // متغير لتتبع عدد الحسابات المضافة وموقع المؤشر التالي في المصفوفة
    private int numberOfAccounts = 0;

    /**
     * باني الكائن (Constructor)
     * يقوم بتهيئة الاسم الأول واسم العائلة للعميل
     *
     * @param f الاسم الأول
     * @param l اسم العائلة
     */
    public Customer(String f, String l) {
        this.firstName = f;
        this.lastName = l;
    }

    /**
     * دالة استرجاع الاسم الأول للعميل
     *
     * @return الاسم الأول
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * دالة استرجاع اسم العائلة للعميل
     *
     * @return اسم العائلة
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * دالة إضافة/تعيين حساب للعميل (setAccount)
     * بناءً على السلايد 29: تتحقق من عدم تجاوز الحد الأقصى للمصفوفة (5 حسابات)
     *
     * @param acct كائن الحساب البنكي المراد إضافته
     */
    public void setAccount(Account acct) {
        // التأكد من أن عدد الحسابات لم يتجاوز السعة القصوى للمصفوفة (5)
        if (numberOfAccounts < 5) {
            accounts[numberOfAccounts++] = acct;
        } else {
            System.out.println("تنبيه: لا يمكن إضافة المزيد من الحسابات، تم بلوغ الحد الأقصى (5 حسابات).");
        }
    }

    /**
     * دالة مساعدة (alias) لإضافة حساب بنفس وظيفة setAccount
     *
     * @param acct كائن الحساب البنكي
     */
    public void addAccount(Account acct) {
        setAccount(acct);
    }

    /**
     * دالة استرجاع حساب محدد بناءً على رقم الفهرس (Index)
     * بناءً على السلايد 29
     *
     * @param account_index فهرس الحساب في المصفوفة (يبدأ من 0)
     * @return كائن الحساب Account، أو null إذا كان الفهرس خارج الحدود
     */
    public Account getAccount(int account_index) {
        // فحص الحدود لتجنب خطأ ArrayIndexOutOfBoundsException الموضح في السلايد 10
        if (account_index >= 0 && account_index < numberOfAccounts) {
            return accounts[account_index];
        }
        return null;
    }

    /**
     * دالة استرجاع الحساب الأساسي (أول حساب تم إنشاؤه عند الفهرس 0)
     * تطابق المخطط في السلايد 26 و 27 (+getAccount() : Account)
     *
     * @return الحساب الأول أو null إن لم يكن لديه حسابات
     */
    public Account getAccount() {
        return getAccount(0);
    }

    /**
     * دالة استرجاع إجمالي عدد الحسابات المسجلة للعميل
     *
     * @return عدد الحسابات الفعلي
     */
    public int getNumOfAccounts() {
        return this.numberOfAccounts;
    }
}
