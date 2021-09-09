package com.gl.inclusive2;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class BraileLeer extends AppCompatActivity {

    private Intent intent;
    private LinearLayout linearLayoutBraileLeer;
    private Button atras;
    private Button adelante;
    private LinearLayout boton;
    private ImageView[][] botones;
    private TextView textoSalidaEspanol;
    private byte[] posicion;
    ArrayList<String> salida;
    private Vibrator vibrator;
    private byte x;
    private byte y;
    private String texto;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_braile_leer);
        vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        setTranslate();
        setrelativeLayoutBraileLeer();
        cargarCaracter(0);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setrelativeLayoutBraileLeer() {
        linearLayoutBraileLeer = findViewById(R.id.linear_layout_braile_leer);
        textoSalidaEspanol = linearLayoutBraileLeer.findViewById(R.id.texto_salida_espanol);
        atras = linearLayoutBraileLeer.findViewById(R.id.atras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index > 0) {
                    cargarCaracter(index - 1);
                }
            }
        });
        adelante = linearLayoutBraileLeer.findViewById(R.id.adelante);
        adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index < texto.length() - 1) {
                    cargarCaracter(index + 1);
                }
            }
        });
        posicion = new byte[2];
        Arrays.fill(posicion, (byte) -1);
        x = -1;
        y = -1;
        botones = new ImageView[4][3];
        botones[0][0] = linearLayoutBraileLeer.findViewById(R.id.b_cero);
        botones[0][1] = linearLayoutBraileLeer.findViewById(R.id.b_uno);
        botones[0][2] = linearLayoutBraileLeer.findViewById(R.id.b_dos);
        botones[1][0] = linearLayoutBraileLeer.findViewById(R.id.b_tres);
        botones[1][1] = linearLayoutBraileLeer.findViewById(R.id.b_cuatro);
        botones[1][2] = linearLayoutBraileLeer.findViewById(R.id.b_cinco);
        botones[2][0] = linearLayoutBraileLeer.findViewById(R.id.b_seis);
        botones[2][1] = linearLayoutBraileLeer.findViewById(R.id.b_siete);
        botones[2][2] = linearLayoutBraileLeer.findViewById(R.id.b_ocho);
        botones[3][0] = linearLayoutBraileLeer.findViewById(R.id.b_nueve);
        botones[3][1] = linearLayoutBraileLeer.findViewById(R.id.b_diez);
        botones[3][2] = linearLayoutBraileLeer.findViewById(R.id.b_once);
        for (int x = 0; x < botones.length; x++){
            for (int y = 0; y < 3; y++) {
                botones[x][y].setTag(0);
            }
        }
        boton = linearLayoutBraileLeer.findViewById(R.id.leer);
        boton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE || motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    if (motionEvent.getX() <= (Integer) (boton.getWidth()/4)){
                        x = 0;
                    }
                    else if (motionEvent.getX() <= (Integer) (boton.getWidth()/4*2)){
                        x = 1;
                    }
                    else if (motionEvent.getX() <= (Integer) (boton.getWidth()/4*3)){
                        x = 2;
                    }
                    else{
                        x = 3;
                    }
                    if (motionEvent.getY() <= (Integer) (boton.getHeight()/3)){
                        y = 0;
                    }
                    else if (motionEvent.getY() <= (Integer) (boton.getHeight()/3*2)){
                        y = 1;
                    }
                    else{
                        y = 2;
                    }
                }
                if (posicion[0] != x|| posicion[1] != y || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if ((Integer) botones[x][y].getTag() == 1) {
                        vibrator.vibrate(50);
                    }
                    posicion[0] = x;
                    posicion[1] = y;
                }
            return true;
            }
        });
    }

    public void setTranslate(){
        intent = getIntent();
        texto = intent.getStringExtra("k");
        salida = new ArrayList<>();
        for (int x = 0; x < texto.length(); x++){
            switch (String.valueOf(texto.charAt(x))){
                case "0":
                    salida.add("001111010110");
                    break;
                case "1":
                    salida.add("001111100000");
                    break;
                case "2":
                    salida.add("001111110000");
                    break;
                case "3":
                    salida.add("001111100100");
                    break;
                case "4":
                    salida.add("001111100110");
                    break;
                case "5":
                    salida.add("001111100010");
                    break;
                case "6":
                    salida.add("001111110100");
                    break;
                case "7":
                    salida.add("001111110110");
                    break;
                case "8":
                    salida.add("001111110010");
                    break;
                case "9":
                    salida.add("001111010100");
                    break;
                case "A":
                    salida.add("000000100000");
                    break;
                case "B":
                    salida.add("000000110000");
                    break;
                case "C":
                    salida.add("000000100100");
                    break;
                case "D":
                    salida.add("000000100110");
                    break;
                case "E":
                    salida.add("000000100010");
                    break;
                case "F":
                    salida.add("000000110100");
                    break;
                case "G":
                    salida.add("000000110110");
                    break;
                case "H":
                    salida.add("000000110010");
                    break;
                case "I":
                    salida.add("000000010100");
                    break;
                case "J":
                    salida.add("000000010110");
                    break;
                case "K":
                    salida.add("000000101000");
                    break;
                case "L":
                    salida.add("000000111000");
                    break;
                case "M":
                    salida.add("000000101100");
                    break;
                case "N":
                    salida.add("000000101110");
                    break;
                case "Ñ":
                    salida.add("000000110111");
                    break;
                case "O":
                    salida.add("000000101010");
                    break;
                case "P":
                    salida.add("000000111100");
                    break;
                case "Q":
                    salida.add("000000111110");
                    break;
                case "R":
                    salida.add("000000111010");
                    break;
                case "S":
                    salida.add("000000011100");
                    break;
                case "T":
                    salida.add("000000011110");
                    break;
                case "U":
                    salida.add("000000101001");
                    break;
                case "V":
                    salida.add("000000111001");
                    break;
                case "W":
                    salida.add("000000010111");
                    break;
                case "X":
                    salida.add("000000101101");
                    break;
                case "Y":
                    salida.add("000000101111");
                    break;
                case "Z":
                    salida.add("000000101011");
                    break;
                case " ":
                    salida.add("000000000000");
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
    public void cargarCaracter(int index){
        textoSalidaEspanol.setText(String.valueOf(intent.getStringExtra("k").charAt(index)));
        byte z =0;
        for (int x = 0; x < botones.length; x++){
            for (int y = 0; y < 3; y++) {
                if (String.valueOf(salida.get(index).charAt(z)).equals("0")) {
                    botones[x][y].setImageResource(R.drawable.b_vacio);
                    botones[x][y].setTag(0);
                } else {
                    botones[x][y].setImageResource(R.drawable.b_lleno);
                    botones[x][y].setTag(1);
                }
                z++;
            }
        }
        this.index = index;
    }
}