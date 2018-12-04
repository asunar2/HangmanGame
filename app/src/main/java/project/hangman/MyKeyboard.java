package project.hangman;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyKeyboard extends LinearLayout implements View.OnClickListener {

    private Button button1, button2, button3, button4,
            button5, button6, button7, button8,
            button9, button10, button11, button12,
            button13, button14, button15, button16, button17,
            button18, button19, button20, button21, button22,
            button23, button24, button25, button26, buttonDelete, buttonEnter;

    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;
    private String value;
    private String currentLetter;
    private GameActivity gameActivity;

    public MyKeyboard(Context context) {
        this(context, null, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);
        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);
        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(this);
        button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(this);
        button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(this);
        button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(this);
        button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(this);
        button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(this);
        button13 = (Button) findViewById(R.id.button13);
        button13.setOnClickListener(this);
        button14 = (Button) findViewById(R.id.button14);
        button14.setOnClickListener(this);
        button15 = (Button) findViewById(R.id.button15);
        button15.setOnClickListener(this);
        button16 = (Button) findViewById(R.id.button16);
        button16.setOnClickListener(this);
        button17 = (Button) findViewById(R.id.button17);
        button17.setOnClickListener(this);
        button18 = (Button) findViewById(R.id.button18);
        button18.setOnClickListener(this);
        button19 = (Button) findViewById(R.id.button19);
        button19.setOnClickListener(this);
        button20 = (Button) findViewById(R.id.button20);
        button20.setOnClickListener(this);
        button21 = (Button) findViewById(R.id.button21);
        button21.setOnClickListener(this);
        button22 = (Button) findViewById(R.id.button22);
        button22.setOnClickListener(this);
        button23 = (Button) findViewById(R.id.button23);
        button23.setOnClickListener(this);
        button24 = (Button) findViewById(R.id.button24);
        button24.setOnClickListener(this);
        button25 = (Button) findViewById(R.id.button25);
        button25.setOnClickListener(this);
        button26 = (Button) findViewById(R.id.button26);
        button26.setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(this);
        buttonEnter = (Button) findViewById(R.id.button_enter);
        buttonEnter.setOnClickListener(this);


        keyValues.put(R.id.button1, "A");
        keyValues.put(R.id.button2, "B");
        keyValues.put(R.id.button3, "C");
        keyValues.put(R.id.button4, "D");
        keyValues.put(R.id.button5, "E");
        keyValues.put(R.id.button6, "F");
        keyValues.put(R.id.button7, "G");
        keyValues.put(R.id.button8, "H");
        keyValues.put(R.id.button9, "I");
        keyValues.put(R.id.button10, "J");
        keyValues.put(R.id.button11, "K");
        keyValues.put(R.id.button12, "L");
        keyValues.put(R.id.button13, "M");
        keyValues.put(R.id.button14, "N");
        keyValues.put(R.id.button15, "O");
        keyValues.put(R.id.button16, "P");
        keyValues.put(R.id.button17, "Q");
        keyValues.put(R.id.button18, "R");
        keyValues.put(R.id.button19, "S");
        keyValues.put(R.id.button20, "T");
        keyValues.put(R.id.button21, "U");
        keyValues.put(R.id.button22, "V");
        keyValues.put(R.id.button23, "W");
        keyValues.put(R.id.button24, "X");
        keyValues.put(R.id.button25, "Y");
        keyValues.put(R.id.button26, "Z");
        keyValues.put(R.id.button_enter, "\b");

        //So context is like Object, you have to cast it
        gameActivity = (GameActivity) context;
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_delete) {
            CharSequence selectedText = inputConnection.getSelectedText(0);

            if (TextUtils.isEmpty(selectedText)) {
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                inputConnection.commitText("", 1);
            }
        } else if (view.getId() == R.id.button_enter) {
            currentLetter = value;
            value = "";
            inputConnection.deleteSurroundingText(1, 0);
            gameActivity.playGame();
        } else {
            value = keyValues.get(view.getId());
            inputConnection.commitText(value, 1);
        }
    }

    public void setInputConnection(InputConnection ic) {
        inputConnection = ic;
    }

    public String getValue() { return currentLetter;}


}