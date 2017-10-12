package com.example.shraaboni.superdonor.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shraaboni.superdonor.R;

import me.wangyuwei.particleview.ParticleView;

public class MainActivity extends AppCompatActivity {

    ParticleView mParticleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mParticleView =(ParticleView) findViewById(R.id.mparticle);
        mParticleView.startAnim();
        mParticleView.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                Intent intent = new Intent(MainActivity.this, SignUpActivity .class);
                startActivity(intent);
            }
        });
    }
}
