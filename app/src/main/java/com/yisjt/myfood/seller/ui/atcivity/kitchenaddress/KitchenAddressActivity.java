package com.yisjt.myfood.seller.ui.atcivity.kitchenaddress;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.yisjt.myfood.seller.R;
import com.yisjt.myfood.seller.base.BaseActivity;
import com.yisjt.myfood.seller.utils.view.TitleFirstBar;

import java.util.List;

public class KitchenAddressActivity extends BaseActivity implements IKitchenAddressView {
    //百度地图可是控件
    private MapView mMapView;
    //定位执行的主类
    public LocationClient mLocationClient;
    //定位成功后执行的监听事件
    public MyLocationListenner myListener;
    //定位时返回的数据类型
    private BDLocation lastLocation;
    //可视化控件的管理器
    private BaiduMap mBaiduMap;

    private TitleFirstBar titleFirstBar;
    private IKitchenAddressPresenterImpl kitchenAddressPresenter;
    private RecyclerView rlv_address;
    private KitchenAddressAdaper addressAdaper;

    // private SuggestionSearch mSuggestionSearch;
    // private AutoCompleteTextView edittext_search;
    // private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_kitchen_address);
        initView(savedInstanceState);
        initBLL(savedInstanceState);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        // mSuggestionSearch = SuggestionSearch.newInstance();
        titleFirstBar = new TitleFirstBar(this);
        myListener = new MyLocationListenner();
        kitchenAddressPresenter = new IKitchenAddressPresenterImpl(this);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        rlv_address = (RecyclerView) findViewById(R.id.rlv_address);
        //获取管理器
        mBaiduMap = mMapView.getMap();
        titleFirstBar.title_txt_center.setText(getResources().getString(R.string.kitchen_address));
        titleFirstBar.leftFinish();
        //  edittext_search = (AutoCompleteTextView) findViewById(R.id.act_edittext_search);

    }

    @Override
    protected void initBLL(Bundle savedInstanceState) {

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlv_address.setLayoutManager(linearLayoutManager);
        addressAdaper = new KitchenAddressAdaper(this);
        rlv_address.setAdapter(addressAdaper);

        kitchenAddressPresenter.pHistoricalAddresses();
        mMapView.setLongClickable(true);
        //设置进入时候的放大倍数
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15.0f));
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);//注册监听函数
        kitchenAddressPresenter.pInitLocation();
        mLocationClient.start();
        //对地图单击事件的监听
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                goClickHere(latLng);
                kitchenAddressPresenter.pGetNowAddress(latLng, getResources().getColor(R.color.color_9a9a9a));
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });


//        edittext_search.addTextChangedListener(new TextWatcher() {
//
//
//            public void afterTextChanged(Editable editable) {
//                // 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
//
//                mSuggestionSearch.requestSuggestion((new SuggestionSearchOption())
//                        .keyword(edittext_search.getText().toString())
//                        .city(""));
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//
//            }
//
//            public void onTextChanged(CharSequence s, int start, int before,
//                                      int count) {
//
//
//            }
//
//        });
//        edittext_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int index,
//                                    long arg3) {
//                // TODO Auto-generated method stub
//                Toast.makeText(KitchenAddressActivity.this, "" + index, Toast.LENGTH_SHORT).show();
//            }
//        });


//        mSuggestionSearch.setOnGetSuggestionResultListener(new OnGetSuggestionResultListener() {
//            public void onGetSuggestionResult(SuggestionResult res) {
//
//                if (res != null && res.getAllSuggestions() != null) {
//
//                    for (int i = 0; i < res.getAllSuggestions().size(); i++) {
//                        Log.e("district", "district:" + res.getAllSuggestions().get(i).district);
//                        Log.e("district", "city:" + res.getAllSuggestions().get(i).city);
//                        Log.e("district", "uid:" + res.getAllSuggestions().get(i).uid);
//                        Log.e("district", "key:" + res.getAllSuggestions().get(i).key);
//
//                    }
//                    //show(res.getAllSuggestions());
//                    return;
//                }
//
//                if (res == null || res.getAllSuggestions() == null) {
//
//                    return;
//                }
//            }
//        });
    }


    @Override
    public void vInitLocation(LocationClientOption option) {
        mLocationClient.setLocOption(option);
    }

    @Override
    public void vAddresses(Spannable addressFirst) {
        addressAdaper.notifyFirst(addressFirst);
    }

    @Override
    public void vHistoricalAddresses(List<Spannable> addressAll) {
        addressAdaper.setListData(addressAll);
    }


    /**
     * 监听函数，有新位置的时候，格式化成字符串，输出到屏幕中
     */
    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
            kitchenAddressPresenter.pGetNowAddress(location, getResources().getColor(R.color.color_9a9a9a));
            if (lastLocation != null) {
                if (lastLocation.getLatitude() == location.getLatitude() && lastLocation.getLongitude() == location.getLongitude()) {
                    Log.e("map", "same location, skip refresh");
                    return;
                }
            }
            lastLocation = location;
            //获取定位经纬度
            LatLng convertLatLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            goClickHere(convertLatLng);
            //设置获取定位信息后的放大倍数
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLngZoom(convertLatLng, 17.0f));
            //停止刷新
            mLocationClient.stop();
        }
    }

    /**
     * 在地图上显示图标
     *
     * @param convertLatLng
     */
    protected void goClickHere(LatLng convertLatLng) {
        mBaiduMap.clear();
        //图片信息
        OverlayOptions ooA = new MarkerOptions()
                .position(convertLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_search_green))
                .zIndex(4).draggable(true);
        mBaiduMap.addOverlay(ooA);

    }


