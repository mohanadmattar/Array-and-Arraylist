/**
 * كلاس الحساب البنكي (Account)
 * بناءً على ما ورد في السلايد رقم 27 و 28 من مادة برمجة الكائنات (PBO).
 * يقوم بإدارة الرصيد والعمليات المصرفية الأساسية مثل الإيداع والسحب.
 */
public class Account {
    // الخاصية المحمية لتخزين رصيد الحساب الحالي
    // protected double balance تسمح للفئات الفرعية (إن وجدت مستقبلاً) بالوصول المباشر للرصيد
    protected double balance;

    /**
     * باني الكائن (Constructor)
     * يستقبل الرصيد الابتدائي ويقوم بتهيئة الحساب به
     *
     * @param init_balance الرصيد الأولي للحساب
     */
    public Account(double init_balance) {
        this.balance = init_balance;
    }

    /**
     * دالة استرجاع الرصيد الحالي (Getter)
     *
     * @return الرصيد الحالي للحساب
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * دالة إيداع مبلغ مالي في الحساب (deposit)
     * تشترط أن يكون المبلغ المودع أكبر من صفر
     *
     * @param amount المبلغ المراد إيداعه
     * @return true إذا تمت العملية بنجاح، و false إذا كانت القيمة غير صالحة
     */
    public boolean deposit(double amount) {
        // التحقق من أن المبلغ المراد إيداعه أكبر من صفر
        if (amount > 0) {
            this.balance = this.balance + amount;
            return true; // تمت العملية بنجاح
        } else {
            return false; // فشل الإيداع لأن المبلغ سالب أو صفر
        }
    }

    /**
     * دالة سحب مبلغ مالي من الحساب (withdraw)
     * تشترط أن يكون المبلغ المطلوب سحبه أقل من أو يساوي الرصيد المتوفر
     *
     * @param amount المبلغ المراد سحبه
     * @return true إذا تم السحب بنجاح، و false إذا كان الرصيد غير كافٍ
     */
    public boolean withdraw(double amount) {
        // التحقق من كفاية الرصيد قبل إتمام السحب
        if (amount > 0 && this.balance >= amount) {
            this.balance = this.balance - amount;
            return true; // تم السحب بنجاح
        } else {
            return false; // فشل السحب لعدم كفاية الرصيد أو إدخال قيمة غير صالحة
        }
    }
}
