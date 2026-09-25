/**
 * كلاس البنك (Bank)
 * بناءً على ما ورد في السلايد رقم 30 و 31 من مادة برمجة الكائنات (PBO).
 * يقوم بإدارة مصفوفة من كائنات العملاء (Customer[]) مع تتبع عددهم.
 */
public class Bank {
    // مصفوفة لتخزين كائنات العملاء (Customer Objects)
    private Customer[] customers;
    // متغير لتتبع عدد العملاء الفعلي وموقع الفهرس القادم في المصفوفة
    private int numberOfCustomers;

    /**
     * باني الكائن الافتراضي (Constructor)
     * يقوم بتهيئة مصفوفة العملاء بحجم مناسب أكبر من 5 (تم اختيار 10 عملاء)
     * بناءً على متطلبات السلايد 31: (at least bigger than 5)
     */
    public Bank() {
        this(10); // استدعاء الباني المخصص بسعة 10 افتراضياً
    }

    /**
     * باني كائن مخصص يسمح بتحديد السعة القصوى المبدئية للعملاء
     *
     * @param maxCustomers السعة القصوى لمصفوفة العملاء (تشترط أن تكون أكبر من 5)
     */
    public Bank(int maxCustomers) {
        if (maxCustomers <= 5) {
            maxCustomers = 10; // ضمان أن تكون السعة أكبر من 5
        }
        this.customers = new Customer[maxCustomers];
        this.numberOfCustomers = 0;
    }

    /**
     * دالة إضافة عميل جديد للبنك (addCustomer)
     * تقوم بإنشاء كائن Customer جديد وتخزينه في المصفوفة ثم زيادة العداد
     *
     * @param f الاسم الأول للعميل
     * @param l اسم العائلة للعميل
     */
    public void addCustomer(String f, String l) {
        // التحقق مما إذا كانت المصفوفة ممتلئة لتجنب تجاوز الحجم
        if (numberOfCustomers >= customers.length) {
            // إعادة تحجيم المصفوفة بنسخها لمصفوفة أكبر بمقدار الضعف
            Customer[] newCustomers = new Customer[customers.length * 2];
            for (int i = 0; i < customers.length; i++) {
                newCustomers[i] = customers[i];
            }
            customers = newCustomers;
        }

        // إنشاء كائن العميل ووضعه في المصفوفة عند المؤشر الحالي ثم زيادة المؤشر
        Customer newCustomer = new Customer(f, l);
        customers[numberOfCustomers] = newCustomer;
        numberOfCustomers++;
    }

    /**
     * دالة استرجاع عدد العملاء الحاليين في البنك
     *
     * @return عدد العملاء الفعلي المسجلين
     */
    public int getNumOfCustomers() {
        return this.numberOfCustomers;
    }

    /**
     * دالة استرجاع كائن العميل بناءً على الفهرس المحدد (Index)
     *
     * @param index موقع العميل في المصفوفة (من 0 إلى numberOfCustomers - 1)
     * @return كائن Customer، أو null إذا كان الفهرس غير صحيح
     */
    public Customer getCustomer(int index) {
        // فحص حدود الفهرس (Bounds Checking) لتفادي حدوث ArrayIndexOutOfBoundsException
        if (index >= 0 && index < numberOfCustomers) {
            return customers[index];
        }
        return null;
    }

    /**
     * دالة مساعدة لاسترجاع مصفوفة العملاء كاملة
     *
     * @return مصفوفة العملاء
     */
    public Customer[] getCustomers() {
        return this.customers;
    }
}
