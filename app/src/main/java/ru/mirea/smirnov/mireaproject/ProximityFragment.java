package ru.mirea.smirnov.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import ru.mirea.smirnov.mireaproject.databinding.FragmentMusicPlayerBinding;
import ru.mirea.smirnov.mireaproject.databinding.FragmentProximityBinding;

public class ProximityFragment extends Fragment implements SensorEventListener{

        private FragmentProximityBinding binding;
        private SensorManager mSensorManager;
        private Sensor mProximity;
        private static final int SENSOR_SENSITIVITY = 4;

        @Override
        public void onResume() {
            super.onResume();
            mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        public void onPause() {
            super.onPause();
            mSensorManager.unregisterListener(this);
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
                    //near
                    Toast.makeText(getContext().getApplicationContext(), "near", Toast.LENGTH_SHORT).show();
                } else {
                    //far
                    Toast.makeText(getContext().getApplicationContext(), "far", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProximityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        return root;
    }
}