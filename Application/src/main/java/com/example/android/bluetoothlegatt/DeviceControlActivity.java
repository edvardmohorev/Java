/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * For a given BLE device, this Activity provides the user interface to connect, display data,
 * and display GATT services and characteristics supported by the device.  The Activity
 * communicates with {@code BluetoothLeService}, which in turn interacts with the
 * Bluetooth LE API.
 */
public class DeviceControlActivity extends Activity {
    private final static String TAG = DeviceControlActivity.class.getSimpleName();

    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
    float x;
    float y;
    float yp = -1000;
    float yr = 0;
    float yx = 0;
    List<Integer> hss = new ArrayList<>();
    double begin = System.currentTimeMillis();
    double end;
    double past_time = System.currentTimeMillis();
    double real_time;
    LineData datas;

    //    private TextView mConnectionState;
//    private TextView mDataField;
    private String mDeviceName;
    int o = 0;
    private String mDeviceAddress;
    private ExpandableListView mGattServicesList;
    private BluetoothLeService mBluetoothLeService;
    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics =
            new ArrayList<ArrayList<BluetoothGattCharacteristic>>();
    private boolean mConnected = false;
    private BluetoothGattCharacteristic mNotifyCharacteristic;
    private LineChart lineChart;
    TextView res;

    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";
    TextView hss_text;
    TextView ifs;
    List<Entry> entries = new ArrayList<>();
    List<Entry> entrieses = new ArrayList<>();

    private final Handler mHandler = new Handler();
    private Runnable mTimer;
    float xf = 0;

    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    // Handles various events fired by the Service.
    // ACTION_GATT_CONNECTED: connected to a GATT server.
    // ACTION_GATT_DISCONNECTED: disconnected from a GATT server.
    // ACTION_GATT_SERVICES_DISCOVERED: discovered GATT services.
    // ACTION_DATA_AVAILABLE: received data from the device.  This can be a result of read
    //                        or notification operations.
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true;
                updateConnectionState(R.string.connected);
                invalidateOptionsMenu();
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                mConnected = false;
                updateConnectionState(R.string.disconnected);
                invalidateOptionsMenu();
                clearUI();
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // Show all the supported services and characteristics on the user interface.
                displayGattServices(mBluetoothLeService.getSupportedGattServices());
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
            }
        }
    };

    // If a given GATT characteristic is selected, check for supported features.  This sample
    // demonstrates 'Read' and 'Notify' features.  See
    // http://d.android.com/reference/android/bluetooth/BluetoothGatt.html for the complete
    // list of supported characteristic features.
    private final ExpandableListView.OnChildClickListener servicesListClickListner =
            new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                            int childPosition, long id) {
                    if (mGattCharacteristics != null) {
                        final BluetoothGattCharacteristic characteristic =
                                mGattCharacteristics.get(groupPosition).get(childPosition);
                        final int charaProp = characteristic.getProperties();
                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                            // If there is an active notification on a characteristic, clear
                            // it first so it doesn't update the data field on the user interface.
                            if (mNotifyCharacteristic != null) {
                                mBluetoothLeService.setCharacteristicNotification(
                                        mNotifyCharacteristic, false);
                                mNotifyCharacteristic = null;
                            }
                            mBluetoothLeService.readCharacteristic(characteristic);
                        }
                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                            mNotifyCharacteristic = characteristic;
                            mBluetoothLeService.setCharacteristicNotification(
                                    characteristic, true);
                        }
                        return true;
                    }
                    return false;
                }
            };

    private void clearUI() {
//        mGattServicesList.setAdapter((SimpleExpandableListAdapter) null);
//        mDataField.setText(R.string.no_data);
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        return entrieses;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gatt_services_characteristics);
//getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.back));
        final Intent intent = getIntent();
        mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);
        hss_text = findViewById(R.id.hss);
        res = findViewById(R.id.res);
        entries = (List<Entry>) getLastNonConfigurationInstance();
        ifs = findViewById(R.id.ifs);// Sets up UI references.
