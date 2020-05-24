package com.dam2.medicion;

        import androidx.appcompat.app.AppCompatActivity;

        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor giroscopio;
    private SensorEventListener giroscopioEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        giroscopio = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (giroscopio == null){
            Toast.makeText(this, "Este dispositivo no tiene giroscopio", Toast.LENGTH_LONG).show();
        }

        giroscopioEventListener = new SensorEventListener(){
            @Override
            public void onSensorChanged(SensorEvent sensorEvent){
                TextView text = (TextView) findViewById(R.id.x);
                String texto = String.valueOf(sensorEvent.values[0]);
                text.setText("X: " + texto);
                TextView text2 = (TextView) findViewById(R.id.y);
                String texto2 = String.valueOf(sensorEvent.values[1]);
                text2.setText("y: " + texto2);
                TextView text3 = (TextView) findViewById(R.id.z);
                String texto3 = String.valueOf(sensorEvent.values[2]);
                text3.setText("Z: " + texto3);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(giroscopioEventListener, giroscopio, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(giroscopioEventListener);
    }
}
