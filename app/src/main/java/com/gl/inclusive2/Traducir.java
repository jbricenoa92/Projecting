package com.gl.inclusive2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Traducir extends AppCompatActivity {

    ViewGroup layout;
    LayoutInflater layoutInflater;
    LinearLayout linearLayoutTextoEntrada;
    LinearLayout linearLayoutTextoSalida;
    RelativeLayout rlTecladoDactilologico;
    RelativeLayout rlTecladoBraile;
    Spinner alfabetoEntrada;
    ArrayAdapter<CharSequence> adapterEntrada;
    Spinner alfabetoSalida;
    TextView borrarTextoEntrada;
    ArrayAdapter<CharSequence> adapterSalida;
    ImageButton cambio;
    AutoCompleteTextView textoEntrada;
    TextView textoSalida;
    LinearLayout tecladoDactilologico;
    LinearLayout tecladoBraile;
    Typeface fontLsc;
    Typeface fontBraile;
    String s;
    int textoEntradaId;
    int textoSalidaId;
    int tecladoId;
    String[] sugerencias;
    Integer[] id;
    ImageView iUno;
    ImageView iDos;
    ImageView iTres;
    ImageView iCuatro;
    ImageView iCinco;
    ImageView iSeis;
    ImageButton bEsp;
    ImageButton bNumero;
    ImageButton bDel;
    ImageButton bCheck;
    ImageButton bLeer;
    LinearLayout boton;
    int posicion = -1;
    Vibrator vibrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traducir);
        layout = findViewById(R.id.linear_layout);
        layoutInflater = LayoutInflater.from(this);
        textoEntradaId = R.layout.texto_entrada;
        linearLayoutTextoEntrada = (LinearLayout) layoutInflater.inflate(textoEntradaId, layout, false);
        textoEntrada = (AutoCompleteTextView) linearLayoutTextoEntrada.findViewById(R.id.texto_entrada);
        borrarTextoEntrada = (TextView)  linearLayoutTextoEntrada.findViewById(R.id.borrar_texto);
        sugerencias = getResources().getStringArray(R.array.sugerencias);
        textoSalidaId = R.layout.texto_salida;
        linearLayoutTextoSalida = (LinearLayout) layoutInflater.inflate(textoSalidaId, layout, false);
        textoSalida = (TextView) linearLayoutTextoSalida.findViewById(R.id.texto_salida);
        textoSalida.setMovementMethod(new ScrollingMovementMethod());
        tecladoId = R.layout.teclado_dactilologico;
        rlTecladoDactilologico = (RelativeLayout) layoutInflater.inflate(tecladoId, layout, false);
        tecladoDactilologico = (LinearLayout) rlTecladoDactilologico.findViewById(R.id.teclado_dactilologico);
        rlTecladoBraile = (RelativeLayout) layoutInflater.inflate(R.layout.teclado_braile, layout, false);
        tecladoBraile = rlTecladoBraile.findViewById(R.id.teclado_braile);
        vibrar = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        setAlfabetoSalida();
        setAlfabetoEntrada();
        setCambio();
        setBorrarTextoEntrada();
        setTextoSalida();
        setTecladoDactilologico();
        setTecladoBraile();
    }

    public void setAlfabetoEntrada(){
        fontLsc = ResourcesCompat.getFont(this, R.font.lsc);
        fontBraile = ResourcesCompat.getFont(this, R.font.braile_regular);
        alfabetoEntrada = findViewById(R.id.alfabeto_entrada);
        adapterEntrada = ArrayAdapter.createFromResource(this, R.array.alfabeto, android.R.layout.simple_spinner_dropdown_item);
        alfabetoEntrada.setAdapter(adapterEntrada);
        alfabetoEntrada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                layout.removeView(linearLayoutTextoEntrada);
                layout.removeView(linearLayoutTextoSalida);
                layout.removeView(rlTecladoDactilologico);
                layout.removeView(rlTecladoBraile);
                textoEntrada.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        textoSalida.setText(charSequence.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });
                switch(i) {
                    case 0:
                        textoEntrada.setKeyListener(DigitsKeyListener.getInstance(" ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789"));
                        textoEntrada.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        textoEntrada.setTypeface(Typeface.DEFAULT);
                        textoEntrada.setTextSize(56);
                        ArrayAdapter<String> adapterSugerencias = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, sugerencias);
                        textoEntrada.setAdapter(adapterSugerencias);
                        layout.addView(linearLayoutTextoEntrada);
                        layout.addView(linearLayoutTextoSalida);
                        break;
                    case 1:
                        textoEntrada.setTypeface(fontLsc);
                        textoEntrada.setInputType(InputType.TYPE_NULL);
                        textoEntrada.setTextSize(56);
                        layout.addView(linearLayoutTextoEntrada);
                        layout.addView(linearLayoutTextoSalida);
                        layout.addView(rlTecladoDactilologico);
                        break;
                    case 2:
                        textoEntrada.setTypeface(fontBraile);
                        textoEntrada.setInputType(InputType.TYPE_NULL);
                        textoEntrada.setTextSize(80);
                        layout.addView(linearLayoutTextoEntrada);
                        layout.addView(linearLayoutTextoSalida);
                        layout.addView(rlTecladoBraile);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void setAlfabetoSalida(){
        alfabetoSalida = findViewById(R.id.alfabeto_salida);
        adapterSalida = ArrayAdapter.createFromResource(this, R.array.alfabeto, android.R.layout.simple_spinner_dropdown_item);
        alfabetoSalida.setAdapter(adapterSalida);
        alfabetoSalida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                layout.removeView(linearLayoutTextoSalida);
                layout.removeView(rlTecladoDactilologico);
                if (alfabetoSalida.getSelectedItemPosition() == 2) {
                    textoSalida.setLineSpacing(2,2);
                    textoSalida.setMaxLines(3);
                }else{
                    textoSalida.setLineSpacing(1,1);
                    textoSalida.setMaxLines(5);
                }
                switch(i) {
                    case 0:
                        textoSalida.setTypeface(Typeface.DEFAULT);
                        textoSalida.setTextSize(56);
                        layout.addView(linearLayoutTextoSalida);
                        try {
                            layout.removeView(rlTecladoDactilologico);
                            layout.removeView(rlTecladoBraile);
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), "error de teclado", Toast.LENGTH_SHORT).show();
                        }
                        if (alfabetoEntrada.getSelectedItemPosition() == 1) {
                            layout.addView(rlTecladoDactilologico);
                        } else if (alfabetoEntrada.getSelectedItemPosition() == 2) {
                            layout.addView(rlTecladoBraile);
                        }
                        break;
                    case 1:
                        textoSalida.setTypeface(fontLsc);
                        textoSalida.setTextSize(56);
                        layout.addView(linearLayoutTextoSalida);
                        try {
                            layout.removeView(rlTecladoDactilologico);
                            layout.removeView(rlTecladoBraile);
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), "error de teclado", Toast.LENGTH_SHORT).show();
                        }
                        if (alfabetoEntrada.getSelectedItemPosition() == 1) {
                            layout.addView(rlTecladoDactilologico);
                        } else if (alfabetoEntrada.getSelectedItemPosition() == 2) {
                            layout.addView(rlTecladoBraile);
                        }
                        break;
                    case 2:
                        textoSalida.setTypeface(fontBraile);
                        textoSalida.setTextSize(80);
                        layout.addView(linearLayoutTextoSalida);
                        try {
                            layout.removeView(rlTecladoDactilologico);
                            layout.removeView(rlTecladoBraile);
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), "error de teclado", Toast.LENGTH_SHORT).show();
                        }
                        if (alfabetoEntrada.getSelectedItemPosition() == 1) {
                            layout.addView(rlTecladoDactilologico);
                        } else if (alfabetoEntrada.getSelectedItemPosition() == 2) {
                            layout.addView(rlTecladoBraile);
                        }
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void setCambio(){
        cambio = findViewById(R.id.cambio);
        cambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = alfabetoEntrada.getSelectedItemPosition();
                alfabetoEntrada.setSelection(alfabetoSalida.getSelectedItemPosition());
                alfabetoSalida.setSelection(i);
            }
        });
    }

    public void setBorrarTextoEntrada(){
        borrarTextoEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText("");
            }
        });
    }

    public void setTecladoDactilologico(){
        TextView uno = (TextView) tecladoDactilologico.findViewById(R.id.uno);
        uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"1");
            }
        });
        TextView dos = (TextView) tecladoDactilologico.findViewById(R.id.dos);
        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"2");
            }
        });
        TextView tres = (TextView) tecladoDactilologico.findViewById(R.id.tres);
        tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"3");
            }
        });
        TextView cuatro = (TextView) tecladoDactilologico.findViewById(R.id.cuatro);
        cuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"4");
            }
        });
        TextView cinco = (TextView) tecladoDactilologico.findViewById(R.id.cinco);
        cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"5");
            }
        });
        TextView seis = (TextView) tecladoDactilologico.findViewById(R.id.seis);
        seis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"6");
            }
        });
        TextView siete = (TextView) tecladoDactilologico.findViewById(R.id.siete);
        siete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"7");
            }
        });
        TextView ocho = (TextView) tecladoDactilologico.findViewById(R.id.ocho);
        ocho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"8");
            }
        });
        TextView nueve = (TextView) tecladoDactilologico.findViewById(R.id.nueve);
        nueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"9");
            }
        });
        TextView cero = (TextView) tecladoDactilologico.findViewById(R.id.cero);
        cero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"0");
            }
        });
        TextView q = (TextView) tecladoDactilologico.findViewById(R.id.q);
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"Q");
            }
        });
        TextView w = (TextView) tecladoDactilologico.findViewById(R.id.w);
        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"W");
            }
        });
        TextView e = (TextView) tecladoDactilologico.findViewById(R.id.e);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"E");
            }
        });
        TextView r = (TextView) tecladoDactilologico.findViewById(R.id.r);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"R");
            }
        });
        TextView t = (TextView) tecladoDactilologico.findViewById(R.id.t);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"T");
            }
        });
        TextView y = (TextView) tecladoDactilologico.findViewById(R.id.y);
        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"Y");
            }
        });
        TextView u = (TextView) tecladoDactilologico.findViewById(R.id.u);
        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"U");
            }
        });
        TextView i = (TextView) tecladoDactilologico.findViewById(R.id.i);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"I");
            }
        });
        TextView o = (TextView) tecladoDactilologico.findViewById(R.id.o);
        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"O");
            }
        });
        TextView p = (TextView) tecladoDactilologico.findViewById(R.id.p);
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"P");
            }
        });
        TextView a = (TextView) tecladoDactilologico.findViewById(R.id.a);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"A");
            }
        });
        TextView s = (TextView) tecladoDactilologico.findViewById(R.id.s);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"S");
            }
        });
        TextView d = (TextView) tecladoDactilologico.findViewById(R.id.d);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"D");
            }
        });
        TextView f = (TextView) tecladoDactilologico.findViewById(R.id.f);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"F");
            }
        });
        TextView g = (TextView) tecladoDactilologico.findViewById(R.id.g);
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"G");
            }
        });
        TextView h = (TextView) tecladoDactilologico.findViewById(R.id.h);
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"H");
            }
        });
        TextView j = (TextView) tecladoDactilologico.findViewById(R.id.j);
        j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"J");
            }
        });
        TextView k = (TextView) tecladoDactilologico.findViewById(R.id.k);
        k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"K");
            }
        });
        TextView l = (TextView) tecladoDactilologico.findViewById(R.id.l);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"L");
            }
        });
        TextView ene = (TextView) tecladoDactilologico.findViewById(R.id.ñ);
        ene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"Ñ");
            }
        });
        TextView z = (TextView) tecladoDactilologico.findViewById(R.id.z);
        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"Z");
            }
        });
        TextView x = (TextView) tecladoDactilologico.findViewById(R.id.x);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"X");
            }
        });
        TextView c = (TextView) tecladoDactilologico.findViewById(R.id.c);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"C");
            }
        });
        TextView v = (TextView) tecladoDactilologico.findViewById(R.id.v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"V");
            }
        });
        TextView b = (TextView) tecladoDactilologico.findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"B");
            }
        });
        TextView n = (TextView) tecladoDactilologico.findViewById(R.id.n);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"N");
            }
        });
        TextView m = (TextView) tecladoDactilologico.findViewById(R.id.m);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+"M");
            }
        });
        TextView esp = (TextView) tecladoDactilologico.findViewById(R.id.esp);
        esp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+" ");
            }
        });
        TextView del = (TextView) tecladoDactilologico.findViewById(R.id.del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    textoEntrada.setText(textoEntrada.getText().toString().substring(0, textoEntrada.getText().toString().length() -1));
                }catch (Exception e){}
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setTecladoBraile(){
        posicion = -1;
        id = new Integer[6];
        Arrays.fill(id, 0);
        iUno = tecladoBraile.findViewById(R.id.i_uno);
        iUno.setTag(0);
        iDos = tecladoBraile.findViewById(R.id.i_dos);
        iDos.setTag(0);
        iTres = tecladoBraile.findViewById(R.id.i_tres);
        iTres.setTag(0);
        iCuatro = tecladoBraile.findViewById(R.id.i_cuatro);
        iCuatro.setTag(0);
        iCinco = tecladoBraile.findViewById(R.id.i_cinco);
        iCinco.setTag(0);
        iSeis = tecladoBraile.findViewById(R.id.i_seis);
        iSeis.setTag(0);
        boton = tecladoBraile.findViewById(R.id.boton);
        boton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_MOVE || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if (motionEvent.getX() <= (Integer) (boton.getWidth() / 2)) {
                    if (motionEvent.getY() <= (Integer) (boton.getHeight() / 3)) {
                        if (0 != posicion || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            switch ((Integer) iUno.getTag()) {
                                case 0:
                                    id[0] = 1;
                                    iUno.setTag(1);
                                    iUno.setImageResource(R.drawable.b_lleno);
                                    vibrar.vibrate(50);
                                    break;
                                case 1:
                                    id[0] = 0;
                                    iUno.setTag(0);
                                    iUno.setImageResource(R.drawable.b_vacio);
                                    vibrar.vibrate(50);
                                    break;
                                default:
                                    Toast.makeText(getApplicationContext(), "error en la tecla 1", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            posicion = 0;
                        }
                    } else if (motionEvent.getY() <= (Integer) (boton.getHeight() / 3 * 2)) {
                        if (1 != posicion || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            switch ((Integer) iDos.getTag()) {
                                case 0:
                                    id[1] = 1;
                                    iDos.setTag(1);
                                    iDos.setImageResource(R.drawable.b_lleno);
                                    vibrar.vibrate(50);
                                    break;
                                case 1:
                                    id[1] = 0;
                                    iDos.setTag(0);
                                    iDos.setImageResource(R.drawable.b_vacio);
                                    vibrar.vibrate(50);
                                    break;
                                default:
                                    Toast.makeText(getApplicationContext(), "error en la tecla 2", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            posicion = 1;
                        }
                    } else {
                        if (2 != posicion || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            switch ((Integer) iTres.getTag()) {
                                case 0:
                                    id[2] = 1;
                                    iTres.setTag(1);
                                    iTres.setImageResource(R.drawable.b_lleno);
                                    vibrar.vibrate(50);
                                    break;
                                case 1:
                                    id[2] = 0;
                                    iTres.setTag(0);
                                    iTres.setImageResource(R.drawable.b_vacio);
                                    vibrar.vibrate(50);
                                    break;
                                default:
                                    Toast.makeText(getApplicationContext(), "error en la tecla 3", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            posicion = 2;
                        }
                    }
                } else {
                    if (motionEvent.getY() <= (Integer) (boton.getHeight() / 3)) {
                        if (3 != posicion || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            switch ((Integer) iCuatro.getTag()) {
                                case 0:
                                    id[3] = 1;
                                    iCuatro.setTag(1);
                                    iCuatro.setImageResource(R.drawable.b_lleno);
                                    vibrar.vibrate(50);
                                    break;
                                case 1:
                                    id[3] = 0;
                                    iCuatro.setTag(0);
                                    iCuatro.setImageResource(R.drawable.b_vacio);
                                    vibrar.vibrate(50);
                                    break;
                                default:
                                    Toast.makeText(getApplicationContext(), "error en la tecla 4", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            posicion = 3;
                        }
                    } else if (motionEvent.getY() <= (Integer) (boton.getHeight() / 3 * 2)) {
                        if (4 != posicion || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            switch ((Integer) iCinco.getTag()) {
                                case 0:
                                    id[4] = 1;
                                    iCinco.setTag(1);
                                    iCinco.setImageResource(R.drawable.b_lleno);
                                    vibrar.vibrate(50);
                                    break;
                                case 1:
                                    id[4] = 0;
                                    iCinco.setTag(0);
                                    iCinco.setImageResource(R.drawable.b_vacio);
                                    vibrar.vibrate(50);
                                    break;
                                default:
                                    Toast.makeText(getApplicationContext(), "error en la tecla 5", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            posicion = 4;
                        }
                    } else {
                        if (5 != posicion || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            switch ((Integer) iSeis.getTag()) {
                                case 0:
                                    id[5] = 1;
                                    iSeis.setTag(1);
                                    iSeis.setImageResource(R.drawable.b_lleno);
                                    vibrar.vibrate(50);
                                    break;
                                case 1:
                                    id[5] = 0;
                                    iSeis.setTag(0);
                                    iSeis.setImageResource(R.drawable.b_vacio);
                                    vibrar.vibrate(50);
                                    break;
                                default:
                                    Toast.makeText(getApplicationContext(), "error en la tecla 6", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            posicion = 5;
                        }
                    }
                }
            }
            return true;
            }
        });
        bEsp = tecladoBraile.findViewById(R.id.b_esp);
        bEsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoEntrada.setText(textoEntrada.getText()+" ");
            }
        });
        bDel = tecladoBraile.findViewById(R.id.b_del);
        bDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    textoEntrada.setText(textoEntrada.getText().toString().substring(0, textoEntrada.getText().toString().length() -1));
                }catch (Exception e){}
            }
        });
        bNumero = tecladoBraile.findViewById(R.id.b_numero);
        bNumero.setImageResource(R.drawable.ic_outline_looks_one_24);
        bNumero.setBackgroundColor(000000);
        bNumero.setTag(0);
        bNumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            switch((Integer) bNumero.getTag()){
                case 0:
                    bNumero.setTag(1);
                    bNumero.setImageResource(R.drawable.ic_baseline_looks_one_24_2);
                    break;
                case 1:
                    bNumero.setTag(0);
                    bNumero.setImageResource(R.drawable.ic_outline_looks_one_24);
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "error de número", Toast.LENGTH_SHORT).show();
                    break;
            }
            }
        });
        bCheck = tecladoBraile.findViewById(R.id.b_check);
        bCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder letra = new StringBuilder();
                for (Integer integer : id) {
                    letra.append(integer.toString());
                }
                if (bNumero.getTag().toString().equals("1")){
                    switch(letra.toString()){
                        case "010110":
                            textoEntrada.setText(textoEntrada.getText()+"0");
                            break;
                        case "100000":
                            textoEntrada.setText(textoEntrada.getText()+"1");
                            break;
                        case "110000":
                            textoEntrada.setText(textoEntrada.getText()+"2");
                            break;
                        case "100100":
                            textoEntrada.setText(textoEntrada.getText()+"3");
                            break;
                        case "100110":
                            textoEntrada.setText(textoEntrada.getText()+"4");
                            break;
                        case "100010":
                            textoEntrada.setText(textoEntrada.getText()+"5");
                            break;
                        case "110100":
                            textoEntrada.setText(textoEntrada.getText()+"6");
                            break;
                        case "110110":
                            textoEntrada.setText(textoEntrada.getText()+"7");
                            break;
                        case "110010":
                            textoEntrada.setText(textoEntrada.getText()+"8");
                            break;
                        case "010100":
                            textoEntrada.setText(textoEntrada.getText()+"9");
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "Esto no es un número", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }else{
                    switch(letra.toString()){
                        case "100000":
                            textoEntrada.setText(textoEntrada.getText()+"A");
                            break;
                        case "110000":
                            textoEntrada.setText(textoEntrada.getText()+"B");
                            break;
                        case "100100":
                            textoEntrada.setText(textoEntrada.getText()+"C");
                            break;
                        case "100110":
                            textoEntrada.setText(textoEntrada.getText()+"D");
                            break;
                        case "100010":
                            textoEntrada.setText(textoEntrada.getText()+"E");
                            break;
                        case "110100":
                            textoEntrada.setText(textoEntrada.getText()+"F");
                            break;
                        case "110110":
                            textoEntrada.setText(textoEntrada.getText()+"G");
                            break;
                        case "110010":
                            textoEntrada.setText(textoEntrada.getText()+"H");
                            break;
                        case "010100":
                            textoEntrada.setText(textoEntrada.getText()+"I");
                            break;
                        case "010110":
                            textoEntrada.setText(textoEntrada.getText()+"J");
                            break;
                        case "101000":
                            textoEntrada.setText(textoEntrada.getText()+"K");
                            break;
                        case "111000":
                            textoEntrada.setText(textoEntrada.getText()+"L");
                            break;
                        case "101100":
                            textoEntrada.setText(textoEntrada.getText()+"M");
                            break;
                        case "101110":
                            textoEntrada.setText(textoEntrada.getText()+"N");
                            break;
                        case "110111":
                            textoEntrada.setText(textoEntrada.getText()+"Ñ");
                            break;
                        case "101010":
                            textoEntrada.setText(textoEntrada.getText()+"O");
                            break;
                        case "111100":
                            textoEntrada.setText(textoEntrada.getText()+"P");
                            break;
                        case "111110":
                            textoEntrada.setText(textoEntrada.getText()+"Q");
                            break;
                        case "111010":
                            textoEntrada.setText(textoEntrada.getText()+"R");
                            break;
                        case "011100":
                            textoEntrada.setText(textoEntrada.getText()+"S");
                            break;
                        case "011110":
                            textoEntrada.setText(textoEntrada.getText()+"T");
                            break;
                        case "101001":
                            textoEntrada.setText(textoEntrada.getText()+"U");
                            break;
                        case "111001":
                            textoEntrada.setText(textoEntrada.getText()+"V");
                            break;
                        case "010111":
                            textoEntrada.setText(textoEntrada.getText()+"W");
                            break;
                        case "101101":
                            textoEntrada.setText(textoEntrada.getText()+"X");
                            break;
                        case "101111":
                            textoEntrada.setText(textoEntrada.getText()+"Y");
                            break;
                        case "101011":
                            textoEntrada.setText(textoEntrada.getText()+"Z");
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "Esto no es una letra", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
                iUno.setTag(0);
                iUno.setImageResource(R.drawable.b_vacio);
                id[0] = 0;
                iDos.setTag(0);
                iDos.setImageResource(R.drawable.b_vacio);
                id[1] = 0;
                iTres.setTag(0);
                iTres.setImageResource(R.drawable.b_vacio);
                id[2] = 0;
                iCuatro.setTag(0);
                iCuatro.setImageResource(R.drawable.b_vacio);
                id[3] = 0;
                iCinco.setTag(0);
                iCinco.setImageResource(R.drawable.b_vacio);
                id[4] = 0;
                iSeis.setTag(0);
                iSeis.setImageResource(R.drawable.b_vacio);
                id[5] = 0;
            }
        });
        bLeer = tecladoBraile.findViewById(R.id.b_leer);
        bLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (textoEntrada.getText().toString() != "") {
                Intent intent = new Intent(view.getContext(), BraileLeer.class);
                intent.putExtra("k", textoEntrada.getText().toString());
                startActivity(intent);
            }
            }
        });
    }

    public void setTextoSalida(){
        textoSalida.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(alfabetoSalida.getSelectedItemPosition() == 2) {
                    bLeer.callOnClick();
                }
                return true;
            }
        });
    }
}