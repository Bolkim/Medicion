package com.dam2.medicion;

        import androidx.appcompat.app.AppCompatActivity;

        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.os.Bundle;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor giroscopio;
    //private Sensor angulo;
    private SensorEventListener giroscopioEventListener;
    //private SensorEventListener anguloEventListener;

    private float x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        giroscopio = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        //angulo = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        if (giroscopio == null){
            Toast.makeText(this, "Este dispositivo no tiene giroscopio", Toast.LENGTH_LONG).show();
        }

        giroscopioEventListener = new SensorEventListener(){
            @Override
            public void onSensorChanged(SensorEvent sensorEvent){
                TextView text = (TextView) findViewById(R.id.x);
                x += sensorEvent.values[0];
                String texto = String.valueOf(Math.round(x));
                text.setText("X: " + texto);
                TextView text2 = (TextView) findViewById(R.id.y);
                y += sensorEvent.values[1];
                String texto2 = String.valueOf(Math.round(y));
                text2.setText("y: " + texto2);
                TextView text3 = (TextView) findViewById(R.id.z);
                z+= sensorEvent.values[2];
                String texto3 = String.valueOf(Math.round(z));
                text3.setText("Z: " + texto3);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
//        anguloEventListener = new SensorEventListener(){
//            @Override
//            public void onSensorChanged(SensorEvent sensorEvent){
//                TextView text = (TextView) findViewById(R.id.angulo);
//
//            }
//
//            @Override
//            public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//            }
//        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(giroscopioEventListener, giroscopio, SensorManager.SENSOR_DELAY_FASTEST);
        //sensorManager.registerListener(anguloEventListener, angulo, SensorManager.SENSOR_DELAY_FASTEST);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(giroscopioEventListener);
        //sensorManager.unregisterListener(anguloEventListener);

    }
}