//        ((TextView) findViewById(R.id.device_address)).setText(mDeviceAddress);
//        mGattServicesList = findViewById(R.id.gatt_services_list);
//        mGattServicesList.setOnChildClickListener(servicesListClickListner);
//        mConnectionState = findViewById(R.id.connection_state);
//        mDataField = findViewById(R.id.data_value);
        lineChart = findViewById(R.id.chart);
        initChart(lineChart);
        getActionBar().setTitle(mDeviceName);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    private void initChart(LineChart lineChart) {
//        lineChart.getDescription().setEnabled(true);

        // enable touch gestures
        lineChart.setTouchEnabled(true);
        lineChart.setDrawGridBackground(false);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleXEnabled(true);
        lineChart.setScaleYEnabled(false);
        lineChart.setDrawGridBackground(false);
        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(true);
        XAxis xl = lineChart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setGridColor(Color.BLACK);
        xl.setDrawGridLines(true);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(false);

        YAxis leftAxis = lineChart.getAxisLeft();
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setEnabled(false);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(-100f);
//        leftAxis.setDrawGridLines(false);
//        lineChart.zoom(100f,50f,20f,1f);

        LineData data = new LineData();
        data.setValueTextColor(Color.BLACK);
        lineChart.setData(data);
    }

    private LineDataSet createSet() {
//        entries.add(new Entry(1,50));
//        entries.add(new Entry(10,20));
//        entries.add(new Entry(20,50));
        LineDataSet set = new LineDataSet(entries, "");
        set.setColor(Color.WHITE);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(Color.RED);
        set.setCircleColor(Color.RED);
        set.setLineWidth(1.5f);
        set.setCircleRadius(1f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(9f);
        set.setDrawValues(false);
        return set;
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mGattUpdateReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gatt_services, menu);
        if (mConnected) {
            menu.findItem(R.id.menu_connect).setVisible(false);
            menu.findItem(R.id.menu_disconnect).setVisible(true);
        } else {
            menu.findItem(R.id.menu_connect).setVisible(true);
            menu.findItem(R.id.menu_disconnect).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_connect:
                mBluetoothLeService.connect(mDeviceAddress);
                return true;
            case R.id.menu_disconnect:
                mBluetoothLeService.disconnect();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateConnectionState(final int resourceId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                mConnectionState.setText(resourceId);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("xf", xf);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        xf = savedInstanceState.getFloat("xf");
    }

    private void displayData(String data) {
        datas = lineChart.getData();
        if (datas != null) {

            ILineDataSet set = datas.getDataSetByIndex(0);
            // set.addEntry(...); // can be called as well

            if (set == null) {
                set = createSet();
                datas.addDataSet(set);
            }
            if (data != null) {
//                mDataField.setText(data);
                String[] values = data.split(" ");
                for (int i = 0; i < values.length - 4; i = i + 2) {
                    x = (float) (hex2decimal(values[i + 1]) / 150);
                    y = (float) (hex2decimal(values[i]) - 122.5);
                    real_time = System.currentTimeMillis();
                    end = System.currentTimeMillis();
                    if (end - begin > 20000) {
                        int sum = 0;
                        if (!hss.isEmpty()) {
                            for (int h = 0; h < hss.size(); h++) {
                                sum += hss.get(h);
                            }
                            int avg = sum / hss.size();
                            hss_text.setText(String.valueOf(avg) + " уд/мин");
                            res.setVisibility(View.GONE);
                            hss.clear();
                            if (avg < 50) {
                                ifs.setText("Ниже нормы");
                                hss_text.setTextColor(Color.YELLOW);
                                ifs.setTextColor(Color.YELLOW);
                            } else if (avg >= 50 && avg <= 80) {
                                ifs.setText("В норме");
                                hss_text.setTextColor(Color.GREEN);
                                ifs.setTextColor(Color.GREEN);
                            } else if (avg > 80 && avg <= 90) {
                                ifs.setText("Чуть выше нормы");
                                hss_text.setTextColor(Color.YELLOW);
                                ifs.setTextColor(Color.YELLOW);
                            } else if (avg > 90) {
                                ifs.setText("Тахикардия");
                                hss_text.setTextColor(Color.RED);
                                ifs.setTextColor(Color.RED);
                            }
                        }
                        begin = end;
                    }
                    yr = Math.abs(y);
                    if (yp == -1000) {
                        yp = yr;
                        yr = 0;
                    }
                    if (yr != -1000 && yr - yp > 18) {
                        float time = (float) (real_time - past_time);
                        if (time > 500.0 && time < 1000.0) {
//                            Log.d("GAG", String.valueOf(60000/time));
                            hss.add((int) (60000 / time));
                        }
//                        Log.d("GAG", String.valueOf(time));
                        past_time = real_time;
                    }
//                    Log.d("GAG", "\n"+(y)+"\n"+yr+"\n"+yp+"\n"+(yr-yp));
                    xf = xf + x;
                    yp = yr;
                    ArrayList g = new ArrayList();
                    if (o < 256) {
                        Log.d("GAG", String.valueOf(y));
                    }
                    o++;
//                    Log.d("GAG", data+" "+values[i+1]+" "+x + "  " +values[i]+" "+ y+" "+i);
                    Entry point = new Entry(xf, y);
                    datas.addEntry(point, 0);
                    entrieses.add(point);
                    datas.notifyDataChanged();


                    // let the chart know it's data has changed
                    lineChart.notifyDataSetChanged();
                    lineChart.setVisibleXRangeMaximum(250);

                    // chart.setVisibleYRange(30, AxisDependency.LEFT);

                    // move to the latest entry
                    lineChart.moveViewToX(datas.getXMax());
//                    lineChart.moveViewToX(0);
                }
            }
        }
    }

    public static int hex2decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16 * val + d;
        }
        return val;
    }

    // Demonstrates how to iterate through the supported GATT Services/Characteristics.
    // In this sample, we populate the data structure that is bound to the ExpandableListView
    // on the UI.
    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null) return;
        String uuid;
        String unknownServiceString = getResources().getString(R.string.unknown_service);
        String unknownCharaString = getResources().getString(R.string.unknown_characteristic);
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<HashMap<String, String>>();
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData
                = new ArrayList<ArrayList<HashMap<String, String>>>();
        mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {
            HashMap<String, String> currentServiceData = new HashMap<String, String>();
            uuid = gattService.getUuid().toString();
            if (uuid.equals("0000ffe0-0000-1000-8000-00805f9b34fb")) {
                final BluetoothGattCharacteristic characteristic =
                        gattService.getCharacteristic(UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb"));
                final int charaProp = characteristic.getProperties();
                if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                    // If there is an active notification on a characteristic, clear
                    // it first so it doesn't update the data field on the user interface.
                    if (mNotifyCharacteristic != null) {
                        mBluetoothLeService.setCharacteristicNotification(
                                mNotifyCharacteristic, false);
                        mNotifyCharacteristic = null;
                    }
                    mBluetoothLeService.readCharacteristic(characteristic);
                }
                if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                    mNotifyCharacteristic = characteristic;
                    mBluetoothLeService.setCharacteristicNotification(
                            characteristic, true);
                }
            }
            currentServiceData.put(
                    LIST_NAME, SampleGattAttributes.lookup(uuid, unknownServiceString));
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);

            ArrayList<HashMap<String, String>> gattCharacteristicGroupData =
                    new ArrayList<HashMap<String, String>>();
            List<BluetoothGattCharacteristic> gattCharacteristics =
                    gattService.getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas =
                    new ArrayList<BluetoothGattCharacteristic>();

            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();
                currentCharaData.put(
                        LIST_NAME, SampleGattAttributes.lookup(uuid, unknownCharaString));
                currentCharaData.put(LIST_UUID, uuid);
                gattCharacteristicGroupData.add(currentCharaData);
            }
            mGattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
        }

        SimpleExpandableListAdapter gattServiceAdapter = new SimpleExpandableListAdapter(
                this,
                gattServiceData,
                android.R.layout.simple_expandable_list_item_2,
                new String[]{LIST_NAME, LIST_UUID},
                new int[]{android.R.id.text1, android.R.id.text2},
                gattCharacteristicData,
                android.R.layout.simple_expandable_list_item_2,
                new String[]{LIST_NAME, LIST_UUID},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
//        mGattServicesList.setAdapter(gattServiceAdapter);
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }
}