//    protected void show(final List<SuggestionResult.SuggestionInfo> allsuggestions) {
//        final List<String> tempAddress = new ArrayList<>();
//
//        for (SuggestionResult.SuggestionInfo temp : allsuggestions) {
//            tempAddress.add(temp.key);
//        }
//
//        adapter = new ArrayAdapter<String>(KitchenAddressActivity.this,
//                android.R.layout.simple_dropdown_item_1line, tempAddress) {
//
//            private Filter f;
//
//            @Override
//            public Filter getFilter() {
//                // TODO Auto-generated method stub
//                if (f == null) {
//                    f = new Filter() {
//
//                        @Override
//                        protected synchronized FilterResults performFiltering(
//                                CharSequence c) {
//                            // TODO Auto-generated method stub
//
//                            FilterResults filterResults = new FilterResults();
//                            filterResults.values = tempAddress;
//                            filterResults.count = tempAddress.size();
//                            return filterResults;
//                        }
//
//                        @Override
//                        protected synchronized void publishResults(
//                                CharSequence c, FilterResults results) {
//                            if (results.count > 0) {
//                                adapter.notifyDataSetChanged();
//                            } else {
//                                adapter.notifyDataSetInvalidated();
//                            }
//
//                        }
//
//                    };
//                }
//                return f;
//            }
//
//        };
//        edittext_search.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        adapter.notifyDataSetInvalidated();
//
//    }

//    private BaiduSDKReceiver mBaiduReceiver;
//    /**
//     * 构造广播监听类，监听 SDK key 验证以及网络异常广播
//     */
//    public class BaiduSDKReceiver extends BroadcastReceiver {
//        public void onReceive(Context context, Intent intent) {
//            String s = intent.getAction();
//            String st1 = getResources().getString(R.string.Network_error);
//            if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
//
//                String st2 = getResources().getString(R.string.please_check);
//                Toast.makeText(instance, st2, Toast.LENGTH_SHORT).show();
//            } else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
//                Toast.makeText(instance, st1, Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


//    public class MyLocationListener implements BDLocationListener {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            //Receive Location
//            StringBuffer sb = new StringBuffer(256);
//            sb.append("time : ");
//            sb.append(location.getTime());
//            sb.append("\nerror code : ");
//            sb.append(location.getLocType());
//            sb.append("\nlatitude : ");
//            sb.append(location.getLatitude());
//            sb.append("\nlontitude : ");
//            sb.append(location.getLongitude());
//            sb.append("\nradius : ");
//            sb.append(location.getRadius());
//            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
//                sb.append("\nspeed : ");
//                sb.append(location.getSpeed());// 单位：公里每小时
//                sb.append("\nsatellite : ");
//                sb.append(location.getSatelliteNumber());
//                sb.append("\nheight : ");
//                sb.append(location.getAltitude());// 单位：米
//                sb.append("\ndirection : ");
//                sb.append(location.getDirection());// 单位度
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//                sb.append("\ndescribe : ");
//                sb.append("gps定位成功");
//
//            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//                //运营商信息
//                sb.append("\noperationers : ");
//                sb.append(location.getOperators());
//                sb.append("\ndescribe : ");
//                sb.append("网络定位成功");
//            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                sb.append("\ndescribe : ");
//                sb.append("离线定位成功，离线定位结果也是有效的");
//            } else if (location.getLocType() == BDLocation.TypeServerError) {
//                sb.append("\ndescribe : ");
//                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
//            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                sb.append("\ndescribe : ");
//                sb.append("网络不同导致定位失败，请检查网络是否通畅");
//            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                sb.append("\ndescribe : ");
//                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
//            }
//            sb.append("\nlocationdescribe : ");
//            sb.append(location.getLocationDescribe());// 位置语义化信息
//            List<Poi> list = location.getPoiList();// POI数据
//            if (list != null) {
//                sb.append("\npoilist size = : ");
//                sb.append(list.size());
//                for (Poi p : list) {
//                    sb.append("\npoi= : ");
//                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
//                }
//            }
//            Log.e("BaiduLocationApiDem", sb.toString());
//        }
//    }


}
