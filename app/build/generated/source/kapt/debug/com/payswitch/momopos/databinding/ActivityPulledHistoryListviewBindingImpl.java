package com.payswitch.momopos.databinding;
import com.payswitch.momopos.R;
import com.payswitch.momopos.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityPulledHistoryListviewBindingImpl extends ActivityPulledHistoryListviewBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.pulled_transaction_img_success, 9);
        sViewsWithIds.put(R.id.pulled_transaction_img_failed, 10);
        sViewsWithIds.put(R.id.check_full_details, 11);
        sViewsWithIds.put(R.id.response_status, 12);
        sViewsWithIds.put(R.id.btn_check_status, 13);
        sViewsWithIds.put(R.id.printTer, 14);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityPulledHistoryListviewBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private ActivityPulledHistoryListviewBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[2]
            , (android.widget.Button) bindings[13]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[14]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[1]
            );
        this.amount.setTag(null);
        this.creationDate.setTag(null);
        this.desc.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rSwitch.setTag(null);
        this.responseReason.setTag(null);
        this.responseTransactionId.setTag(null);
        this.subcriberNumber.setTag(null);
        this.transStatus.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.pullDataTransactionBinding == variableId) {
            setPullDataTransactionBinding((com.payswitch.momopos.room.entity.PulledTransactionEntity) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPullDataTransactionBinding(@Nullable com.payswitch.momopos.room.entity.PulledTransactionEntity PullDataTransactionBinding) {
        this.mPullDataTransactionBinding = PullDataTransactionBinding;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.pullDataTransactionBinding);
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
        com.payswitch.momopos.room.entity.PulledTransactionEntity pullDataTransactionBinding = mPullDataTransactionBinding;
        java.lang.String javaLangStringTransactionIDPullDataTransactionBindingTransactionId = null;
        java.lang.String javaLangStringDescriptionPullDataTransactionBindingDescription = null;
        java.lang.String pullDataTransactionBindingSubscriberNumber = null;
        java.lang.String pullDataTransactionBindingRSwitch = null;
        java.lang.String pullDataTransactionBindingResponseReason = null;
        java.lang.String pullDataTransactionBindingResponseStatus = null;
        java.lang.String pullDataTransactionBindingDescription = null;
        java.lang.String javaLangStringFoneNumberPullDataTransactionBindingSubscriberNumber = null;
        java.lang.String javaLangStringDateCreatedPullDataTransactionBindingCreationDate = null;
        java.lang.String javaLangStringRSwitchPullDataTransactionBindingRSwitch = null;
        java.lang.String pullDataTransactionBindingCreationDate = null;
        java.lang.String javaLangStringResponseTransactionIdPullDataTransactionBindingResponseTransactionId = null;
        java.lang.String pullDataTransactionBindingResponseTransactionId = null;
        java.lang.String javaLangStringResponseReasonPullDataTransactionBindingResponseReason = null;
        java.lang.String javaLangStringStatusPullDataTransactionBindingResponseStatus = null;
        java.lang.String pullDataTransactionBindingTransactionId = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (pullDataTransactionBinding != null) {
                    // read pullDataTransactionBinding.subscriber_number
                    pullDataTransactionBindingSubscriberNumber = pullDataTransactionBinding.getSubscriber_number();
                    // read pullDataTransactionBinding.r_switch
                    pullDataTransactionBindingRSwitch = pullDataTransactionBinding.getR_switch();
                    // read pullDataTransactionBinding.response_reason
                    pullDataTransactionBindingResponseReason = pullDataTransactionBinding.getResponse_reason();
                    // read pullDataTransactionBinding.response_status
                    pullDataTransactionBindingResponseStatus = pullDataTransactionBinding.getResponse_status();
                    // read pullDataTransactionBinding.description
                    pullDataTransactionBindingDescription = pullDataTransactionBinding.getDescription();
                    // read pullDataTransactionBinding.creation_date
                    pullDataTransactionBindingCreationDate = pullDataTransactionBinding.getCreation_date();
                    // read pullDataTransactionBinding.response_transaction_id
                    pullDataTransactionBindingResponseTransactionId = pullDataTransactionBinding.getResponse_transaction_id();
                    // read pullDataTransactionBinding.transaction_id
                    pullDataTransactionBindingTransactionId = pullDataTransactionBinding.getTransaction_id();
                }


                // read ("Fone Number: ") + (pullDataTransactionBinding.subscriber_number)
                javaLangStringFoneNumberPullDataTransactionBindingSubscriberNumber = ("Fone Number: ") + (pullDataTransactionBindingSubscriberNumber);
                // read ("R-Switch: ") + (pullDataTransactionBinding.r_switch)
                javaLangStringRSwitchPullDataTransactionBindingRSwitch = ("R-Switch: ") + (pullDataTransactionBindingRSwitch);
                // read ("Response Reason: ") + (pullDataTransactionBinding.response_reason)
                javaLangStringResponseReasonPullDataTransactionBindingResponseReason = ("Response Reason: ") + (pullDataTransactionBindingResponseReason);
                // read ("Status: ") + (pullDataTransactionBinding.response_status)
                javaLangStringStatusPullDataTransactionBindingResponseStatus = ("Status: ") + (pullDataTransactionBindingResponseStatus);
                // read ("Description: ") + (pullDataTransactionBinding.description)
                javaLangStringDescriptionPullDataTransactionBindingDescription = ("Description: ") + (pullDataTransactionBindingDescription);
                // read ("Date created: ") + (pullDataTransactionBinding.creation_date)
                javaLangStringDateCreatedPullDataTransactionBindingCreationDate = ("Date created: ") + (pullDataTransactionBindingCreationDate);
                // read ("Response Transaction id: ") + (pullDataTransactionBinding.response_transaction_id)
                javaLangStringResponseTransactionIdPullDataTransactionBindingResponseTransactionId = ("Response Transaction id: ") + (pullDataTransactionBindingResponseTransactionId);
                // read ("Transaction ID: ") + (pullDataTransactionBinding.transaction_id)
                javaLangStringTransactionIDPullDataTransactionBindingTransactionId = ("Transaction ID: ") + (pullDataTransactionBindingTransactionId);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.amount.setHint(javaLangStringTransactionIDPullDataTransactionBindingTransactionId);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.creationDate, javaLangStringDateCreatedPullDataTransactionBindingCreationDate);
            this.desc.setHint(javaLangStringDescriptionPullDataTransactionBindingDescription);
            this.rSwitch.setHint(javaLangStringRSwitchPullDataTransactionBindingRSwitch);
            this.responseReason.setHint(javaLangStringResponseReasonPullDataTransactionBindingResponseReason);
            this.responseTransactionId.setHint(javaLangStringResponseTransactionIdPullDataTransactionBindingResponseTransactionId);
            this.subcriberNumber.setHint(javaLangStringFoneNumberPullDataTransactionBindingSubscriberNumber);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.transStatus, javaLangStringStatusPullDataTransactionBindingResponseStatus);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): pullDataTransactionBinding
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}