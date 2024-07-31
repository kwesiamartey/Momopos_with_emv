package com.payswitch.momopos.databinding;
import com.payswitch.momopos.R;
import com.payswitch.momopos.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityHistoryListviewBindingImpl extends ActivityHistoryListviewBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.transaction_img_success, 11);
        sViewsWithIds.put(R.id.transaction_img_failed, 12);
        sViewsWithIds.put(R.id.check_full_details, 13);
        sViewsWithIds.put(R.id.btn_check_status, 14);
        sViewsWithIds.put(R.id.printTer, 15);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityHistoryListviewBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }
    private ActivityHistoryListviewBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[14]
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[6]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.ImageView) bindings[11]
            );
        this.fname.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.transAmount.setTag(null);
        this.transDate.setTag(null);
        this.transDescription.setTag(null);
        this.transId.setTag(null);
        this.transMerchantId.setTag(null);
        this.transNumber.setTag(null);
        this.transOperator.setTag(null);
        this.transStatus.setTag(null);
        this.transTime.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.views == variableId) {
            setViews((android.view.View) variable);
        }
        else if (BR.s_history == variableId) {
            setSHistory((com.payswitch.momopos.room.entity.TransactionEntity) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViews(@Nullable android.view.View Views) {
        this.mViews = Views;
    }
    public void setSHistory(@Nullable com.payswitch.momopos.room.entity.TransactionEntity SHistory) {
        this.mSHistory = SHistory;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.s_history);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String sHistoryRSwitch = null;
        java.lang.String sHistoryMerchantId = null;
        java.lang.String javaLangStringDateSHistoryDate = null;
        java.lang.String javaLangStringEntitySHistoryFName = null;
        java.lang.String sHistoryDate = null;
        java.lang.String javaLangStringAmountSHistoryAmount = null;
        java.lang.String javaLangStringMerchantIdSHistoryMerchantId = null;
        java.lang.String sHistorySubscriberNumber = null;
        com.payswitch.momopos.room.entity.TransactionEntity sHistory = mSHistory;
        java.lang.String javaLangStringTimeSHistoryTime = null;
        java.lang.String sHistoryStatus = null;
        java.lang.String javaLangStringDescriptionSHistoryDesc = null;
        java.lang.String sHistoryTime = null;
        java.lang.String sHistoryFName = null;
        java.lang.String sHistoryDesc = null;
        java.lang.String javaLangStringTransactionIdSHistoryTransactionId = null;
        java.lang.String sHistoryAmount = null;
        java.lang.String sHistoryTransactionId = null;
        java.lang.String javaLangStringStatusSHistoryStatus = null;
        java.lang.String javaLangStringOperatorSHistoryRSwitch = null;
        java.lang.String javaLangStringFoneNumberSHistorySubscriberNumber = null;

        if ((dirtyFlags & 0x6L) != 0) {



                if (sHistory != null) {
                    // read s_history.r_switch
                    sHistoryRSwitch = sHistory.getR_switch();
                    // read s_history.merchant_id
                    sHistoryMerchantId = sHistory.getMerchant_id();
                    // read s_history.date
                    sHistoryDate = sHistory.getDate();
                    // read s_history.subscriber_number
                    sHistorySubscriberNumber = sHistory.getSubscriber_number();
                    // read s_history.Status
                    sHistoryStatus = sHistory.getStatus();
                    // read s_history.time
                    sHistoryTime = sHistory.getTime();
                    // read s_history.fName
                    sHistoryFName = sHistory.getFName();
                    // read s_history.desc
                    sHistoryDesc = sHistory.getDesc();
                    // read s_history.amount
                    sHistoryAmount = sHistory.getAmount();
                    // read s_history.transaction_id
                    sHistoryTransactionId = sHistory.getTransaction_id();
                }


                // read ("Operator: ") + (s_history.r_switch)
                javaLangStringOperatorSHistoryRSwitch = ("Operator: ") + (sHistoryRSwitch);
                // read ("Merchant id: ") + (s_history.merchant_id)
                javaLangStringMerchantIdSHistoryMerchantId = ("Merchant id: ") + (sHistoryMerchantId);
                // read ("Date: ") + (s_history.date)
                javaLangStringDateSHistoryDate = ("Date: ") + (sHistoryDate);
                // read ("Fone number: ") + (s_history.subscriber_number)
                javaLangStringFoneNumberSHistorySubscriberNumber = ("Fone number: ") + (sHistorySubscriberNumber);
                // read ("Status: ") + (s_history.Status)
                javaLangStringStatusSHistoryStatus = ("Status: ") + (sHistoryStatus);
                // read ("Time: ") + (s_history.time)
                javaLangStringTimeSHistoryTime = ("Time: ") + (sHistoryTime);
                // read ("Entity: ") + (s_history.fName)
                javaLangStringEntitySHistoryFName = ("Entity: ") + (sHistoryFName);
                // read ("Description: ") + (s_history.desc)
                javaLangStringDescriptionSHistoryDesc = ("Description: ") + (sHistoryDesc);
                // read ("Amount: ") + (s_history.amount)
                javaLangStringAmountSHistoryAmount = ("Amount: ") + (sHistoryAmount);
                // read ("Transaction id: ") + (s_history.transaction_id)
                javaLangStringTransactionIdSHistoryTransactionId = ("Transaction id: ") + (sHistoryTransactionId);
        }
        // batch finished
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.fname, javaLangStringEntitySHistoryFName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.transAmount, javaLangStringAmountSHistoryAmount);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.transDate, javaLangStringDateSHistoryDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.transDescription, javaLangStringDescriptionSHistoryDesc);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.transId, javaLangStringTransactionIdSHistoryTransactionId);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.transMerchantId, javaLangStringMerchantIdSHistoryMerchantId);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.transNumber, javaLangStringFoneNumberSHistorySubscriberNumber);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.transOperator, javaLangStringOperatorSHistoryRSwitch);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.transStatus, javaLangStringStatusSHistoryStatus);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.transTime, javaLangStringTimeSHistoryTime);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): views
        flag 1 (0x2L): s_history
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}