package com.payswitch.momopos.databinding;
import com.payswitch.momopos.R;
import com.payswitch.momopos.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityHomePageBindingImpl extends ActivityHomePageBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.today_saleeee, 7);
        sViewsWithIds.put(R.id.trans_over_diff, 8);
        sViewsWithIds.put(R.id.services_List, 9);
        sViewsWithIds.put(R.id.top_header, 10);
        sViewsWithIds.put(R.id.todays_sales_progressbar, 11);
        sViewsWithIds.put(R.id.todays_sales, 12);
        sViewsWithIds.put(R.id.todays_sales_amt, 13);
    }
    // views
    @NonNull
    private final android.widget.TextView mboundView4;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityHomePageBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private ActivityHomePageBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.LinearLayout) bindings[6]
            , (bindings[9] != null) ? com.payswitch.momopos.databinding.ActivityPaymentSettlementTransferBinding.bind((android.view.View) bindings[9]) : null
            , (androidx.swiperefreshlayout.widget.SwipeRefreshLayout) bindings[0]
            , (bindings[7] != null) ? com.payswitch.momopos.databinding.ActivityTadaysSalesBinding.bind((android.view.View) bindings[7]) : null
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[13]
            , (android.widget.ProgressBar) bindings[11]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[5]
            , (bindings[8] != null) ? com.payswitch.momopos.databinding.ActivityTransactionOverviewBinding.bind((android.view.View) bindings[8]) : null
            , (android.widget.TextView) bindings[1]
            );
        this.appPhoneNumber.setTag(null);
        this.appUserMerchantid.setTag(null);
        this.bottomButtons.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.swpieRefresh.setTag(null);
        this.topOverview.setTag(null);
        this.userFullName.setTag(null);
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
        if (BR.profile == variableId) {
            setProfile((com.payswitch.momopos.userProfile.UserProfile) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setProfile(@Nullable com.payswitch.momopos.userProfile.UserProfile Profile) {
        this.mProfile = Profile;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.profile);
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
        java.lang.String profileUserFullName = null;
        com.payswitch.momopos.userProfile.UserProfile profile = mProfile;
        java.lang.String profileAppPhoneNumber = null;
        java.lang.String profileAppUserMerchantid = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (profile != null) {
                    // read profile.user_full_name
                    profileUserFullName = profile.getUser_full_name();
                    // read profile.app_phone_number
                    profileAppPhoneNumber = profile.getApp_phone_number();
                    // read profile.app_user_merchantid
                    profileAppUserMerchantid = profile.getApp_user_merchantid();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.appPhoneNumber, profileAppPhoneNumber);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.appUserMerchantid, profileAppUserMerchantid);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, profileAppPhoneNumber);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.userFullName, profileUserFullName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): profile
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}