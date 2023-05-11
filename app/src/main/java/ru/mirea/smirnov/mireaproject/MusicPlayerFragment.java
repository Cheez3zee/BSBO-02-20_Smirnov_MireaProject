package ru.mirea.smirnov.mireaproject;

import static android.Manifest.permission.FOREGROUND_SERVICE;
import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.smirnov.mireaproject.databinding.FragmentMusicPlayerBinding;

public class MusicPlayerFragment extends Fragment {
    private FragmentMusicPlayerBinding binding;
    private boolean isplaying = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMusicPlayerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (ContextCompat.checkSelfPermission(getContext(), POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{POST_NOTIFICATIONS, FOREGROUND_SERVICE}, 200);
        }

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isplaying)
                {
                    Intent serviceIntent = new Intent(root.getContext(), NotMyServicePlayer.class);
                    ContextCompat.startForegroundService(root.getContext(), serviceIntent);
                }
                else
                {

                    root.getContext().stopService(new Intent(root.getContext(), NotMyServicePlayer.class));

                }
                isplaying = !isplaying;
            }
        });

        return root;
    }
}