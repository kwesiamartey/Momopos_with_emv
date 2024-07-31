package com.payswitch.momopos;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.payswitch.momopos.databinding.ActivityHistoryListviewBindingImpl;
import com.payswitch.momopos.databinding.ActivityHomePageBindingImpl;
import com.payswitch.momopos.databinding.ActivityPulledHistoryListviewBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYHISTORYLISTVIEW = 1;

  private static final int LAYOUT_ACTIVITYHOMEPAGE = 2;

  private static final int LAYOUT_ACTIVITYPULLEDHISTORYLISTVIEW = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.payswitch.momopos.R.layout.activity_history_listview, LAYOUT_ACTIVITYHISTORYLISTVIEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.payswitch.momopos.R.layout.activity_home_page, LAYOUT_ACTIVITYHOMEPAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.payswitch.momopos.R.layout.activity_pulled_history_listview, LAYOUT_ACTIVITYPULLEDHISTORYLISTVIEW);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYHISTORYLISTVIEW: {
          if ("layout/activity_history_listview_0".equals(tag)) {
            return new ActivityHistoryListviewBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_history_listview is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYHOMEPAGE: {
          if ("layout/activity_home_page_0".equals(tag)) {
            return new ActivityHomePageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_home_page is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYPULLEDHISTORYLISTVIEW: {
          if ("layout/activity_pulled_history_listview_0".equals(tag)) {
            return new ActivityPulledHistoryListviewBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_pulled_history_listview is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(5);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "profile");
      sKeys.put(2, "pullDataTransactionBinding");
      sKeys.put(3, "s_history");
      sKeys.put(4, "views");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(3);

    static {
      sKeys.put("layout/activity_history_listview_0", com.payswitch.momopos.R.layout.activity_history_listview);
      sKeys.put("layout/activity_home_page_0", com.payswitch.momopos.R.layout.activity_home_page);
      sKeys.put("layout/activity_pulled_history_listview_0", com.payswitch.momopos.R.layout.activity_pulled_history_listview);
    }
  }
}
